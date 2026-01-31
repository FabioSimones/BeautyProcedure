package dev.fabiosimones.ms_sync.services;

import dev.fabiosimones.ms_sync.dtos.customers.CustomerDTO;

public interface CustomerService {
    void savedCustomer(CustomerDTO customerDTO);
}
