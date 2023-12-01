package com.example.ReserveNestProject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "discounts")
public class Discount {
    @Id
    private String id;
    private String type; // e.g., "EARLY_BIRD", "LAST_MINUTE", "BULK", "OFF_PEAK"
    private double discountPercentage;
    private String description;
    private String validityPeriodStart;
    private String validityPeriodEnd;
    private String blackoutDates; // This could be a List if multiple dates
    private String termsAndConditions;

    public Discount(String id, String type, double discountPercentage, String description, String validityPeriodStart,
                    String validityPeriodEnd, String blackoutDates, String termsAndConditions) {
        this.id = id;
        this.type = type;
        this.discountPercentage = discountPercentage;
        this.description = description;
        this.validityPeriodStart = validityPeriodStart;
        this.validityPeriodEnd = validityPeriodEnd;
        this.blackoutDates = blackoutDates;
        this.termsAndConditions = termsAndConditions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValidityPeriodStart() {
        return validityPeriodStart;
    }

    public void setValidityPeriodStart(String validityPeriodStart) {
        this.validityPeriodStart = validityPeriodStart;
    }

    public String getValidityPeriodEnd() {
        return validityPeriodEnd;
    }

    public void setValidityPeriodEnd(String validityPeriodEnd) {
        this.validityPeriodEnd = validityPeriodEnd;
    }

    public String getBlackoutDates() {
        return blackoutDates;
    }

    public void setBlackoutDates(String blackoutDates) {
        this.blackoutDates = blackoutDates;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }
}
