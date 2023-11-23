package com.example.ReserveNestProject.services;


import com.example.ReserveNestProject.models.Customer;
import com.example.ReserveNestProject.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo repo;


    public void saveOrUpdate(Customer customers) {

        repo.save(customers);
    }

    public Iterable<Customer> listAll() {

        return this.repo.findAll();
    }


    public void deleteCustomer(String id) {

        repo.deleteById(id);
    }

    public Customer getCustomerById(String customerId){

        return repo.findById(customerId).get();

    }
}
