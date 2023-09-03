package com.bernarsk.orderservice.controller;

import com.bernarsk.orderservice.dao.OrderDAO;
import com.bernarsk.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDAO orderDAO) {
        if (orderService.createOrder(orderDAO)) {
            return ResponseEntity.ok().body("Order placed successfully!");
        } else {
            return new ResponseEntity<>("One of the products is not in stock or does not exist", HttpStatus.NOT_FOUND);
        }
    }


}
