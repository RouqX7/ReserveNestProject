package com.example.ReserveNestProject.services;

import com.example.ReserveNestProject.factories.ConcretePaymentPlanFactory;
import com.example.ReserveNestProject.factories.PaymentPlan;
import com.example.ReserveNestProject.factories.PaymentPlanFactory;
import com.example.ReserveNestProject.models.Booking;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentPlan processPayment(Booking booking, String planType) {
        PaymentPlanFactory factory = new ConcretePaymentPlanFactory();
        PaymentPlan paymentPlan = factory.createPaymentPlan(planType);

        paymentPlan.processPayment(booking);

        // Additional logic based on payment plan

        return paymentPlan;
    }
}
