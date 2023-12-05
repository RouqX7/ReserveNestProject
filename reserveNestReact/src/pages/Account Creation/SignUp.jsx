import React, { useState } from 'react';
import axios from 'axios';
import CustomerForm from '../../components/CustomerForm';
const Signup = () => {
    const [customer, setCustomer] = useState({
        customerName: '',
        email: '',
        address: '',
        userName: '',
        mobile: '',
        password: ''
    });

    const handleChange = (e) => {
        setCustomer({ ...customer, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8081/api/customers/create-account', customer);
            alert("Account created successfully");
            setCustomer({
                customerName: '',
                email: '',
                address: '',
                userName: '',
                mobile: '',
                password: ''
            });
        } catch (err) {
            alert("Failed to create account: " + err.message);
        }
    };

    return (
        <div>
            <h1>Signup for New Customer</h1>
            <CustomerForm onSave={handleSubmit} customerData={customer} onInputChange={handleChange} />
        </div>
    );
};

export default Signup;

