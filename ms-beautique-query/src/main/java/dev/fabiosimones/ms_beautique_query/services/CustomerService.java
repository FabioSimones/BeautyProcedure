package dev.fabiosimones.ms_beautique_query.services;

import dev.fabiosimones.ms_beautique_query.dtos.customers.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> listAllCustomer();
    List<CustomerDTO> listByNameLikeIgnoreCase(String name);
    List<CustomerDTO> listByEmailLikeIgnoreCase(String email);
}
