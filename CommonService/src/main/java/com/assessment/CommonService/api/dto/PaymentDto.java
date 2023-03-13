package com.assessment.CommonService.api.dto;


import lombok.Data;

import java.util.Date;

@Data
public class PaymentDto {
    private Long id;
    private Long loanId;
    private String walletAccountId;
    private Date entryDate;
    private float amount;
    private float balance;
    private Date dueDate;
}