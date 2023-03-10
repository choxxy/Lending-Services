package com.assessment.api.LoanOfferService.api.service;

import com.assessment.api.LoanOfferService.api.dto.LoanProductDto;
import com.assessment.api.LoanOfferService.api.entity.LoanProduct;
import com.assessment.api.LoanOfferService.api.mapper.LoanProductMapper;
import com.assessment.api.LoanOfferService.api.repository.LoanProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class LoanProductServiceImpl implements LoanProductService {
    private final LoanProductRepository repository;
    private final LoanProductMapper loanProductMapper;

    public LoanProductServiceImpl(LoanProductRepository repository, LoanProductMapper loanProductMapper) {
        this.repository = repository;
        this.loanProductMapper = loanProductMapper;
    }

    public LoanProductDto findById(Long id) {
        return loanProductMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public List<LoanProductDto> findByCondition(long maxLimit) {
        List<LoanProduct> entities = repository.findAll();
        return loanProductMapper.toDto(entities);
    }


    public List<LoanProductDto> findAll() {
        List<LoanProduct> entities = repository.findAll();
        return loanProductMapper.toDto(entities);
    }

    @Override
    public List<LoanProductDto> requestLoan(String walletId, Long amount) {

        /* check if user qualify for a loan
           1. Does not have a loan already
           2. Wallet is active
         */

        return null;
    }


}