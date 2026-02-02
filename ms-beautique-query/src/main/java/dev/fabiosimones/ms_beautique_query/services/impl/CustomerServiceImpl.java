package dev.fabiosimones.ms_beautique_query.services.impl;

import dev.fabiosimones.ms_beautique_query.dtos.customers.CustomerDTO;
import dev.fabiosimones.ms_beautique_query.repositories.CustomerRepository;
import dev.fabiosimones.ms_beautique_query.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> listAllCustomer() {
        try {
            return customerRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Error listing customers, " +e.getMessage());
        }
    }

    @Override
    public List<CustomerDTO> listByNameLikeIgnoreCase(String name) {
        try {
            return customerRepository.findByNameLikeIgnoreCase(name);
        }catch (Exception e){
            throw new RuntimeException("Error listing customer by name, " +e.getMessage());
        }
    }

    @Override
    public List<CustomerDTO> listByEmailLikeIgnoreCase(String email) {
        try {
            return customerRepository.findByEmailLikeIgnoreCase(email);
        }catch (Exception e){
            throw new RuntimeException("Error listing customer by email, " +e.getMessage());
        }
    }
}
