package com.example.ReserveNestProject.dto;

public class DiscountDetail {

    private String type; // e.g., "EARLY_BIRD", "LOYAL_CUSTOMER"
    private double discountPercentage; // e.g., 10 for 10%
    private double amountApplied;
    private String description;
    private String validityPeriodStart;
    private String validityPeriodEnd;
    private String blackoutDates;
    private String termsAndConditions;


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

    public double getAmountApplied() {
        return amountApplied;
    }

    public void setAmountApplied(double amountApplied) {
        this.amountApplied = amountApplied;
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
