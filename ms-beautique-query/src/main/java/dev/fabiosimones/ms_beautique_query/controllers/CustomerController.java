package dev.fabiosimones.ms_beautique_query.controllers;

import dev.fabiosimones.ms_beautique_query.dtos.customers.CustomerDTO;
import dev.fabiosimones.ms_beautique_query.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> listAllCustomers(){
        return ResponseEntity.ok(customerService.listAllCustomer());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CustomerDTO>> listByName(@PathVariable String name){
        return ResponseEntity.ok(customerService.listByNameLikeIgnoreCase(name));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<CustomerDTO>> listByEmail(@PathVariable String email){
        return ResponseEntity.ok(customerService.listByEmailLikeIgnoreCase(email));
    }
}
