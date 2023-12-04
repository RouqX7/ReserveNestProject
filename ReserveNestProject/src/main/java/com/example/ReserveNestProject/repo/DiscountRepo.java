package com.example.ReserveNestProject.repo;

import com.example.ReserveNestProject.models.Discount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepo extends MongoRepository<Discount, String> {
}
