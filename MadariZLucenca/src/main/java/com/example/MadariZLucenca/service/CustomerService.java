package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LoginRepository loginRepository;

    public Long createNewCustomer(Customer customer, String roleName) {
        CustomerEntity entity = new CustomerEntity();
        entity.setUsername(customer.getUsername());
        entity.setTelNumber(customer.getTelNumber());
        entity.setEmail(customer.getEmail());
        entity.setPassword(customer.getPassword());
        entity.setPasswordHash(passwordEncoder.encode(customer.getPassword()));
        entity.setStreetName(customer.getStreetName());
        entity.setCityName(customer.getCityName());
        entity.setPostCode(customer.getPostCode());
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());

        RoleEntity customerRole = roleRepository.findByName(roleName);

        if (customerRole != null) {
            entity.setRoleName(roleName);
        } else {
            System.out.println("chyba s rolami pri customer :/");
        }

        customerRepository.save(entity);

        LoginService test = new LoginService(loginRepository, roleRepository);
        test.createNewLogin(customer, entity);

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


    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
