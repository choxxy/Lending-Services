package com.assessment.CommonService.api.dto;

import com.assessment.CommonService.api.enums.LoanStatus;
import lombok.Data;

@Data
public class LoanDto {
    private Long Id;
    private Long loanProductId;
    private String walletId;
    private Long requestId;
    private Long userId;
    float amount;
    private float loanAmount;
    private String entryDateTime;
    private LoanStatus status;
}
