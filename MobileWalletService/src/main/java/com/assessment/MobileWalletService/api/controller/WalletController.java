package com.assessment.MobileWalletService.api.controller;

import com.assessment.MobileWalletService.api.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/wallet")
@RestController
@Slf4j
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping("/{accountId}/loan-limit")
    public ResponseEntity<Long> getLoanLimit(@PathVariable("accountId") String accountId) {
        Long limit = accountService.getLoanLimit(accountId);
        return ResponseEntity.ok(limit);
    }
}