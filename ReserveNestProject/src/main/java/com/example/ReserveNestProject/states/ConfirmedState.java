package com.example.ReserveNestProject.states;

import com.example.ReserveNestProject.models.Booking;

public class ConfirmedState implements BookingState {
    @Override
    public void handleCheckIn(Booking booking) {
        // Handle check-in logic for a confirmed booking
        booking.setStatus("CheckedIn");
        // Update booking in the database, etc.
    }

    @Override
    public void handleCheckOut(Booking booking) {
        throw new IllegalStateException("Cannot check out a booking that is not checked in.");
    }

    @Override
    public void handleCancel(Booking booking) {
        // Handle cancellation logic for a confirmed booking
        booking.setStatus("Cancelled");
        // Update booking in the database, etc.
    }
}
