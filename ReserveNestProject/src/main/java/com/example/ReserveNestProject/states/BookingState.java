package com.example.ReserveNestProject.states;

import com.example.ReserveNestProject.models.Booking;
import com.example.ReserveNestProject.services.BookingContext;

public interface BookingState {
    void handleCheckIn(Booking booking);
    void handleCheckOut(Booking booking);
    void handleCancel(Booking booking);

}