package com.example.ReserveNestProject.controllers;

import com.example.ReserveNestProject.models.Booking;
import com.example.ReserveNestProject.models.Discount;
import com.example.ReserveNestProject.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/discounts")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @PostMapping("/create")
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount) {
        Discount savedDiscount = discountService.saveDiscount(discount);
        return ResponseEntity.ok(savedDiscount);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        List<Discount> discounts = discountService.getAllDiscounts();
        return ResponseEntity.ok(discounts);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable String id, @RequestBody Discount discount) {
        Discount updatedDiscount = discountService.updateDiscount(id, discount);
        return ResponseEntity.ok(updatedDiscount);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDiscount(@PathVariable String id) {
        discountService.deleteDiscount(id);
        return ResponseEntity.ok("Discount deleted successfully");
    }

    @PostMapping("/customer/{customerId}/discounts")
    public ResponseEntity<List<Discount>> getDiscountsForCustomerAndBooking(@PathVariable String customerId, @RequestBody Booking booking) {
        List<Discount> discounts = discountService.getDiscountsForCustomer(customerId, booking);
        return ResponseEntity.ok(discounts);
    }

    // Additional endpoints as needed
}
