package com.bernarsk.orderservice.service;

import com.bernarsk.orderservice.dto.OrderDTO;
import com.bernarsk.orderservice.dto.OrderDetailsDTO;
import com.bernarsk.orderservice.event.OrderPlacedEvent;
import com.bernarsk.orderservice.model.Order;
import com.bernarsk.orderservice.model.OrderDetails;
import com.bernarsk.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public Boolean createOrder(OrderDTO orderDTO) {
        // collect all product IDs
        List<Integer> productIDs = orderDTO.getOrderDetailsList()
                .stream()
                .map(OrderDetailsDTO::getProductId)
                .toList();
        // check if product is in stock
        Boolean result = webClientBuilder.build().get()
                .uri("http://product-service/api/products",
                        uriBuilder -> uriBuilder.queryParam("productId", productIDs).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if (result) {
            Order orderEntity = modelMapper.map(orderDTO, Order.class);

            List<OrderDetails> orderDetailsEntities = new ArrayList<>();
            for (OrderDetailsDTO orderDetailsDTO : orderDTO.getOrderDetailsList()) {
                OrderDetails orderDetailsEntity = modelMapper.map(orderDetailsDTO, OrderDetails.class);
                orderDetailsEntity.setOrder(orderEntity);
                orderDetailsEntities.add(orderDetailsEntity);
            }
            orderEntity.setOrderDetailsList(orderDetailsEntities);
            orderRepository.save(orderEntity);

            // kafka

            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(orderEntity.getOrderId()));
            return true;
        } else {
            log.info("One of the products is not in stock!");
            return false;
        }
    }

    public OrderDTO getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        Order order = optionalOrder.orElse(null);
        return modelMapper.map(order, OrderDTO.class);
    }

    public Boolean checkCustomerExists(Long id) {
        return null;
    }
}
