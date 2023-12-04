package com.example.ReserveNestProject.controllers;

import com.example.ReserveNestProject.models.Customer;
import com.example.ReserveNestProject.dto.CustomerDTO;
import com.example.ReserveNestProject.dto.LoginDTO;
import com.example.ReserveNestProject.observer.EmailNotificationListener;
import com.example.ReserveNestProject.services.CustomerService;
import com.example.ReserveNestProject.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerAccountController {

    private final ValidationService validationService;
    private final CustomerService customerService;
    @Autowired
    private EmailNotificationListener emailNotificationListener;


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
        customerService.saveCustomer(newCustomer);  // Save the new customer to the database Customer();
        emailNotificationListener.sendEmailNotification(newCustomer.getEmail(), "Welcome to Our Service!");


        return ResponseEntity.ok("Account created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Optional<Customer> customerOptional = customerService.findByUsername(loginDTO.getUserName());

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (customer.getPassword().equals(loginDTO.getPassword())) {
                // Assuming CustomerDTO has a constructor accepting username, password, and email
                CustomerDTO customerDTO = new CustomerDTO(customer.getCustomerName(), customer.getEmail(), customer.getId());
                return ResponseEntity.ok(customerDTO);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }






    // Other customer account-related endpoints can be added here
}
