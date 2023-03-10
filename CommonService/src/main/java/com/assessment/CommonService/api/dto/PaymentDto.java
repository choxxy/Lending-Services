package com.assessment.CommonService.api.dto;


import lombok.Data;

@Data
public class PaymentDto{
    private Long Id;
    private Long loanId;
    private String walletId;
    private String paymentDateTime;
    private String amount;
}