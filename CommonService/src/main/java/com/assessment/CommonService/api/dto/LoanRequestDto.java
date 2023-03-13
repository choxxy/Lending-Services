package com.assessment.CommonService.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoanRequestDto {
    @NotNull(message = "The user's Id is required.")
    private Long customerId;
    @NotBlank(message = "The walletAccountId is required.")
    private String walletAccountId;
    private long loanProductId;
}
