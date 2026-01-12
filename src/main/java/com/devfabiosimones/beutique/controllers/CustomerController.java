package com.devfabiosimones.beutique.controllers;

import com.devfabiosimones.beutique.dtos.CustomerDTO;
import com.devfabiosimones.beutique.entities.CustomerEntity;
import com.devfabiosimones.beutique.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.create(customerDTO));
    }
}
