package com.devfabiosimones.beutique.services;

import com.devfabiosimones.beutique.dtos.CustomerDTO;
import com.devfabiosimones.beutique.entities.CustomerEntity;

public interface CustomerService {
    CustomerDTO create(CustomerDTO customerDTO);
}
