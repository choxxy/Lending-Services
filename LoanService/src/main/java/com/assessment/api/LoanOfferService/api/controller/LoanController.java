package com.assessment.api.LoanOfferService.api.controller;

import com.assessment.api.LoanOfferService.api.dto.LoanProductDto;
import com.assessment.api.LoanOfferService.api.service.LoanProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/loan-product")
@RestController
@Slf4j
public class LoanProductController {
    private final LoanProductService loanProductService;

    public LoanProductController(LoanProductService loanProductService) {
        this.loanProductService = loanProductService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanProductDto> findById(@PathVariable("id") Long id) {
        LoanProductDto loanProduct = loanProductService.findById(id);
        return ResponseEntity.ok(loanProduct);
    }


    @GetMapping("/")
    public ResponseEntity<List<LoanProductDto>> getAll() {
        List<LoanProductDto> loanProducts = loanProductService.findAll();
        return ResponseEntity.ok(loanProducts);
    }

    @GetMapping("/{limit}/limit")
    public ResponseEntity<List<LoanProductDto>> findByCondition(@PathVariable("limit") Long maxLimit) {
        List<LoanProductDto> loanProducts = loanProductService.findByCondition(maxLimit);
        return ResponseEntity.ok(loanProducts);
    }

}