package com.example.ReserveNestProject.states;

import com.example.ReserveNestProject.models.Booking;
import com.example.ReserveNestProject.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ConfirmedState implements BookingState {
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public void handleCheckIn(Booking booking) {
        booking.setStatus("CheckedIn");
        booking.setState(new CheckedInState());
        bookingRepository.save(booking);
    }
    @Override
    public void handleCheckOut(Booking booking) {
        throw new IllegalStateException("Cannot check out a booking that is not checked in.");
    }
    @Override
    public void handleCancel(Booking booking) {
        booking.setStatus("Cancelled");
        booking.setState(new CancelledState());
        bookingRepository.save(booking);
    }
}
