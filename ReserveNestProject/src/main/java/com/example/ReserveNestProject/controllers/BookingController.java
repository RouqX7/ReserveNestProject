package com.example.ReserveNestProject.controllers;

import com.example.ReserveNestProject.ErrorResponse;
import com.example.ReserveNestProject.dto.BookingRequest;
import com.example.ReserveNestProject.dto.DiscountDetail;
import com.example.ReserveNestProject.models.*;
import com.example.ReserveNestProject.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {


    @Autowired
    private BookingPriceService bookingPriceService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookingService bookingService;

    @Autowired
    private AddOnService addOnService;

    @Autowired
    private  RoomService roomService;

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);


    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(createdBooking);
    }


    @PostMapping("/calculate-price")
    public ResponseEntity<?> calculateBookingPrice(@RequestBody BookingRequest bookingRequest) {
        Booking booking = convertToBooking(bookingRequest);


        // Fetch the room details based on roomId from the booking
        Room room = roomService.getRoomById(booking.getRoomId());
        if (room == null) {
            ErrorResponse errorResponse = new ErrorResponse("Room not found.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        // Fetch customer details
        Customer customer = customerService.getCustomerById(booking.getCustomerId());
        if (customer == null) {
            ErrorResponse errorResponse = new ErrorResponse("Customer not found.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        double addOnsPrice = addOnService.calculateAddOnsPrice(booking);
        double initialPrice = room.getRentPerDay() * booking.getTotalDays() + addOnsPrice;


        List<Discount> applicableDiscounts = discountService.getApplicableDiscounts(booking, customer);
        double finalPrice = discountService.applyDiscounts(initialPrice, applicableDiscounts);

        List<DiscountDetail> discountDetails = applicableDiscounts.stream()
                .map(discount -> convertToDiscountDetail(discount, initialPrice))
                .collect(Collectors.toList());

        BookingResponse response = new BookingResponse();
        response.setTotalPrice(finalPrice);
        response.setAppliedDiscounts(discountDetails);
        logger.debug("Room rent per day: {}", room.getRentPerDay());
        logger.debug("Total days: {}", booking.getTotalDays());
        logger.debug("Add-ons price: {}", addOnsPrice);
        logger.debug("Initial price (Room price + Add-ons): {}", initialPrice);
        logger.debug("Applicable discounts: {}", applicableDiscounts);
        logger.debug("Final price after discounts: {}", finalPrice);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmBooking(@RequestBody Booking booking) {
        try {
            // Logic to confirm the booking
            Booking confirmedBooking = bookingService.confirmBooking(booking);
            return ResponseEntity.ok(confirmedBooking);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error confirming booking: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }







    private DiscountDetail convertToDiscountDetail(Discount discount, double initialPrice) {
        DiscountDetail detail = new DiscountDetail();
        detail.setType(discount.getType());
        detail.setDiscountPercentage(discount.getDiscountPercentage());

        double amountApplied = initialPrice * (discount.getDiscountPercentage() / 100.0);
        detail.setAmountApplied(amountApplied);

        detail.setDescription(discount.getDescription());
        // Set other fields as required
        return detail;
    }
    public double calculateAddOnsPrice(Booking booking) {
        Map<String, Double> addOnPrices = addOnService.getAddOnPrices();
        double addOnsPrice = 0;

        if (booking.isBreakfastIncluded()) addOnsPrice += addOnPrices.getOrDefault("breakfastIncluded", 0.0);
        if (booking.isAirportPickupIncluded()) addOnsPrice += addOnPrices.getOrDefault("airportPickupIncluded", 0.0);
        // Continue for other add-ons...

        return addOnsPrice;
    }

    private Booking convertToBooking(BookingRequest bookingRequest) {
        // Convert BookingRequest DTO to Booking object
        Booking booking = new Booking();
        booking.setCustomerId(bookingRequest.getCustomerId());
        booking.setRoomId(bookingRequest.getRoomId());
        booking.setCheckInDate(bookingRequest.getCheckInDate());
        booking.setCheckOutDate(bookingRequest.getCheckOutDate());
        booking.setTotalDays(bookingRequest.getTotalDays());
        booking.setTotalAmount(booking.getTotalAmount());
        booking.setId(bookingRequest.get_id());
        booking.setStatus(bookingRequest.getStatus());
        booking.setCreatedAt(bookingRequest.getCreatedAt());
        booking.setUpdatedAt(bookingRequest.getUpdatedAt());
        booking.setSpecialRequests(bookingRequest.getSpecialRequests());
        booking.setBreakfastIncluded(bookingRequest.isBreakfastIncluded());
        booking.setAirportPickupIncluded(bookingRequest.isAirportPickupIncluded());
        booking.setRoomUpgrade(bookingRequest.isRoomUpgrade());
        booking.setLateCheckout(bookingRequest.isLateCheckout());
        booking.setHasSpecialAmenities(bookingRequest.isHasSpecialAmenities());
        booking.setHasReservedParking(bookingRequest.isHasReservedParking());
        booking.setHasSpaAccess(bookingRequest.isHasSpaAccess());
        booking.setHasDiningOptions(bookingRequest.isHasDiningOptions());
        return booking;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping(value ="/save")
    private String saveBooking(@RequestBody Booking booking){

        bookingService.saveOrUpdate(booking);
        return  booking.getId();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable String id, @RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.updateBooking(id, booking));
    }

    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseEntity<Booking> updateBookingStatus(@PathVariable String id, @PathVariable BookingStatus status) {
        return ResponseEntity.ok(bookingService.updateBookingStatus(id, status));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successfully");
    }

    @RequestMapping("/search/{id}")
    private Optional<Booking> getBookingById(@PathVariable(name = "id")String bookingId){

        return  bookingService.getBookingById(bookingId);
    }

    // Additional endpoints as necessary...
}
