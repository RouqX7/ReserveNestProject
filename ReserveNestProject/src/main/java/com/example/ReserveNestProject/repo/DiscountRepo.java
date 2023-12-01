package com.example.ReserveNestProject.repo;

import com.example.ReserveNestProject.models.Discount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiscountRepo extends MongoRepository<Discount, String> {
    // You can add query methods if needed
}
