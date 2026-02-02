package dev.fabiosimones.ms_beautique_query.repositories;

import dev.fabiosimones.ms_beautique_query.dtos.customers.CustomerDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerDTO, Long> {
}
