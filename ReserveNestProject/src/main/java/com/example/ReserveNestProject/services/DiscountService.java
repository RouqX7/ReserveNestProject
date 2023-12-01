package com.example.ReserveNestProject.services;

import com.example.ReserveNestProject.models.Booking;
import com.example.ReserveNestProject.models.Customer;
import com.example.ReserveNestProject.models.Discount;
import com.example.ReserveNestProject.repo.CustomerRepo;
import com.example.ReserveNestProject.repo.DiscountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepo discountRepo;
    @Autowired
    private CustomerRepo customerRepo;


    public Discount saveDiscount(Discount discount) {
        // Validation and business logic before saving the discount
        return discountRepo.save(discount);
    }

    public List<Discount> getAllDiscounts() {
        return discountRepo.findAll();
    }


    public Optional<Discount> getDiscountById(String id) {
        return discountRepo.findById(id);
    }

    public Discount updateDiscount(String id, Discount discountDetails) {
        return discountRepo.findById(id)
                .map(discount -> {
                    discount.setType(discountDetails.getType());
                    discount.setDiscountPercentage(discountDetails.getDiscountPercentage());
                    discount.setDescription(discountDetails.getDescription());
                    discount.setValidityPeriodStart(discountDetails.getValidityPeriodStart());
                    discount.setValidityPeriodEnd(discountDetails.getValidityPeriodEnd());
                    discount.setBlackoutDates(discountDetails.getBlackoutDates());
                    discount.setTermsAndConditions(discountDetails.getTermsAndConditions());
                    return discountRepo.save(discount);
                })
                .orElseGet(() -> {
                    discountDetails.setId(id);
                    return discountRepo.save(discountDetails);
                });
    }


    public void deleteDiscount(String id) {
        discountRepo.deleteById(id);
    }


    public List<Discount> getApplicableDiscounts(Booking booking, Customer customer) {
        List<Discount> allDiscounts = discountRepo.findAll();
        return allDiscounts.stream()
                .filter(discount -> isDiscountApplicable(discount, booking, customer))
                .collect(Collectors.toList());
    }

    private boolean isDiscountApplicable(Discount discount, Booking booking, Customer customer) {
        // Implement logic for each discount type
        switch (discount.getType()) {
            case "FIRST_TIME_USER":
                return isFirstTimeUser(customer);
            case "LOYALTY_DISCOUNT":
                return isLoyalCustomer(customer);
            case "EARLY_BIRD":
                return isEarlyBird(booking);
            case "LAST_MINUTE":
                return isLastMinute(booking);
            case "BULK":
                return isBulk(booking);
            // Add more cases as necessary
            default:
                return false;
        }
    }

    private boolean isFirstTimeUser(Customer customer) {
        return customer.getPreviousBookings().isEmpty();
    }

    private boolean isLoyalCustomer(Customer customer) {
        final int LOYALTY_THRESHOLD = 5;
        return customer.getPreviousBookings() != null && customer.getPreviousBookings().size() >= LOYALTY_THRESHOLD;
    }

    private boolean isEarlyBird(Booking booking) {
        LocalDate bookingDate = convertToLocalDate(booking.getCheckInDate());
        return ChronoUnit.DAYS.between(LocalDate.now(), bookingDate) >= 30; // Adjust the days as needed
    }

    private boolean isLastMinute(Booking booking) {
        LocalDate bookingDate = convertToLocalDate(booking.getCheckInDate());
        return ChronoUnit.DAYS.between(LocalDate.now(), bookingDate) <= 2;
    }

    private boolean isBulk(Booking booking) {
        return booking.getTotalDays() > 7; // Adjust the threshold as needed
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public double applyDiscounts(double initialPrice, List<Discount> discounts) {
        double finalPrice = initialPrice;
        for (Discount discount : discounts) {
            double discountAmount = initialPrice * (discount.getDiscountPercentage() / 100);
            finalPrice -= discountAmount;
        }
        return finalPrice;
    }
    public List<Discount> getDiscountsForCustomer(String customerId, Booking booking) {
        // Retrieve the customer from the repository
        Optional<Customer> customerOpt = customerRepo.findById(customerId);
        if (customerOpt.isEmpty()) {
            return Collections.emptyList(); // No discounts if customer not found
        }
        Customer customer = customerOpt.get();

        // Retrieve all discounts
        List<Discount> allDiscounts = discountRepo.findAll();

        // Filter discounts based on customer-specific and booking-specific conditions
        return allDiscounts.stream()
                .filter(discount -> isDiscountApplicable(discount, booking, customer))
                .collect(Collectors.toList());
    }


    // Additional methods to apply discounts, etc.
}
