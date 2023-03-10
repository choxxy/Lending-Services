package com.assessment.CommonService.api.dto;

import lombok.Data;

@Data
public class WalletDto {
    String walletId;
    String walletName;
    float walletBalance;
    float loanLimit;
    float loanBalance;
    WalletStatus status;
}
