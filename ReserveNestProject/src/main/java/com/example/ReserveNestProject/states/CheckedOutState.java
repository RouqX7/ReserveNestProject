package com.example.ReserveNestProject.states;

import com.example.ReserveNestProject.models.Booking;

public class CheckedOutState implements BookingState {
    @Override
    public void handleCheckIn(Booking booking) {
        throw new IllegalStateException("Cannot check in after the booking has been checked out.");
    }

    @Override
    public void handleCheckOut(Booking booking) {
        // The booking is already checked out, so this might be a no-op or error
        throw new IllegalStateException("Booking is already checked out.");
    }

    @Override
    public void handleCancel(Booking booking) {
        throw new IllegalStateException("Cannot cancel a booking after check-out.");
    }
}
