// in src/pages/CustomerPage.js
import React, { useState, useEffect } from "react";
import axios from "axios";
import CustomerForm from "../../components/CustomerForm";
import CustomerList from "../../components/CustomerList";

const CustomerPage = () => {
  const [customers, setCustomers] = useState([]);
  const [editingCustomer, setEditingCustomer] = useState(null);

  useEffect(() => {
    loadCustomers();
  }, []);

  const loadCustomers = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8081/api/v1/customer/getAll"
      );
      setCustomers(response.data);
    } catch (error) {
      console.error("Failed to load customers:", error);
    }
  };

  const saveCustomer = async (customer) => {
    try {
      const response = await axios.post(
        "http://localhost:8081/api/v1/customer/save",
        customer
      );
      alert("Customer Registration Successfully");
      setEditingCustomer(null); // Reset editing customer
      loadCustomers(); // Reload the customer list
    } catch (err) {
      alert("Customer Registration Failed: " + err.message);
    }
  };

  const deleteCustomer = async (customerId) => {
    try {
      await axios.delete(
        `http://localhost:8081/api/v1/customer/delete/${customerId}`
      );
      alert("Customer deleted Successfully");
      loadCustomers();
    } catch (error) {
      alert("Failed to delete customer: " + error.message);
    }
  };

  const updateCustomer = async (customer) => {
    try {
      await axios.put(
        `http://localhost:8081/api/v1/customer/edit/${customer._id}`,
        customer
      );
      alert("Customer Updated Successfully");
      setEditingCustomer(null); // Reset editing customer
      loadCustomers(); // Reload the customer list
    } catch (err) {
      alert("Customer Update Failed: " + err.message);
    }
  };

  return (
    <div>
      <h1>Customer Details</h1>
      <CustomerForm
        onSave={editingCustomer ? updateCustomer : saveCustomer}
        customerData={editingCustomer}
      />
      <CustomerList
        customers={customers}
        onEdit={setEditingCustomer}
        onDelete={deleteCustomer}
      />
    </div>
  );
};
export default CustomerPage;
