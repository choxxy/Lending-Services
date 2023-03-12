package com.assessment.api.LoanOfferService.api.service;

import com.assessment.CommonService.api.dto.LoanProductDto;
import com.assessment.CommonService.api.dto.LoanRequestDto;
import com.assessment.api.LoanOfferService.api.dto.LoanProductDto;

import java.util.List;

public interface LoanRequestService {

    LoanProductDto findById(Long id);

    List<LoanProductDto> findByCondition(long maxLimit);

    List<LoanProductDto> findAll();

    List<LoanProductDto> requestLoan(LoanRequestDto loanRequestDto);
}