package com.example.ReserveNestProject.services;

import com.example.ReserveNestProject.models.Booking;
import com.example.ReserveNestProject.models.BookingStatus;
import com.example.ReserveNestProject.models.Customer;
import com.example.ReserveNestProject.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
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

    public void saveOrUpdate(Booking booking) {
        bookingRepository.save(booking); // Save or update the room
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

        booking.setStatus(BookingStatus.PENDING.toString());
        return bookingRepository.save(booking);
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
                case PENDING:
                    context.toPendingState();
                    break;
                case CONFIRMED:
                    context.toConfirmedState();
                    break;
                case CHECKED_IN:
                    context.toCheckedInState();
                    break;
                case CANCELED:
                    context.toCancelledState();
                    break;
                case CHECKED_OUT:
                    context.toCheckedOutState();
                    break;
            }
            booking.setStatus(context.getCurrentState().toString());
            booking.setUpdatedAt(new Date());
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


}
