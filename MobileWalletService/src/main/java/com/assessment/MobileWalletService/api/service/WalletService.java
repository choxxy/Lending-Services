package com.assessment.MobileWalletService.api.service;

import com.assessment.CommonService.api.dto.LoanLimitDto;

public interface WalletService {
    LoanLimitDto getLoanLimit(String accountId);
}