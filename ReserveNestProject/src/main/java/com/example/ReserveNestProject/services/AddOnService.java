package com.example.ReserveNestProject.services;

import com.example.ReserveNestProject.decorators.AddOnsDecorator;
import java.util.Map;
import java.util.HashMap;
import com.example.ReserveNestProject.models.Booking;
import org.springframework.stereotype.Service;

@Service
public class AddOnService {



    public Map<String, Double> getAddOnPrices() {
        Map<String, Double> addOnPrices = new HashMap<>();
        addOnPrices.put("breakfastIncluded", 15.0);
        addOnPrices.put("airportPickupIncluded", 30.0);
        addOnPrices.put("roomUpgrade", 100.0);
        addOnPrices.put("lateCheckout", 50.0);
        addOnPrices.put("specialAmenities", 50.0);
        addOnPrices.put("reservedParking", 25.0);
        addOnPrices.put("spaAccess", 30.0);
        addOnPrices.put("diningOptions", 30.0);

        return addOnPrices;
    }

    public double calculateAddOnsPrice(Booking booking) {
        Map<String, Double> addOnPrices = getAddOnPrices();
        double addOnsTotalPrice = 0;

        if (booking.isBreakfastIncluded()) addOnsTotalPrice += addOnPrices.getOrDefault("breakfastIncluded", 0.0);
        if (booking.isAirportPickupIncluded()) addOnsTotalPrice += addOnPrices.getOrDefault("airportPickupIncluded", 0.0);
        // Continue for other add-ons
        if (booking.isRoomUpgrade()) addOnsTotalPrice += addOnPrices.getOrDefault("roomUpgrade", 0.0);
        if (booking.isLateCheckout()) addOnsTotalPrice += addOnPrices.getOrDefault("lateCheckout", 0.0);
        if (booking.isHasSpecialAmenities()) addOnsTotalPrice += addOnPrices.getOrDefault("specialAmenities", 0.0);
        if (booking.isHasReservedParking()) addOnsTotalPrice += addOnPrices.getOrDefault("reservedParking", 0.0);
        if (booking.isHasSpaAccess()) addOnsTotalPrice += addOnPrices.getOrDefault("spaAccess", 0.0);
        if (booking.isHasDiningOptions()) addOnsTotalPrice += addOnPrices.getOrDefault("diningOptions", 0.0);

        return addOnsTotalPrice;
    }



//    private Booking createDummyBookingWithAllAddOns() {
//        Booking dummyBooking = new Booking();
//        dummyBooking.setBreakfastIncluded(true);
//        dummyBooking.setAirportPickupIncluded(true);
//        dummyBooking.setRoomUpgrade(true);
//        dummyBooking.setLateCheckout(true);
//        dummyBooking.setHasSpecialAmenities(true);
//        dummyBooking.setHasReservedParking(true);
//        dummyBooking.setHasSpaAccess(true);
//        dummyBooking.setHasDiningOptions(true);
//        return dummyBooking;
//    }
}
