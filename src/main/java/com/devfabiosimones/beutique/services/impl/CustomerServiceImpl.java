package com.devfabiosimones.beutique.services.impl;

import com.devfabiosimones.beutique.dtos.CustomerDTO;
import com.devfabiosimones.beutique.entities.CustomerEntity;
import com.devfabiosimones.beutique.repositories.CustomerRepository;
import com.devfabiosimones.beutique.services.CustomerService;
import com.devfabiosimones.beutique.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

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

        return converterUtil.convertToTarget(newCustomerEntity);

    }
}
