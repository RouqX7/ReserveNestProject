package com.example.ReserveNestProject.repo;

import com.example.ReserveNestProject.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends MongoRepository<Customer,String> {
    Customer findByUserName(String userName);
    Customer findByEmail(String email);


}
