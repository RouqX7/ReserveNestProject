import React from "react";

const BookingList = ({ bookingDetails }) => {
    return (
        <div>
            <h3>Booking Summary</h3>
            <p>Room Upgrade: {bookingDetails.isRoomUpgrade ? "Yes" : "No"}</p>
            <p>Late Checkout: {bookingDetails.isLateCheckout ? "Yes" : "No"}</p>
            <p>Breakfast Included: {bookingDetails.isBreakfastIncluded ? "Yes" : "No"}</p>
            <p>Airport Pickup: {bookingDetails.isAirportPickupIncluded ? "Yes" : "No"}</p>
            <p>Special Amenities: {bookingDetails.hasSpecialAmenities ? "Yes" : "No"}</p>
            <p>Reserved Parking: {bookingDetails.hasReservedParking ? "Yes" : "No"}</p>
            <p>Spa Access: {bookingDetails.hasSpaAccess ? "Yes" : "No"}</p>
            <p>Dining Options: {bookingDetails.hasDiningOptions ? "Yes" : "No"}</p>
            <p>Total Amount: {bookingDetails.totalAmount}</p>
        </div>
    );
};

export default BookingList;
