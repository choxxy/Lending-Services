package com.assessment.api.LoanOfferService.api.service;

import com.assessment.api.LoanOfferService.api.dto.LoanProductDto;

import java.util.List;

public interface LoanProductService {

    LoanProductDto findById(Long id);

    List<LoanProductDto> findByCondition(long maxLimit);

    List<LoanProductDto> findAll();

    List<LoanProductDto> requestLoan(String walletId, Long amount);

}