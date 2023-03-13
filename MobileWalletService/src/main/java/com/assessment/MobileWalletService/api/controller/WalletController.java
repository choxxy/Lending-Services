package com.assessment.MobileWalletService.api.controller;

import com.assessment.CommonService.api.dto.LoanLimitDto;
import com.assessment.MobileWalletService.api.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/wallet")
@RestController
@Slf4j
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{accountId}/loan-limit")
    public ResponseEntity<LoanLimitDto> getLoanLimit(@PathVariable("accountId") String accountId) {
        LoanLimitDto limit = walletService.getLoanLimit(accountId);
        return ResponseEntity.ok(limit);
    }
}