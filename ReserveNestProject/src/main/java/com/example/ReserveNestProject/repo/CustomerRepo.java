package com.example.ReserveNestProject.repo;

import com.example.ReserveNestProject.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepo extends MongoRepository<Customer, String> {
    Optional<Customer> findByUserName(String userName);
    Optional<Customer> findByEmail(String email);
}

