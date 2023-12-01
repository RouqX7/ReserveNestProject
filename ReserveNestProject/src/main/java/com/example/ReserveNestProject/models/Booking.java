package com.example.ReserveNestProject.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Booking {

    @Id
    private String id; // Unique identifier for the booking
    private String customerId; // Identifier linking to the customer who made the booking
    private String roomId; // Identifier linking to the room that is booked
    private Date checkInDate; // Date and time when the customer plans to check in
    private Date checkOutDate; // Date and time when the customer plans to check out
    private int totalDays; // Total number of days for the booking
    private double totalAmount; // Total cost of the stay
    private String transactionId; // Identifier for the transaction
    private String status; // Status of the booking (e.g., booked, cancelled)
    private Date createdAt; // Timestamp when the booking was created
    private Date updatedAt; // Timestamp when the booking was last updated
    private String specialRequests;

    private boolean isBreakfastIncluded;
    private boolean isAirportPickupIncluded;
    private boolean isRoomUpgrade;
    private boolean isLateCheckout;
    private boolean hasSpecialAmenities;
    private boolean hasReservedParking;
    private boolean hasSpaAccess;
    private boolean hasDiningOptions;


    public Booking(String id, String customerId, String roomId, Date checkInDate, Date checkOutDate, int totalDays, double totalAmount, String transactionId, String status, Date createdAt, Date updatedAt, String specialRequests, boolean isBreakfastIncluded, boolean isAirportPickupIncluded, boolean isRoomUpgrade,
                   boolean isLateCheckout, boolean hasSpecialAmenities, boolean hasReservedParking, boolean hasSpaAccess, boolean hasDiningOptions) {
        this.id = id;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalDays = totalDays;
        this.totalAmount = totalAmount;
        this.transactionId = transactionId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.specialRequests = specialRequests;
        this.isBreakfastIncluded = isBreakfastIncluded;
        this.isAirportPickupIncluded = isAirportPickupIncluded;
        this.isRoomUpgrade = isRoomUpgrade;
        this.isLateCheckout = isLateCheckout;
        this.hasSpecialAmenities = hasSpecialAmenities;
        this.hasReservedParking = hasReservedParking;
        this.hasSpaAccess = hasSpaAccess;
        this.hasDiningOptions = hasDiningOptions;
    }

    public Booking(){

    }

    public double calculateTotalPrice() {
        // Implement logic for calculating the basic price of the booking
        return this.totalAmount; // Assuming totalAmount is a field in Booking
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }

    public boolean isBreakfastIncluded() {
        return isBreakfastIncluded;
    }

    public void setBreakfastIncluded(boolean breakfastIncluded) {
        isBreakfastIncluded = breakfastIncluded;
    }

    public boolean isAirportPickupIncluded() {
        return isAirportPickupIncluded;
    }

    public void setAirportPickupIncluded(boolean airportPickupIncluded) {
        isAirportPickupIncluded = airportPickupIncluded;
    }

    public boolean isRoomUpgrade() {
        return isRoomUpgrade;
    }

    public void setRoomUpgrade(boolean roomUpgrade) {
        isRoomUpgrade = roomUpgrade;
    }

    public boolean isLateCheckout() {
        return isLateCheckout;
    }

    public void setLateCheckout(boolean lateCheckout) {
        isLateCheckout = lateCheckout;
    }

    public boolean isHasSpecialAmenities() {
        return hasSpecialAmenities;
    }

    public void setHasSpecialAmenities(boolean hasSpecialAmenities) {
        this.hasSpecialAmenities = hasSpecialAmenities;
    }

    public boolean isHasReservedParking() {
        return hasReservedParking;
    }

    public void setHasReservedParking(boolean hasReservedParking) {
        this.hasReservedParking = hasReservedParking;
    }

    public boolean isHasSpaAccess() {
        return hasSpaAccess;
    }

    public void setHasSpaAccess(boolean hasSpaAccess) {
        this.hasSpaAccess = hasSpaAccess;
    }

    public boolean isHasDiningOptions() {
        return hasDiningOptions;
    }

    public void setHasDiningOptions(boolean hasDiningOptions) {
        this.hasDiningOptions = hasDiningOptions;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalDays=" + totalDays +
                ", totalAmount=" + totalAmount +
                ", transactionId='" + transactionId + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", specialRequests='" + specialRequests + '\'' +
                ", isBreakfastIncluded=" + isBreakfastIncluded +
                ", isAirportPickupIncluded=" + isAirportPickupIncluded +
                ", isRoomUpgrade=" + isRoomUpgrade +
                ", isLateCheckout=" + isLateCheckout +
                ", hasSpecialAmenities=" + hasSpecialAmenities +
                ", hasReservedParking=" + hasReservedParking +
                ", hasSpaAccess=" + hasSpaAccess +
                ", hasDiningOptions=" + hasDiningOptions +
                '}';
    }
}
