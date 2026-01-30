package com.devfabiosimones.beutique.services.impl;

import com.devfabiosimones.beutique.dtos.CustomerDTO;
import com.devfabiosimones.beutique.entities.CustomerEntity;
import com.devfabiosimones.beutique.repositories.CustomerRepository;
import com.devfabiosimones.beutique.services.BrokerService;
import com.devfabiosimones.beutique.services.CustomerService;
import com.devfabiosimones.beutique.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BrokerService brokerService;

    private final ConverterUtil<CustomerEntity, CustomerDTO> converterUtil = new ConverterUtil<>(CustomerEntity.class, CustomerDTO.class);

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
//        CustomerEntity customerEntity = CustomerEntity.builder()
//                .name(customerDTO.getName())
//                .email(customerDTO.getEmail())
//                .phone(customerDTO.getPhone())
//                .build();
//
//        CustomerEntity newCustomerEntity = customerRepository.save(customerEntity);
//
//        return CustomerDTO.builder()
//                .id(newCustomerEntity.getId())
//                .name(newCustomerEntity.getName())
//                .email(newCustomerEntity.getEmail())
//                .phone(newCustomerEntity.getPhone())
//                .build();
        CustomerEntity customerEntity = converterUtil.convertToSource(customerDTO);
        CustomerEntity newCustomerEntity = customerRepository.save(customerEntity);
        sendCustomerToQueue(newCustomerEntity);

        return converterUtil.convertToTarget(newCustomerEntity);

    }

    @Override
    public void delete(Long id) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);
        if(customerEntityOptional.isEmpty()){
            throw new RuntimeException("Customer not found.");
        }
        customerRepository.delete(customerEntityOptional.get());
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(customerDTO.getId());
        if(customerEntityOptional.isEmpty()){
            throw new RuntimeException("Customer not found.");
        }
        CustomerEntity customerEntity = converterUtil.convertToSource(customerDTO);

        customerEntity.setAppointments(customerEntityOptional.get().getAppointments());
        customerEntity.setCreatedAt(customerEntityOptional.get().getCreatedAt());
        customerEntity.setUpdatedAt(LocalDateTime.now());
        CustomerDTO updatedCustomerDTO = converterUtil.convertToTarget(customerRepository.save(customerEntity));
        sendCustomerToQueue(customerEntity);

        return updatedCustomerDTO;
    }

    private void sendCustomerToQueue(CustomerEntity customerEntity){
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
                .phone(customerEntity.getPhone())
                .build();

        brokerService.send("customer", customerDTO);

    }
}
