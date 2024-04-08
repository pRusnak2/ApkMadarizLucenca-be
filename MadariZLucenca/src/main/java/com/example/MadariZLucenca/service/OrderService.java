package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Long createNewOrder(Order objednavka){
        OrderEntity entity = new OrderEntity();
        entity.setOrderId(objednavka.getOrderId());
        entity.setStatus(objednavka.getStatus());
        entity.setOrderTime(objednavka.getOrderTime());
        entity.setDeliveryTime(objednavka.getDeliveryTime());
        orderRepository.save(entity);
        return entity.getOrderId();
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
}
