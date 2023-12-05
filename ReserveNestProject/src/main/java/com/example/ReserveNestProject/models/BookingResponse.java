package com.example.ReserveNestProject.models;
import com.example.ReserveNestProject.dto.DiscountDetail;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class BookingResponse {
    private String bookingId;
    private String customerId;
    private String roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private int totalDays;
    private String status;
    private double basePrice;
    private double addOnsPrice;
    private double totalPrice;
    private double discountAmount;
    private List<DiscountDetail> appliedDiscounts;
    private Map<String, Boolean> addOns;
    private String specialRequests;
    private Date createdAt;
    private Date updatedAt;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getAddOnsPrice() {
        return addOnsPrice;
    }

    public void setAddOnsPrice(double addOnsPrice) {
        this.addOnsPrice = addOnsPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public List<DiscountDetail> getAppliedDiscounts() {
        return appliedDiscounts;
    }

    public void setAppliedDiscounts(List<DiscountDetail> appliedDiscounts) {
        this.appliedDiscounts = appliedDiscounts;
    }

    public Map<String, Boolean> getAddOns() {
        return addOns;
    }

    public void setAddOns(Map<String, Boolean> addOns) {
        this.addOns = addOns;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
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



}
