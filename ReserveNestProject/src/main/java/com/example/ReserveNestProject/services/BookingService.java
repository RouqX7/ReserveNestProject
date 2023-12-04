package com.example.ReserveNestProject.services;

import com.example.ReserveNestProject.factories.PaymentPlan;
import com.example.ReserveNestProject.models.Booking;
import com.example.ReserveNestProject.models.BookingStatus;
import com.example.ReserveNestProject.models.Customer;
import com.example.ReserveNestProject.observer.EmailNotificationListener;
import com.example.ReserveNestProject.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private PaymentService paymentService;
    private final BookingRepository bookingRepository;
    private final CustomerService customerService; //
    @Autowired
    public BookingService(CustomerService customerService, BookingRepository bookingRepository) {
        this.customerService = customerService;
        this.bookingRepository = bookingRepository;
    }


    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking saveOrUpdate(Booking booking) {
        bookingRepository.save(booking); // Save or update the room
        return booking;
    }


    public Optional<Booking> getBookingById(String bookingId) {
        return bookingRepository.findById(bookingId);
    }

    public Booking createBooking(Booking booking) {
        Optional<Customer> customerOpt = Optional.ofNullable(customerService.getCustomerById(booking.getCustomerId()));

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.getPreviousBookings().add(booking);
            customerService.saveOrUpdate(customer);
        }

        // Instantiate and register the PropertyChangeListener
        PropertyChangeListener emailListener = new EmailNotificationListener();
        booking.addPropertyChangeListener(emailListener);

        booking.setStatus(BookingStatus.PENDING.toString());

        // Save the booking with the listener attached
        return bookingRepository.save(booking);
    }

    public Booking finalizeBooking(String bookingId, String paymentPlanType) {
        Booking booking = getBookingById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        PaymentPlan paymentPlan = paymentService.processPayment(booking, paymentPlanType);

        // Using BookingContext for state transition
        BookingContext context = new BookingContext(booking);
        if (paymentPlan.isSuccessful()) { // Assuming you have a way to check if payment was successful
            context.toConfirmedState();
        } else {
            // Handle unsuccessful payment scenario
        }
        return saveOrUpdate(context.getBooking());
    }






    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }

    public Booking updateBooking(String bookingId, Booking bookingDetails) {
        return bookingRepository.findById(bookingId)
                .map(existingBooking -> {
                    existingBooking.setCustomerId(bookingDetails.getCustomerId());
                    existingBooking.setRoomId(bookingDetails.getRoomId());

                    existingBooking.setCheckInDate(bookingDetails.getCheckInDate());
                    existingBooking.setCheckOutDate(bookingDetails.getCheckOutDate());


                    existingBooking.setTotalDays(bookingDetails.getTotalDays());
                    existingBooking.setTotalAmount(bookingDetails.getTotalAmount());
                    existingBooking.setTransactionId(bookingDetails.getTransactionId());
                    existingBooking.setStatus(bookingDetails.getStatus());

                    existingBooking.setUpdatedAt(new Date());

                    return bookingRepository.save(existingBooking);
                })
                .orElseGet(() -> {
                    bookingDetails.setId(bookingId);
                    bookingDetails.setCreatedAt(new Date());
                    bookingDetails.setUpdatedAt(new Date());
                    return bookingRepository.save(bookingDetails);
                });
    }


    public Booking updateBookingStatus(String bookingId, BookingStatus newStatus) {
        return bookingRepository.findById(bookingId).map(booking -> {
            BookingContext context = new BookingContext(booking);

            switch (newStatus) {
                case CHECKED_IN:
                    context.checkIn();
                    break;
                case CANCELED:
                    context.cancel();
                    break;
                case CHECKED_OUT:
                    context.checkOut();
                    break;
            }
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }


    public Booking confirmBooking(Booking booking) {
        // If the booking already has an ID, we're confirming an existing booking.
        if(booking.getId() != null && bookingRepository.existsById(booking.getId())) {
            Booking existingBooking = bookingRepository.findById(booking.getId())
                    .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + booking.getId()));
            existingBooking.setStatus(BookingStatus.CONFIRMED.toString());
            existingBooking.setUpdatedAt(new Date()); // Set the updated time
            // Other logic if needed, e.g., sending confirmation email
            return bookingRepository.save(existingBooking);
        } else {
            // This is a new booking, save it first.
            booking.setStatus(BookingStatus.CONFIRMED.toString()); // Set the status to CONFIRMED directly
            booking.setCreatedAt(new Date()); // Set the created time
            booking.setUpdatedAt(booking.getCreatedAt()); // Set the updated time to the created time
            // Other logic if needed for a new booking
            return bookingRepository.save(booking);
        }
    }

    public Booking createAndConfirmBooking(Booking booking) {
        // Set the status of the booking to CONFIRMED
        booking.setStatus(BookingStatus.CONFIRMED.toString());

        // Save the booking to the database
        return bookingRepository.save(booking);
    }



}
