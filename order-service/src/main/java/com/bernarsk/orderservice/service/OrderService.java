package com.bernarsk.orderservice.service;

import com.bernarsk.orderservice.dao.OrderDAO;
import com.bernarsk.orderservice.dao.OrderDetailsDAO;
import com.bernarsk.orderservice.model.Order;
import com.bernarsk.orderservice.model.OrderDetails;
import com.bernarsk.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final WebClient webClient;

    public void createOrder(OrderDAO orderDAO) {
        // check if product is in stock
        Order orderEntity = modelMapper.map(orderDAO, Order.class);

        List<OrderDetails> orderDetailsEntities = new ArrayList<>();
        for (OrderDetailsDAO orderDetailsDAO : orderDAO.getOrderDetailsList()) {
            OrderDetails orderDetailsEntity = modelMapper.map(orderDetailsDAO, OrderDetails.class);
            orderDetailsEntity.setOrder(orderEntity);
            orderDetailsEntities.add(orderDetailsEntity);
        }

        orderEntity.setOrderDetailsList(orderDetailsEntities);

        orderRepository.save(orderEntity);
    }
}
