package com.example.ReserveNestProject.services;

import com.example.ReserveNestProject.models.Customer;
import com.example.ReserveNestProject.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo; // Use final for injected dependencies

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void saveOrUpdate(Customer customer) {
        customerRepo.save(customer); // Save or update the customer
    }

    public Iterable<Customer> listAll() {
        return customerRepo.findAll(); // Retrieve all customers
    }

    public void  deleteCustomer(String id) {
        customerRepo.deleteById(id); // Delete a customer by ID
    }

    public Customer getCustomerById(String customerId) {
        return customerRepo.findById(customerId).orElse(null); // Get a customer by ID
    }

    // Newly added method to save a new customer
    public void saveCustomer(Customer newCustomer) {
        customerRepo.save(newCustomer);
    }

    public Optional<Customer> findByUsername(String username) {
        return customerRepo.findByUserName(username);
    }
}
