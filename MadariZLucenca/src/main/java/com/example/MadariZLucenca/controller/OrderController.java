package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.service.Order;
import com.example.MadariZLucenca.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public Long createNewOrder(@RequestBody Order order) {
        return orderService.createNewOrder(order);
    }

    @GetMapping("/order/{orderId}")
    public Order orderById(@PathVariable Long orderId) {
        return orderService.orderById(orderId);
    }
}
