package com.example.ReserveNestProject.controllers;

import com.example.ReserveNestProject.models.Customer;
import com.example.ReserveNestProject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping(value ="/save")
    private String saveCustomer(@RequestBody Customer customers){

        customerService.saveOrUpdate(customers);
        return  customers.getId();
    }

    @GetMapping(value ="/getAll")
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        try {
            Iterable<Customer> customers = customerService.listAll();
            if (!customers.iterator().hasNext()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/edit/{id}")
    private Customer update(@RequestBody Customer customer,@PathVariable(name = "id")String id) {

        customer.setId(id);
        customerService.saveOrUpdate(customer);
        return customer;

    }

    @DeleteMapping("/delete/{id}")
     private void deleteCustomer(@PathVariable("id") String id){

        customerService.deleteCustomer(id);
        }
     @RequestMapping("/search/{id}")
        private Customer getCustomers(@PathVariable(name = "id")String customerId){

        return  customerService.getCustomerById(customerId);
        }
}
