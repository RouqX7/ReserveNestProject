package com.example.ReserveNestProject.controllers;

import com.example.ReserveNestProject.models.Customer;
import com.example.ReserveNestProject.models.CustomerDTO;
import com.example.ReserveNestProject.services.CustomerService;
import com.example.ReserveNestProject.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerAccountController {

    private final ValidationService validationService;
    private final CustomerService customerService;


    @Autowired
    public CustomerAccountController(ValidationService validationService, CustomerService customerService) {
        this.validationService = validationService;
        this.customerService = customerService;
    }

    @PostMapping("/create-account")
    public ResponseEntity<String> createAccount(@RequestBody CustomerDTO customerDTO) {
        if (!validationService.isValidEmail(customerDTO.getEmail()) ||
                !validationService.isValidUsername(customerDTO.getUserName()) ||
                !validationService.isValidPassword(customerDTO.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid customer details");
        }

        Customer newCustomer = customerDTO.toCustomer();
        customerService.saveCustomer(newCustomer);  // Save the new customer to the databaseoCustomer();
        // Save newCustomer to database
        // ...

        return ResponseEntity.ok("Account created successfully");
    }


    // Other customer account-related endpoints can be added here
}
