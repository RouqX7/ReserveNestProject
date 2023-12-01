package com.example.ReserveNestProject.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.ReserveNestProject.models.Booking;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    // Add custom query methods if needed. For example:
     List<Booking> findByCustomerId(String customerId);
}
