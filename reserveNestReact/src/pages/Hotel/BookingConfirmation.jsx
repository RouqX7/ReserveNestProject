import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom'; // Updated import
import axios from 'axios';

const BookingConfirmation = () => {
    const location = useLocation();
    const navigate = useNavigate(); 
    const bookingDetails = location.state?.bookingDetails;

    const handleCancelBooking = async () => {
        try {
            await axios.post(`/api/bookings/cancel/${bookingDetails.id}`);
            // Redirect to a different page or update state to show cancellation
            navigate('/booking-cancelled'); 
        } catch (error) {
            // Handle error (show message or log)
            console.error('Error cancelling booking:', error);
        }
    };

    return (
        <div>
            <h1>Booking Confirmed</h1>
            <p>Your booking has been successfully confirmed.</p>

            {bookingDetails && (
                <div>
                <p><strong>Booking ID:</strong> {bookingDetails.id}</p>
                    <p><strong>Customer Name:</strong> {bookingDetails.customerName}</p>
                    <p><strong>Room Name:</strong> {bookingDetails.roomName}</p>
                    <p><strong>Check-In Date:</strong> {bookingDetails.checkInDate}</p>
                    <p><strong>Check-Out Date:</strong> {bookingDetails.checkOutDate}</p>
                    <p><strong>Total Days:</strong> {bookingDetails.totalDays}</p>
                    <p><strong>Total Amount:</strong> ${bookingDetails.totalAmount}</p>
                    <p><strong>Special Requests:</strong> {bookingDetails.specialRequests || 'None'}</p>
                    <p><strong>Breakfast Included:</strong> {bookingDetails.isBreakfastIncluded ? 'Yes' : 'No'}</p>
                    <p><strong>Airport Pickup:</strong> {bookingDetails.isAirportPickupIncluded ? 'Yes' : 'No'}</p>
                    {/* Add more details as needed */}
                </div>
            )}

            <button onClick={handleCancelBooking}>Cancel Booking</button>
        </div>
    );
};

export default BookingConfirmation;
