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
        double addOnsPrice = 0;
        if (decoratedBooking.isBreakfastIncluded()) {
            addOnsPrice += 15;
        }
        if (decoratedBooking.isAirportPickupIncluded()) {
            addOnsPrice += 30;
        }
        if (decoratedBooking.isRoomUpgrade()) {
            addOnsPrice += 100;
        }
        if (decoratedBooking.isLateCheckout()) {
            addOnsPrice += 50;
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
        return addOnsPrice;
    }
}
