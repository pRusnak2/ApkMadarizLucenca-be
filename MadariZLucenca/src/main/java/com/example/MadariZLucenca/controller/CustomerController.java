package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.service.Customer;
import com.example.MadariZLucenca.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
    public  class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/zakaznik")
    public Long createNewCustomer(@RequestBody Customer customer) {
        return customerService.createNewCustomer(customer);
    }

    @GetMapping("/zakaznik/{zakaznikId}")
    public Customer customerById(@PathVariable Long zakaznikId) {
        return customerService.customerById(zakaznikId);
    }

    @DeleteMapping("/zakaznik/delete/{zakaznikId}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Long zakaznikId) {
        customerService.deleteCustomerById(zakaznikId);
        return ResponseEntity.ok("id zakaznika " + zakaznikId + " bol uspens vymazany");


    }
}
