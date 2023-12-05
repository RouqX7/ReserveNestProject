package com.example.ReserveNestProject.services;

import com.example.ReserveNestProject.decorators.AddOnsDecorator;
import com.example.ReserveNestProject.models.Booking;
import com.example.ReserveNestProject.models.Customer;
import com.example.ReserveNestProject.models.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
@Service
public class BookingPriceService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private DiscountCalculator discountCalculator;

    public double calculateFinalPrice(Booking booking) {
        Booking decoratedBooking = new AddOnsDecorator(booking);
        double basePrice = decoratedBooking.calculateTotalPrice(); // Price including add-ons

        Customer customer = customerService.getCustomerById(booking.getCustomerId());
        if (customer == null) {
            throw new IllegalStateException("Customer with ID " + booking.getCustomerId() + " not found.");
        }

        List<Discount> discounts = discountService.getApplicableDiscounts(booking, customer);
        double discountAmount = discountCalculator.calculateDiscount(booking, customer, discounts);

        return basePrice - discountAmount; // Final price after discounts
    }



    private double calculateDiscounts(List<Discount> discounts, Booking booking, Customer customer) {
        double discountAmount = 0;
        for (Discount discount : discounts) {
            if (isDiscountApplicable(discount, booking, customer)) {
                discountAmount += booking.getTotalAmount() * (discount.getDiscountPercentage() / 100.0);
            }
        }
        return discountAmount;
    }

    private boolean isDiscountApplicable(Discount discount, Booking booking, Customer customer) {
        LocalDate bookingDate = convertToLocalDate(booking.getCheckInDate());
        LocalDate checkOutDate = convertToLocalDate(booking.getCheckOutDate());
        LocalDate validityStart = LocalDate.parse(discount.getValidityPeriodStart());
        LocalDate validityEnd = LocalDate.parse(discount.getValidityPeriodEnd());

        return switch (discount.getType()) {
            case "FIRST_TIME_USER" -> isFirstTimeUser(customer);
            case "LOYALTY_DISCOUNT" -> isLoyalCustomer(customer);
            case "EARLY_BIRD" -> bookingDate.isAfter(LocalDate.now()) && bookingDate.isBefore(validityStart);
            case "LAST_MINUTE" -> bookingDate.isAfter(LocalDate.now()) && bookingDate.isBefore(validityEnd);
            case "BULK" -> booking.getTotalDays() > 7;
            default ->
                    false;
        };
    }

    private boolean isFirstTimeUser(Customer customer) {
        return customer.getPreviousBookings().isEmpty();
    }

    private boolean isLoyalCustomer(Customer customer) {
        final int LOYALTY_THRESHOLD = 5;
        return customer.getPreviousBookings().size() >= LOYALTY_THRESHOLD;
    }
    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}

