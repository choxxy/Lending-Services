package com.assessment.CommonService.api.dto;

import lombok.Data;

@Data
public class LoanRequestDto {
    private long userId;
    private String walletAccountId;
    private long loanProductId;
}
