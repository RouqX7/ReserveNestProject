package com.example.ReserveNestProject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Document(collection = "customers")
public class Customer {

    @Id
    private String id;
    private String customerName;
    private String customerEmail;
    private String mobile;
    private String userName;
    private String address;

    public  Customer(){

    }

    public Customer(String id, String customerName, String customerEmail,
                    String mobile, String userName, String address) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.mobile = mobile;
        this.userName = userName;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
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

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
