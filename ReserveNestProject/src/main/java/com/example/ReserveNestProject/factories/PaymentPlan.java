package com.example.ReserveNestProject.factories;

import com.example.ReserveNestProject.models.Booking;

public interface PaymentPlan {
    void processPayment(Booking booking);
    boolean isSuccessful();

}
