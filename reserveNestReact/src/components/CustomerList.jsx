// in src/components/CustomerList.js
import React from 'react';

const CustomerList = ({ customers, onEdit, onDelete }) => {
        return (
            <table className="table table-dark">
                <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">User Name</th>
                        <th scope="col">Password</th>
                        <th scope="col">Address</th>
                        <th scope="col">Mobile</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {customers.map(customer => (
                        <tr key={customer._id}>
                            <td>{customer.customerName}</td>
                            <td>{customer.password}</td>
                            <td>{customer.email}</td>
                            <td>{customer.address}</td>
                            <td>{customer.userName}</td>
                            <td>{customer.mobile}</td>
                            <td>
                                <button onClick={() => onEdit(customer)}>Edit</button>
                                <button onClick={() => onDelete(customer._id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        );
    };

export default CustomerList;
