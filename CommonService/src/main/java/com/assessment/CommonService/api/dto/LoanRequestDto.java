package com.assessment.CommonService.api.dto;

import lombok.Data;

@Data
public class LoanRequestDto {
    private String walletId;
    private String loanProductId;
    private float loanAmount;
    private String requestDateTime;
    private LoanRequestStatus requestStatus;
    private String message;
}
