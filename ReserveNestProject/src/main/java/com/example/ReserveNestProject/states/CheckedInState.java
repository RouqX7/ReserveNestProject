package com.example.ReserveNestProject.states;

import com.example.ReserveNestProject.models.Booking;

public class CheckedInState implements BookingState {
    public void handleCheckIn(Booking booking) {
        // Already checked in
        throw new IllegalStateException("Booking is already checked in.");
    }
    public void handleCheckOut(Booking booking) {
        // Handle check-out logic
        booking.setStatus("CheckedOut");
        // Update booking in the database, etc.
    }
    public void handleCancel(Booking booking) {
        // Cannot cancel a booking that has been checked in
        throw new IllegalStateException("Cannot cancel a booking that has been checked in.");
    }
    // Other operations
}