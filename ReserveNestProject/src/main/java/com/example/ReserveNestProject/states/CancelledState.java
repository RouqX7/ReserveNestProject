package com.example.ReserveNestProject.states;

import com.example.ReserveNestProject.models.Booking;

public class CancelledState implements BookingState {
    @Override
    public void handleCheckIn(Booking booking) {
        throw new IllegalStateException("Cannot check in a cancelled booking.");
    }

    @Override
    public void handleCheckOut(Booking booking) {
        throw new IllegalStateException("Cannot check out a cancelled booking.");
    }

    @Override
    public void handleCancel(Booking booking) {
        throw new IllegalStateException("Booking is already cancelled.");
    }
}
