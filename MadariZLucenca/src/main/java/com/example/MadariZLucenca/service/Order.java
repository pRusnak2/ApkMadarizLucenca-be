package com.example.MadariZLucenca.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long orderId;
    private String status;
    private Timestamp orderTime;
    private Timestamp deliveryTime;
    private List<Long> foodIds;
    private List<String> foodNames;

    private int customerTelNumber;
    private String customerEmail;
    private String customerStreetName;
    private String customerCityName;
    private int customerPostCode;
    private String customerFirstName;
    private String customerLastName;
}
