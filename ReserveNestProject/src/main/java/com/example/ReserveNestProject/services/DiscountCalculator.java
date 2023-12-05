package com.example.ReserveNestProject.services;

import com.example.ReserveNestProject.models.Booking;
import com.example.ReserveNestProject.models.Customer;
import com.example.ReserveNestProject.models.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class DiscountCalculator {

    @Autowired
    private final CustomerService customerService;

    public DiscountCalculator(CustomerService customerService) {
        this.customerService = customerService;
    }

    public double calculateDiscount(Booking booking, Customer customer, List<Discount> discounts) {
        double discountAmount = 0;
        for (Discount discount : discounts) {
            if (isDiscountApplicable(discount, booking, customer)) {
                discountAmount += booking.getTotalAmount() * (discount.getDiscountPercentage() / 100.0);
            }
        }
        return discountAmount;
    }
    private boolean isDiscountApplicable(Discount discount, Booking booking, Customer customer) {
        return switch (discount.getType()) {
            case "FIRST_TIME_USER" -> isFirstTimeUser(customer);
            case "LOYALTY_DISCOUNT" -> isLoyalCustomer(customer);
            case "EARLY_BIRD" -> isEarlyBird(booking);
            case "LAST_MINUTE" -> isLastMinute(booking);
            case "BULK" -> isBulk(booking);
            default -> false;
        };
    }

    private boolean isFirstTimeUser(Customer customer) {
        return customer.getPreviousBookings().isEmpty();
    }

    private boolean isLoyalCustomer(Customer customer) {
        final int LOYALTY_THRESHOLD = 5; // Number of bookings to be considered a loyal customer
        return customer.getPreviousBookings().size() >= LOYALTY_THRESHOLD;
    }

    private boolean isEarlyBird(Booking booking) {
        LocalDate bookingDate = convertToLocalDate(booking.getCheckInDate());
        long daysToCheckIn = ChronoUnit.DAYS.between(LocalDate.now(), bookingDate);
        return daysToCheckIn >= 60; // Example: 60 days before check-in
    }

    private boolean isLastMinute(Booking booking) {
        LocalDate bookingDate = convertToLocalDate(booking.getCheckInDate());
        long daysToCheckIn = ChronoUnit.DAYS.between(LocalDate.now(), bookingDate);
        return daysToCheckIn <= 2; // Example: 2 days or less before check-in
    }

    private boolean isBulk(Booking booking) {
        return booking.getTotalDays() > 7;
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    }

