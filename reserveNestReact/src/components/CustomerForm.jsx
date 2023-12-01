// src/components/CustomerForm.js
import React, { useState, useEffect } from "react";
import axios from 'axios'; // Import axios for API calls

const CustomerForm = ({ onSave, initialCustomerData, formType, onToggleFormType }) => {
  const [customer, setCustomer] = useState({
    customerName: "",
    email: "",
    address: "",
    userName: "",
    mobile: "",
    password: "",
  });

  // If customerData is provided, initialize form with this data
  useEffect(() => {
    if (initialCustomerData) {
      setCustomer(initialCustomerData);
    }
  }, [initialCustomerData]);

  const handleChange = (e) => {
    setCustomer({ ...customer, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Call the onSave function passed as prop which will handle the API call
      await onSave(customer);
    } catch (error) {
      console.error('Error submitting form:', error);
    }
  };

  return (
    <div className="row justify content-center mt-5">
    <div className="col-md-5">


    <form onSubmit={handleSubmit}>
      {formType === 'signup' && (
        <div className="form-group">
          <label htmlFor="customerName">Name</label>
          <input
            name="customerName"
            type="text"
            className="form-control"
            value={customer.customerName}
            onChange={handleChange}
          />
        </div>
      )}
      <div className="form-group">
        <label htmlFor="email">Email</label>
        <input
          name="email"
          type="email"
          className="form-control"
          value={customer.email}
          onChange={handleChange}
        />
      </div>
      <div className="form-group">
        <label htmlFor="userName">UserName</label>
        <input
          name="userName"
          type="text"
          className="form-control"
          value={customer.userName}
          onChange={handleChange}
        />
      </div>
      <div className="form-group">
        <label htmlFor="password">Password</label>
        <input
          name="password"
          type="password"
          className="form-control"
          value={customer.password}
          onChange={handleChange}
        />
      </div>
      {formType === 'signup' && (
        <div className="form-group">
          <label htmlFor="address">Address</label>
          <input
            name="address"
            type="text"
            className="form-control"
            value={customer.address}
            onChange={handleChange}
          />
        </div>
      )}
      {formType === 'signup' && (
        <div className="form-group">
          <label htmlFor="mobile">Mobile</label>
          <input
            name="mobile"
            type="text"
            className="form-control"
            value={customer.mobile}
            onChange={handleChange}
          />
        </div>
      )}
      <button type="submit" className="btn btn-primary mt-4">
        {formType === 'signup' ? 'Sign Up' : 'Login'}
      </button>
      <div className="mt-3" onClick={onToggleFormType} style={{ cursor: 'pointer', color: 'blue', textAlign: 'center' }}>
        {formType === 'signup' ? 'Already have an account? Log in' : "Don't have an account? Sign up"}
      </div>
    </form>    
    </div>
    </div>
  );
};

export default CustomerForm;
