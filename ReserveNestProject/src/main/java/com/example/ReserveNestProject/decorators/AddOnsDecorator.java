package com.example.ReserveNestProject.decorators;

import com.example.ReserveNestProject.models.Booking;

public class AddOnsDecorator extends Booking {
    private final Booking decoratedBooking;

    public AddOnsDecorator(Booking decoratedBooking) {
        this.decoratedBooking = decoratedBooking;
    }


    @Override
    public double calculateTotalPrice() {
        double basePrice = decoratedBooking.calculateTotalPrice(); // Get base price
        double addOnsPrice = calculateAddOnsPrice(); // Add price for add-ons
        return basePrice + addOnsPrice; // Total price with add-ons
    }

    public double calculateAddOnsPrice() {
        // Implement the actual logic to calculate the price for add-ons
        double addOnsPrice = 0;

        // Example: Check if breakfast is included and add its price
        if (decoratedBooking.isBreakfastIncluded()) {
            addOnsPrice += 15;  // Example price for breakfast
        }

        // Example: Check if airport pickup is included and add its price
        if (decoratedBooking.isAirportPickupIncluded()) {
            addOnsPrice += 30;  // Example price for airport pickup
        }
        if (decoratedBooking.isRoomUpgrade()) {
            addOnsPrice += 100;  // Example price for room upgrade
        }
        if (decoratedBooking.isLateCheckout()) {
            addOnsPrice += 50;  // Price for late checkout
        }
        if (decoratedBooking.isHasSpecialAmenities()){
            addOnsPrice +=  50;
        }
        if (decoratedBooking.isHasReservedParking()){
            addOnsPrice += 25;
        }
        if (decoratedBooking.isHasSpaAccess()){
            addOnsPrice += 30;
        }
        if (decoratedBooking.isHasDiningOptions()){
            addOnsPrice += 30;
        }


        // Add more conditions for other add-ons as necessary
        // ...

        return addOnsPrice;
    }
}
