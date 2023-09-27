package com.bernarsk.orderservice.controller;

import com.bernarsk.orderservice.model.Order;
import com.bernarsk.orderservice.dto.OrderDTO;
import com.bernarsk.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        if (orderService.createOrder(orderDTO)) {
            return ResponseEntity.ok().body("Order placed successfully!");
        } else {
            return new ResponseEntity<>("One of the products is not in stock or does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long id) {
        //Boolean ifUserExists = orderService.checkCustomerExists(id);
        return null;
    }


}
