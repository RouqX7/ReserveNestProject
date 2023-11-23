package com.example.ReserveNestProject.controllers;

import com.example.ReserveNestProject.models.Customer;
import com.example.ReserveNestProject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping(value = "/save")
    private String saveCustomer(@RequestBody Customer customers){

        customerService.saveOrUpdate(customers);
        return  customers.getId();
    }

    @GetMapping(value = "/getAll")
    private Iterable<Customer>getCustomer(){


        return  customerService.listAll();
    }

    @PutMapping(value = "/edit/{id}")
    private Customer update(@RequestBody Customer customer,@PathVariable(name = "id")String _id) {


        customer.setId(_id);
        customerService.saveOrUpdate(customer);
        return customer;

    }

    @DeleteMapping("/delete/{id}")
     private void deleteCustomer(@PathVariable("id") String _id){

        customerService.deleteCustomer(_id);
        }
     @RequestMapping("/customer/{id}")
        private Customer getCustomers(@PathVariable(name = "id")String customerId){

        return  customerService.getCustomerById(customerId);
        }





}
