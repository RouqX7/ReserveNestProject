import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Login from './Login';
import SignUp from './SignUp'; 

const AuthenticationPage = () => {
    const [isLogin, setIsLogin] = useState(true);
    const navigate = useNavigate(); 

    const handleLoginSuccess = (userData) => {
        console.log('User logged in:', userData);
        // Redirect to HotelDashboard
        navigate('/hotel-dashboard'); // Redirect using useNavigate
    };

    const toggleFormType = () => {
        setIsLogin(!isLogin);
    };

    return (
        <div>
            {isLogin ? (
                <Login onLoginSuccess={handleLoginSuccess} onToggleFormType={toggleFormType} />
            ) : (
                <SignUp onToggleFormType={toggleFormType} />
            )}
        </div>
    );
};

export default AuthenticationPage;
