package com.example.ReserveNestProject.services;

import com.example.ReserveNestProject.models.Booking;
import com.example.ReserveNestProject.repo.BookingRepository;
import com.example.ReserveNestProject.states.*;

public class BookingContext {
    private BookingState currentState;
    private Booking booking;

    private BookingRepository bookingRepository;

    public BookingContext(Booking booking) {
        this.booking = booking;
        switch (booking.getStatus()) {
            case "PENDING":
                this.currentState = new PendingState();
                break;
            case "CONFIRMED":
                this.currentState = new ConfirmedState();
                break;
            case "CHECKED_IN":
                this.currentState = new CheckedInState();
                break;
            case "CHECKED_OUT":
                this.currentState = new CheckedOutState();
                break;
            case "CANCELLED":
                this.currentState = new CancelledState();
                break;
            default:
                throw new IllegalStateException("Unknown booking status: " + booking.getStatus());
        }
    }

    public void toPendingState() {
        currentState = new PendingState();
    }

    public void toConfirmedState() {
        currentState = new ConfirmedState();
    }

    public void toCheckedInState() {
        currentState = new CheckedInState();
    }

    public void toCheckedOutState() {
        currentState = new CheckedOutState();
    }

    public void toCancelledState() {
        currentState = new CancelledState();
    }

    public BookingState getCurrentState() {
        return currentState;
    }

    // Delegate methods to current state
    public void checkIn() {
        currentState.handleCheckIn(booking);
        bookingRepository.save(booking); // Persist the updated booking
    }

    public void checkOut() {
        currentState.handleCheckOut(booking);
        bookingRepository.save(booking); // Persist the updated booking
    }

    public void cancel() {
        currentState.handleCancel(booking);
        bookingRepository.save(booking); // Persist the updated booking
    }

    public void setCurrentState(BookingState currentState) {
        this.currentState = currentState;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

}
