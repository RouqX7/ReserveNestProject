// BookingForm.js
import React, { useState } from 'react';

const BookingForm = ({ onSubmit }) => {
    const [addOns, setAddOns] = useState({
        roomUpgrade: false,
        lateCheckout: false,
        airportIncluded: false,
        breakfastIncluded: false,
        specialAmenities: false,
        reservedParking: false,
        spaAccess: false,
        diningOptions: false
    });

    const [specialRequests, setSpecialRequests] = useState("");


    const handleChange = (e) => {
        setAddOns({ ...addOns, [e.target.name]: e.target.checked });
    };
    const handleSpecialRequestsChange = (e) => {
        setSpecialRequests(e.target.value);
    };

   const handleSubmit = (e) => {
    e.preventDefault();
    const flattenedAddOns = {
        isRoomUpgrade: addOns.roomUpgrade,
        isLateCheckout: addOns.lateCheckout,
        isAirportPickupIncluded: addOns.airportIncluded,
        isBreakfastIncluded: addOns.breakfastIncluded,
        hasSpecialAmenities: addOns.specialAmenities,
        hasReservedParking: addOns.reservedParking,
        hasSpaAccess: addOns.spaAccess,
        hasDiningOptions: addOns.diningOptions
    };
    
    onSubmit(flattenedAddOns, specialRequests);
};


    return (
        <form onSubmit={handleSubmit}>
            <h3>Select Add-Ons</h3>
            <label>
                <input
                    type="checkbox"
                    name="roomUpgrade"
                    checked={addOns.roomUpgrade}
                    onChange={handleChange}
                />
                Room Upgrade (+$50)
            </label>
            <label>
                <input
                    type="checkbox"
                    name="breakfastIncluded"
                    checked={addOns.breakfastIncluded}
                    onChange={handleChange}
                />
                Breakfast (+$20)
            </label>
            
            <label>
                <input
                    type="checkbox"
                    name="lateCheckout"
                    checked={addOns.lateCheckout}
                    onChange={handleChange}
                />
                Late Checkout (+$20)
            </label>
            <label>
                <input
                    type="checkbox"
                    name="airportIncluded"
                    checked={addOns.airportIncluded}
                    onChange={handleChange}
                />
                Handle Airport Pickup (+$30)
            </label>
            
            <label>
                <input
                    type="checkbox"
                    name="specialAmenities"
                    checked={addOns.specialAmenities}
                    onChange={handleChange}
                />
                Special Amenities (+$50)
            </label>
            <label>
                <input
                    type="checkbox"
                    name="reservedParking"
                    checked={addOns.reservedParking}
                    onChange={handleChange}
                />
                Resrved Parking (+$25)
            </label>
            <label>
                <input
                    type="checkbox"
                    name="spaAccess"
                    checked={addOns.spaAccess}
                    onChange={handleChange}
                />
                Spa Access (+$30)
            </label>
            <label>
                <input
                    type="checkbox"
                    name="diningOptions"
                    checked={addOns.diningOptions}
                    onChange={handleChange}
                />
                DiningOptions(+$20)
            </label>
            <label>
                    Special Requests:
                    <textarea
                        value={specialRequests}
                        onChange={handleSpecialRequestsChange}
                    />
                </label>
            <button type="submit">Add to Booking</button>
        </form>
    );
};

export default BookingForm;
