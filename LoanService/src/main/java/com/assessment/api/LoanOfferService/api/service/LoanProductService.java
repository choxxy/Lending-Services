package com.assessment.api.LoanOfferService.api.service;

import com.assessment.CommonService.api.dto.LoanProductDto;

import java.util.List;

public interface LoanProductService {

    LoanProductDto findById(Long id);

    List<LoanProductDto> getLoanLadder(long userLoanLimit);

    List<LoanProductDto> findAll();
}