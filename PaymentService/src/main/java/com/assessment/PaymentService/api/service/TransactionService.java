package com.assessment.PaymentService.api.service;

import com.assessment.CommonService.api.dto.PaymentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PaymentService {

    PaymentDto save(PaymentDto paymentDto);

    void deleteById(Long id);

    PaymentDto findById(Long id);

    Page<PaymentDto> findByCondition(PaymentDto paymentDto, Pageable pageable);

    PaymentDto update(PaymentDto paymentDto, Long id);

    void updatePayments();

    void makePayment(PaymentDto paymentDto);
}