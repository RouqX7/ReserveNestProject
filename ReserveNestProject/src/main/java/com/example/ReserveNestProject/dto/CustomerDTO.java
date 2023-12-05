package com.example.ReserveNestProject.dto;

import com.example.ReserveNestProject.models.Booking;
import com.example.ReserveNestProject.models.Customer;

import java.util.List;

public class CustomerDTO {
    private String id;


    private String customerName;
    private String email;
    private String mobile;
    private String userName;
    private String address;
    private String password;
    private List<Booking> previousBookings;


    public CustomerDTO(String customerName, String email,String id) {
        this.customerName = customerName;
        this.email = email;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String _id) {
        this.id = _id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Booking> getPreviousBookings() {
        return previousBookings;
    }

    public void setPreviousBookings(List<Booking> previousBookings) {
        this.previousBookings = previousBookings;
    }

    public Customer toCustomer() {
        return new Customer(null, customerName, email, mobile, userName, address, password,previousBookings);
    }


}
