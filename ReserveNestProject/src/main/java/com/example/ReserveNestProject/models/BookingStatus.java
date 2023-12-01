package com.example.ReserveNestProject.models;

public enum BookingStatus {
    PENDING, // Initial status, not yet confirmed
    CONFIRMED, // Confirmed booking
    CHECKED_IN, // Guest has checked in
    CANCELED, // Booking was canceled
    CHECKED_OUT
}
