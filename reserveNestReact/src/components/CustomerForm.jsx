// in src/components/CustomerForm.js
import React, { useState, useEffect } from "react";

const CustomerForm = ({ onSave, customerData }) => {
  const [customer, setCustomer] = useState({
    customerName: "",
    email: "",
    address: "",
    userName: "",
    mobile: "",
    password: "",
  });

  useEffect(() => {
    if (customerData) {
      setCustomer(customerData);
    }
  }, [customerData]);

  const handleChange = (e) => {
    setCustomer({ ...customer, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave(customer);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="form-group">
        <label htmlFor="customerName">Customer Name</label>
        <input
          name="customerName"
          type="text"
          className="form-control"
          value={customer.customerName}
          onChange={handleChange}
        />
      </div>
      <div className="form-group">
        <label htmlFor="email">Customer Email</label>
        <input
          name="email"
          type="email"
          className="form-control"
          value={customer.email}
          onChange={handleChange}
        />
        <div className="form-group">
          <label htmlFor="userName">User Name</label>
          <input
            name="userName"
            type="text"
            className="form-control"
            value={customer.userName}
            onChange={handleChange}
          />
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
        </div>
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
      </div>
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
      <button type="submit" className="btn btn-primary mt-4">
        Save
      </button>
    </form>
  );
};

export default CustomerForm;
