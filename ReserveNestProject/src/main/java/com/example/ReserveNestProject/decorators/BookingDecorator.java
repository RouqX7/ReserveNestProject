package com.example.ReserveNestProject.decorators;

import com.example.ReserveNestProject.models.Booking;
import com.example.ReserveNestProject.models.Discount;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public abstract class BookingDecorator {
    protected Booking decoratedBooking;

    public BookingDecorator(Booking decoratedBooking) {
        this.decoratedBooking = decoratedBooking;
    }

    // Example method that adds behavior
    // Method to calculate the total price including add-ons and applicable discounts
    public double calculateTotalPrice(List<Discount> applicableDiscounts) {
        double basePrice = decoratedBooking.getTotalAmount();

        double addOnsPrice = calculateAddOnsPrice();
        double discountAmount = calculateDiscounts(applicableDiscounts);

        return basePrice + addOnsPrice - discountAmount;
    }
    private double calculateDiscounts(List<Discount> discounts) {
        double discountAmount = 0;
        for (Discount discount : discounts) {
            if (isDiscountApplicable(discount, decoratedBooking)) {
                discountAmount += decoratedBooking.getTotalAmount() * (discount.getDiscountPercentage() / 100.0);
            }
        }
        return discountAmount;
    }


    private boolean isDiscountApplicable(Discount discount, Booking booking) {
        LocalDate bookingDate = convertToLocalDate(booking.getCheckInDate());
        // Add logic to check if the booking date falls within the discount's validity period
        // and is not in the blackout dates.
        // ...

        return true; // return true if discount is applicable
    }


    private LocalDate convertToLocalDate(Date date) {
        if (date == null) {
            return null; // or handle the null case as needed
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    public abstract double calculateTotalPrice();

    private double calculateAddOnsPrice() {
        // Logic to calculate the price for add-ons
        // ...

        return 0; // return calculated add-ons price
    }

    // If needed, delegate other methods to the decoratedBooking
    public String getBookingId() {
        return decoratedBooking.getId();
    }

    // Add other delegated methods as necessary...
}
