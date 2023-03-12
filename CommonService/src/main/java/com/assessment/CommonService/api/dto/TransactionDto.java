package com.assessment.CommonService.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {
    Long Id;
    Long paymentId;
    Date entryDate;
    float amount;
}
