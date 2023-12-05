import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';


const Login = ({ onLoginSuccess }) => {
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
    email: "",
  });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setCredentials({ ...credentials, [e.target.name]: e.target.value });
    setError(""); // Clear error when user starts typing again
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(''); // Clear previous errors
    try {
        const response = await axios.post('http://localhost:8081/api/auth/login', credentials);
        console.log("Login response: ", response); // Log the response

       if (response.data && response.data.id) {
            localStorage.setItem("customerDetails", JSON.stringify(response.data));
            localStorage.setItem("isLoggedIn", "true");
            if (onLoginSuccess) {
                onLoginSuccess(response.data);
            }
            navigate("/"); // Navigate to dashboard or other appropriate page
        } else {
            setError("Login failed. Please check your credentials.");
        }
    } catch (error) {
        const serverErrorMessage = error.response?.data?.message;
        setError(serverErrorMessage || "Login failed. Please try again.");
    }
};

  return (
    <div>
      <h2>Login</h2>
      {error && <div style={{ color: "red" }}>{error}</div>}
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="usernameInput">Username</label>
          <input
            id="usernameInput"
            type="text"
            name="username"
            autoComplete="username"
            value={credentials.username}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="emailInput">Email</label>
          <input
            id="emailInput"
            type="email"
            name="email"
            autoComplete="email"
            value={credentials.email}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="passwordInput">Password</label>
          <input
            id="passwordInput"
            type="password"
            name="password"
            autoComplete="current-password"
            value={credentials.password}
            onChange={handleChange}
          />
        </div>

        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default Login;
