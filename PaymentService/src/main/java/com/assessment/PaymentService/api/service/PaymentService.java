package com.assessment.PaymentService.api.service;

import com.assessment.CommonService.api.dto.PaymentDto;


public interface PaymentService {

    PaymentDto save(PaymentDto paymentDto);

    PaymentDto findById(Long id);

    void makePayment(Long paymentId);

    void makeBatchPayment();
}