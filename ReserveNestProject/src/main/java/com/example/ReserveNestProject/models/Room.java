package com.example.ReserveNestProject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "rooms")
public class Room {
    private String name;
    @Id
    private String _id;
    private int maxCount;
    private String phoneNumber;
    private double rentPerDay;
    private String description;
    private ArrayList<Booking> currentBookings = new ArrayList<>();
    private boolean available;
    private String primaryImageUrl;
    private List<String> expandedImageUrls;
    private String type;

    public Room(String name, String _id, int maxCount, String phoneNumber, double rentPerDay, String description, ArrayList<Booking> currentBookings,
                boolean available, String primaryImageUrl, String type,
                List<String> expandedImageUrls) {
        this.name = name;
        this._id = _id;
        this.maxCount = maxCount;
        this.phoneNumber = phoneNumber;
        this.rentPerDay = rentPerDay;
        this.description = description;
        this.currentBookings = currentBookings;
        this.available = available;
        this.primaryImageUrl = primaryImageUrl;
        this.expandedImageUrls = expandedImageUrls;
        this.type = type;
    }

    public Room() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getRentPerDay() {
        return rentPerDay;
    }

    public void setRentPerDay(double rentPerDay) {
        this.rentPerDay = rentPerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Booking> getCurrentBookings() {
        return currentBookings;
    }

    public void setCurrentBookings(ArrayList<Booking> currentBookings) {
        this.currentBookings = currentBookings;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getPrimaryImageUrl() {
        return primaryImageUrl;
    }

    public void setPrimaryImageUrl(String primaryImageUrl) {
        this.primaryImageUrl = primaryImageUrl;
    }

    public List<String> getExpandedImageUrls() {
        return expandedImageUrls;
    }

    public void setExpandedImageUrls(List<String> expandedImageUrls) {
        this.expandedImageUrls = expandedImageUrls;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", id='" + _id + '\'' +
                ", maxCount=" + maxCount +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", rentPerDay=" + rentPerDay +
                ", description='" + description + '\'' +
                ", currentBookings=" + currentBookings +
                ", available=" + available +
                ", primaryImageUrl='" + primaryImageUrl + '\'' +
                ", expandedImageUrls=" + expandedImageUrls +
                ", type='" + type + '\'' +
                '}';
    }
}



