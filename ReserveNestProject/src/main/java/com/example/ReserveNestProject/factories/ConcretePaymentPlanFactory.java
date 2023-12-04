package com.example.ReserveNestProject.factories;
public class ConcretePaymentPlanFactory extends PaymentPlanFactory {
    @Override
    public PaymentPlan createPaymentPlan(String type) {
        return switch (type) {
            case "standard" -> new StandardPaymentPlan();
            case "premium" -> new PremiumPaymentPlan();
            default -> throw new IllegalArgumentException("Unknown payment plan type " + type);
        };
    }
}