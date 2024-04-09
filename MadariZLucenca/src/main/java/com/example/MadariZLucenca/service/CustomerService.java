package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.CustomerEntity;
import com.example.MadariZLucenca.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    public Long createNewCustomer(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setUsername(customer.getUsername());
        entity.setTelNumber(customer.getTelNumber());
        entity.setEmail(customer.getEmail());
        entity.setPassword(customer.getPassword());
        entity.setStreetName(customer.getStreetName());
        entity.setCityName(customer.getCityName());
        entity.setPostCode(customer.getPostCode());
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        customerRepository.save(entity);
        return entity.getCustomerId();
    }


    public Customer customerById(Long id){
        Optional<CustomerEntity> opt = customerRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        CustomerEntity entity = opt.get();
        Customer customer = new Customer();
        customer.setUserId(entity.getCustomerId());
        customer.setUsername(entity.getUsername());
        customer.setTelNumber(entity.getTelNumber());
        customer.setEmail(entity.getEmail());
        customer.setPassword(entity.getPassword());
        customer.setStreetName(entity.getStreetName());
        customer.setCityName(entity.getCityName());
        return customer;
    }
}
