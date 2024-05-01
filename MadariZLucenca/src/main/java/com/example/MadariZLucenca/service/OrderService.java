package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Order createOrder(Long customerId, Order order) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new IllegalArgumentException("Zákazník s daným ID neexistuje.");
        }

        CustomerEntity customer = optionalCustomer.get();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(customer);
        orderEntity.setStatus(order.getStatus());
        orderEntity.setOrderTime(new Timestamp(order.getOrderTime().getTime()));
        orderEntity.setDeliveryTime(new Timestamp(order.getDeliveryTime().getTime()));

        orderRepository.save(orderEntity);

        order.setOrderId(orderEntity.getOrderId());
        return order;
    }

    public Order orderById(Long id){
        Optional<OrderEntity> opt = orderRepository.findById(id);
        if(opt.isEmpty()){
            return null;
        }
        OrderEntity entity = opt.get();
        Order objednavka = new Order();
        objednavka.setStatus(entity.getStatus());
        objednavka.setOrderId(entity.getOrderId());
        objednavka.setOrderTime(entity.getOrderTime());
        objednavka.setDeliveryTime(entity.getDeliveryTime());
        return objednavka;
    }

    public Long getCustomerIdByName(String userName) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findByUsername(userName);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get().getCustomerId();
        } else {
            throw new IllegalArgumentException("Customer not found with username: " + userName);
        }
    }
}
