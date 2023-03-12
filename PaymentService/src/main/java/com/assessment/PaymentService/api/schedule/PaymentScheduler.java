package com.assessment.PaymentService.api.schedule;

import com.assessment.PaymentService.api.service.PaymentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DroneScheduler {

    final PaymentService paymentService;

    public DroneScheduler(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Every  Hour
    @Scheduled(cron = "0 0 */1 * * *")
    public void updatePayments() {
        paymentService.updatePayments();
    }


}