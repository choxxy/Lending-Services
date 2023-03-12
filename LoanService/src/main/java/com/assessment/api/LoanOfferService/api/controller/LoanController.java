package com.assessment.api.LoanOfferService.api.controller;

import com.assessment.CommonService.api.dto.LoanDto;
import com.assessment.CommonService.api.dto.LoanProductDto;
import com.assessment.CommonService.api.dto.LoanRequestDto;
import com.assessment.api.LoanOfferService.api.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/loan")
@RestController
@Slf4j
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDto> findById(@PathVariable("id") Long id) {
        LoanDto loan = loanService.findById(id);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/{loanId}/update-payment-status")
    public ResponseEntity<Void> updatePaymentStatus(@PathVariable("loanId") Long loanId) {
        loanService.updatePaymentStatus(loanId);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/")
    public ResponseEntity<List<LoanProductDto>> requestLoan(@Valid @RequestBody LoanRequestDto loanRequestDto) {
        List<LoanProductDto> loanProducts = loanService.requestLoan(loanRequestDto);
        return ResponseEntity.ok(loanProducts);
    }

    @PostMapping("/request")
    public ResponseEntity<LoanDto> processLoanRequest(@RequestBody LoanRequestDto loanRequestDto) {
        LoanDto loan = loanService.processLoanRequest(loanRequestDto);
        return ResponseEntity.ok(loan);
    }

}