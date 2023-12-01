package com.example.ReserveNestProject.controllers;

import com.example.ReserveNestProject.dto.CustomerDTO;
import com.example.ReserveNestProject.models.Customer;
import com.example.ReserveNestProject.dto.LoginDTO;
import com.example.ReserveNestProject.repo.CustomerRepo;
import com.example.ReserveNestProject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerService customerService;

/*
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDetails) {
        Optional<Customer> customerOpt = customerRepo.findByEmail(loginDetails.getEmail());

        // If the customer wasn't found by email, try by username
        if (customerOpt.isEmpty()) {
            customerOpt = customerRepo.findByUserName(loginDetails.getUserName());
        }

        // Now check if the customer is present and the password matches
        if (customerOpt.isPresent() && customerOpt.get().getPassword().equals(loginDetails.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
*/

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Optional<Customer> customerOpt = customerService.findByUsername(loginDTO.getUserName());

        // Fall back to email if username not found
        if (customerOpt.isEmpty()) {
            customerOpt = customerRepo.findByEmail(loginDTO.getEmail());
        }

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (customer.getPassword().equals(loginDTO.getPassword())) {
                // Ensure that the correct fields are mapped to the DTO
                CustomerDTO customerDTO = new CustomerDTO(customer.getCustomerName(), customer.getEmail(), customer.getId());
                return ResponseEntity.ok(customerDTO);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }





}
