// import React, { useState, useEffect } from 'react';
// import axios from 'axios';

// function Customer() {
//     const [customerId, setId] = useState('');
//     const [customerName, setName] = useState("");
//     const [email, setEmail] = useState("");
//     const [ address, setAddress] = useState("");
//     const [mobile, setMobile] = useState("");
//     const [userName, setUserName] = useState("");
//     const [customers, setCustomers] = useState([]);

//     useEffect(() => {
//         Load();
//     }, []);

//     async function Load() {
//         try {
//             const result = await axios.get("http://localhost:8081/api/v1/customer/getAll");
//             setCustomers(result.data);
//         } catch (error) {
//             console.error('Failed to load customers:', error);
//         }
//     }

//     async function save(event) {
//         event.preventDefault();
//         try {
//             await axios.post("http://localhost:8081/api/v1/customer/save", {
//                 customerName,
//                 address,
//                 email,
//                 userName,
//                 mobile
//             });
//             alert("Customer Registration Successfully");
//             setId("");
//             setName("");
//             setEmail("")
//             setAddress("");
//             setUserName("")
//             setMobile("");
//             Load();
//         } catch (err) {
//             alert("Customer Registration Failed: " + err.message);
//         }
//     }

//     async function editCustomer(customer) {
//         setName(customer.customerName);
//         setAddress(customer.address);
//         setMobile(customer.mobile);
//         setEmail(customer.setEmail);
//         setUserName(customer.setUserName);
//         setId(customer._id);
//     }

//     async function DeleteCustomer(customerId) {
//         try {
//             await axios.delete("http://localhost:8081/api/v1/customer/delete/" + customerId);
//             alert("Customer deleted Successfully");
//             Load();
//         } catch (error) {
//             alert("Failed to delete customer: " + error.message);
//         }
//     }

//     async function update(event) {
//         event.preventDefault();
//         try {
//             await axios.put("http://localhost:8081/api/v1/customer/edit/" + customerId, {
//                 customerName,
//                 address,
//                 email,
//                 userName,
//                 mobile
//             });
//             alert("Registration Updated");
//             setId("");
//             setName("");
//             setEmail("")
//             setAddress("");
//             setUserName("")
//             setMobile("");
//             Load();
//         } catch (err) {
//             alert("Customer Update Failed: " + err.message);
//         }
//     }

//     return (
//         <div>
//             <h1>Customer Details</h1>
//             <div className="container mt-4">
//                 <form onSubmit={customerId ? update : save}>
//                     <div className="form-group">
//                         <label htmlFor="customername">Customer Name</label>
//                         <input type="text" className="form-control" id="customername"
//                             value={customerName}
//                             onChange={(e) => setName(e.target.value)}
//                         />
//                     </div>

//                     <div className="form-group">
//                         <label htmlFor="email">Customer Email</label>
//                         <input type="email" className="form-control" id="email"
//                             value={email}
//                             onChange={(e) => setEmail(e.target.value)}
//                         />
//                     </div>

//                     <div className="form-group">
//                         <label htmlFor="address">Customer Address</label>
//                         <input type="text" className="form-control" id="address"
//                             value={address}
//                             onChange={(e) => setAddress(e.target.value)}
//                         />
//                     </div>

//                     <div className="form-group">
//                         <label htmlFor="username">User Name</label>
//                         <input type="text" className="form-control" id="username"
//                             value={userName}
//                             onChange={(e) => setUserName(e.target.value)}
//                         />
//                     </div>

//                     <div className="form-group">
//                         <label htmlFor="mobile">Mobile</label>
//                         <input type="text" className="form-control" id="mobile"
//                             value={mobile}
//                             onChange={(e) => setMobile(e.target.value)}
//                         />
//                     </div>
//                     <button type="submit" className="btn btn-primary mt-4">Register</button>
//                     {customerId && (
//                         <button type="button" className="btn btn-warning mt-4" onClick={update}>Update</button>
//                     )}
//                 </form>
//             </div>
//             <br />
//             <table className="table table-dark" align="center">
//                 <thead>
//                     <tr>
//                         <th scope="col">Customer Name</th>
//                         <th scope="col">Customer Email</th>
//                         <th scope="col">Customer Address</th>
//                         <th scope="col">User Name</th>
//                         <th scope="col">Customer Mobile</th>
//                         <th scope="col">Option</th>
//                     </tr>
//                 </thead>
//                 <tbody>
//                     {customers.map((customer) => (
//                         <tr key={customer.id}>
//                             <td>{customer.customerName}</td>
//                             <td>{customer.email}</td>
//                             <td>{customer.address}</td>
//                             <td>{customer.userName}</td>
//                             <td>{customer.mobile}</td>
//                             <td>
//                                 <button type="button" className="btn btn-warning" onClick={() => editCustomer(customer)}>Edit</button>
//                                 <button type="button" className="btn btn-danger" onClick={() => DeleteCustomer(customer.id)}>Delete</button>
//                             </td>
//                         </tr>
//                     ))}
//                 </tbody>
//             </table>
//         </div>
//     );
// }

// export default Customer;
