package com.assessment.CommonService.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanLimitDto {
    private String walletAccountId;
    private long loanLimit;
}
