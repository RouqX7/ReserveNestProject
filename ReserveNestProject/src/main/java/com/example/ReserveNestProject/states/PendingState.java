package com.example.ReserveNestProject.states;

import com.example.ReserveNestProject.models.Booking;

public class PendingState implements BookingState {
    @Override
    public void handleCheckIn(Booking booking) {
        throw new IllegalStateException("Cannot check in a booking that is pending.");
    }

    @Override
    public void handleCheckOut(Booking booking) {
        throw new IllegalStateException("Cannot check out a booking that is pending.");
    }

    @Override
    public void handleCancel(Booking booking) {
        booking.setStatus("Cancelled");
        booking.setState(new CancelledState());
        // Update booking in the database.
    }

}
