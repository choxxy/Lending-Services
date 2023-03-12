package com.assessment.api.LoanOfferService.api.service;

import com.assessment.CommonService.api.dto.LoanDto;
import com.assessment.CommonService.api.dto.LoanProductDto;
import com.assessment.CommonService.api.dto.LoanRequestDto;
import com.assessment.CommonService.api.enums.LoanStatus;

import java.util.List;

public interface LoanService {

    LoanDto findById(long Id);

    boolean hasActiveLoan(long userId);

    List<LoanDto> findByStatus(LoanStatus status);

    List<LoanDto> findAll();

    LoanDto updateLoan(LoanDto loanDto);

    List<LoanProductDto> requestLoan(LoanRequestDto loanRequestDto);

    LoanDto processLoanRequest(LoanRequestDto loanRequestDto);

    public LoanDto createLoan(LoanDto loanDto);

    void updatePaymentStatus(Long loanId);
}