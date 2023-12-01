import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './pages/Account Creation/Login';
import Signup from './pages/Account Creation/SignUp';
import AdminRooms from './pages/Admin/AdminRooms';
import HotelDashboard from './pages/Hotel/HotelDashboard';
import BookingPage from './pages/Hotel/BookingPage';
import DiscountManagement from './pages/Admin/DiscountManagement';
import BookingConfirmation from './pages/Hotel/BookingConfirmation';

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        const loggedInUser = localStorage.getItem('isLoggedIn');
        if (loggedInUser === 'true') {
            setIsLoggedIn(true);
        }
    }, []);

    const handleLogin = () => {
        setIsLoggedIn(true);
        localStorage.setItem('isLoggedIn', 'true');
    };

    const handleLogout = () => {
        setIsLoggedIn(false);
        localStorage.removeItem('isLoggedIn');
    };

    return (
        <Router>
        <Routes>
            <Route path="/signup" element={<Signup />} />
            <Route path="/login" element={<Login onLoginSuccess={handleLogin} />} />
            <Route path="/hotel-dashboard" element={isLoggedIn ? <HotelDashboard onLogout={handleLogout} /> : <Login onLoginSuccess={handleLogin} />} />
            <Route path="/book/:roomid/:fromDate/:toDate" element={<BookingPage />} />
            <Route path="/booking-confirmation" element={<BookingConfirmation />} />
            <Route path="/admin-rooms" element={isLoggedIn ? <AdminRooms /> : <Login onLoginSuccess={handleLogin} />} />
            <Route path="/admin-discounts" element={isLoggedIn ? <DiscountManagement /> : <Login onLoginSuccess={handleLogin} />} />
            <Route path="/" element={isLoggedIn ? <HotelDashboard onLogout={handleLogout} /> : <Login onLoginSuccess={handleLogin} />} />
        </Routes>
    </Router>
    );
}

export default App;
