package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/order")
    @HasRole({"CUSTOMER", "ADMIN"})
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

    @GetMapping("/orders")
    @HasRole({"CUSTOMER", "ADMIN"})
    public List<Order> getOrdersForCustomer(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring("Bearer ".length()).trim();
        UserRolesDto userRolesDto = authenticationService.authenticate(token);
        String customerName = userRolesDto.getUserName();
        Long customerId = orderService.getCustomerIdByName(customerName);
        return orderService.getOrdersForCustomer(customerId);
    }

    @GetMapping("/orders/restauracia")
    @HasRole({"RESTAURANT", "ADMIN"})
    public List<Order> getOrdersForRestaurant(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring("Bearer ".length()).trim();
        UserRolesDto userRolesDto = authenticationService.authenticate(token);
        String RestaurantName = userRolesDto.getUserName();
        Long RestaurantId = orderService.getRestaurantIdByUsername(RestaurantName);
        return orderService.getOrdersForRestaurant(RestaurantId);
    }


    @DeleteMapping("/orders/vymazanie/{orderId}")
    @HasRole({"RESTAURANT", "ADMIN"})
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @PutMapping("/orders/update/{orderId}")
    @HasRole("RESTAURANT")
    public Order updateOrderStatus(@PathVariable Long orderId, @RequestBody String status) {
        return orderService.updateOrderStatus(orderId, status);
    }
}
