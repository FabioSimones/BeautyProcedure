package dev.fabiosimones.ms_sync.repositories;

import dev.fabiosimones.ms_sync.dtos.customers.CustomerDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerDTO, Long> {
}
