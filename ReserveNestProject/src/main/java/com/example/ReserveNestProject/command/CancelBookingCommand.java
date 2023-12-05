package com.example.ReserveNestProject.command;

import com.example.ReserveNestProject.models.Booking;
import org.springframework.stereotype.Component;
@Component
public class CancelBookingCommand implements Command {
    private Booking booking;
    public CancelBookingCommand() {
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public void execute() {
        // Logic to cancel the booking
        if (this.booking != null) {
            this.booking.getState().handleCancel(this.booking);
            // Perform cancellation
        } else {
            throw new IllegalStateException("Booking not set for cancellation command.");
        }
    }
}
