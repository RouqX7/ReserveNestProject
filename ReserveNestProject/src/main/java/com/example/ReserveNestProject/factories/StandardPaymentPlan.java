package com.example.ReserveNestProject.factories;

import com.example.ReserveNestProject.models.Booking;

public class StandardPaymentPlan implements PaymentPlan {
    private boolean success;

    @Override
    public void processPayment(Booking booking) {
        double basePrice = booking.getTotalAmount();
        // Process payment based on basePrice
        // Set success based on payment outcome
        // Not going to fully finish this because were not using andy kind of money here
    }

    @Override
    public boolean isSuccessful() {
        return success;
    }
}