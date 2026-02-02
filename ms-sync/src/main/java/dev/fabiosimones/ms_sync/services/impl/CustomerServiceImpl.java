package dev.fabiosimones.ms_sync.services.impl;

import dev.fabiosimones.ms_sync.dtos.customers.CustomerDTO;
import dev.fabiosimones.ms_sync.repositories.CustomerRepository;
import dev.fabiosimones.ms_sync.services.CustomerService;
import dev.fabiosimones.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void savedCustomer(CustomerDTO customerDTO) {
        try{
            SyncLogger.info("Saving customer: " + customerDTO.getId());
            customerRepository.save(customerDTO);
        } catch (Exception e) {
            SyncLogger.error("Error saving customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
