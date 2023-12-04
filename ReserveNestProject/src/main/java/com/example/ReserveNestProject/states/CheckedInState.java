package com.example.ReserveNestProject.states;

import com.example.ReserveNestProject.models.Booking;
import com.example.ReserveNestProject.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckedInState implements BookingState {
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void handleCheckIn(Booking booking) {
        throw new IllegalStateException("Booking is already checked in.");
    }

    @Override
    public void handleCheckOut(Booking booking) {
        booking.setStatus("CheckedOut");
        booking.setState(new CheckedOutState());
        bookingRepository.save(booking); // Persist the updated booking in the database
    }

    @Override
    public void handleCancel(Booking booking) {
        throw new IllegalStateException("Cannot cancel a booking that has been checked in.");
    }
}
