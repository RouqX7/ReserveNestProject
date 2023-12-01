package com.example.ReserveNestProject.states;

import com.example.ReserveNestProject.models.Booking;

public class PendingState implements BookingState {

    @Override
    public void handleCheckIn(Booking booking) {
        // Can not check-in a booking that is pending
        throw new IllegalStateException("Cannot check in a booking that is pending.");
    }
    @Override
    public void handleCheckOut(Booking booking) {
        // Can not check-out a booking that is pending
        throw new IllegalStateException("Cannot check out a booking that is pending.");
    }
    @Override
    public void handleCancel(Booking booking) {
        // Handle cancellation logic for a pending booking
        booking.setStatus("Cancelled");
        // Update booking in the database, etc.
    }
    // Other operations
}