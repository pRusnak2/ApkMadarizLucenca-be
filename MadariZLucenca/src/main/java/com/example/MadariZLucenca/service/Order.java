package com.example.MadariZLucenca.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long orderId;
    private String status;
    private Timestamp orderTime;
    private Timestamp deliveryTime;
}
