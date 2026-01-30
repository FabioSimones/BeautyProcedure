package com.devfabiosimones.beutique.repositories;

import com.devfabiosimones.beutique.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
