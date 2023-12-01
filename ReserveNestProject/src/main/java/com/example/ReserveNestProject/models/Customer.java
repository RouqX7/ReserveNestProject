package com.example.ReserveNestProject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "customers")
public class Customer {

    @Id
    private String id;
    private String customerName;
    private String email;
    private String mobile;
    private String userName;
    private String address;
    private String password;
    private List<Booking> previousBookings;


    public  Customer(){

    }

    public Customer(String id, String customerName, String email, String mobile, String userName, String address, String password, List<Booking> previousBookings) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.mobile = mobile;
        this.userName = userName;
        this.address = address;
        this.password = password;
        this.previousBookings = new ArrayList<>();;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Customer{" +
                "_id='" + id + '\'' +
                ", customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
