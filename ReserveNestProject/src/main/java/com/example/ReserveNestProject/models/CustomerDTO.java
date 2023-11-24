package com.example.ReserveNestProject.models;

public class CustomerDTO {
    private String customerName;
    private String email;
    private String mobile;
    private String userName;
    private String address;
    private String password;

    // Standard getters and setters


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

    // Optionally, you can add a method to convert DTO to Customer model
    public Customer toCustomer() {
        return new Customer(null, customerName, email, mobile, userName, address, password);
    }
}
