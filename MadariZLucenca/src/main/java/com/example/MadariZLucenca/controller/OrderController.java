package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.service.AuthenticationService;
import com.example.MadariZLucenca.service.Order;
import com.example.MadariZLucenca.service.OrderService;
import com.example.MadariZLucenca.service.UserRolesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/order")
    public Order createOrder(@RequestHeader("Authorization") String authHeader, @RequestBody Order order) {
        String token = authHeader.substring("Bearer ".length()).trim();

        UserRolesDto userRolesDto = authenticationService.authenticate(token);

        String customerName = userRolesDto.getUserName();
        Long customerId = orderService.getCustomerIdByName(customerName);

        return orderService.createOrder(customerId, order);
    }

    @GetMapping("/order/{orderId}")
    public Order orderById(@PathVariable Long orderId) {
        return orderService.orderById(orderId);
    }
}
