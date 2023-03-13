package com.assessment.PaymentService.api.service;

import com.assessment.CommonService.api.dto.TransactionDto;

import java.util.List;


public interface TransactionService {
    TransactionDto insert(TransactionDto dto);
    List<TransactionDto> findByPaymentId(Long paymentId);
}