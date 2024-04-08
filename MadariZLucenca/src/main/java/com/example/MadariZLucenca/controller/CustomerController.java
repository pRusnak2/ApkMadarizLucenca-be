package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.persistence.CustomerEntity;
import com.example.MadariZLucenca.persistence.CustomerRepository;
import com.example.MadariZLucenca.service.Customer;
import com.example.MadariZLucenca.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
    public  class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/zakaznik")
    public Long vytvorNovehoZakaznika(@RequestBody Customer customer) {
        return customerService.createNewCustomer(customer);
    }

    @GetMapping("/zakaznik/{zakaznikId}")
    public Customer zakaznikPodlaId(@PathVariable Long zakaznikId) {
        return customerService.customerById(zakaznikId);
    }
}

