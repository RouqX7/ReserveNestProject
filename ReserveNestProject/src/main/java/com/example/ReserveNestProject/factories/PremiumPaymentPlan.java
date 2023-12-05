package com.example.ReserveNestProject.factories;

import com.example.ReserveNestProject.models.Booking;

public class PremiumPaymentPlan implements PaymentPlan {
    private boolean success;

    @Override
    public void processPayment(Booking booking) {
        double basePrice = booking.getTotalAmount();
        double premiumPrice = basePrice * 1.10;
        // Not going to fully finish this because were not using money yet

    }

    @Override
    public boolean isSuccessful() {
        return success;
    }
}