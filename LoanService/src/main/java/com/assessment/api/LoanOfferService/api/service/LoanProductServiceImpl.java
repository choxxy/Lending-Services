package com.assessment.api.LoanOfferService.api.service;

import com.assessment.CommonService.api.dto.LoanProductDto;
import com.assessment.CommonService.api.dto.LoanRequestDto;
import com.assessment.api.LoanOfferService.api.entity.LoanRequest;
import com.assessment.api.LoanOfferService.api.mapper.LoanMapper;
import com.assessment.api.LoanOfferService.api.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class LoanServiceImpl implements LoanService {
    private final LoanRepository repository;
    private final LoanMapper loanProductMapper;

    public LoanServiceImpl(LoanRepository repository, LoanMapper loanProductMapper) {
        this.repository = repository;
        this.loanProductMapper = loanProductMapper;
    }

    public LoanProductDto findById(Long id) {
        return loanProductMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public List<LoanProductDto> findByCondition(long maxLimit) {
        List<LoanRequest> entities = repository.findAll();
        return loanProductMapper.toDto(entities);
    }


    public List<LoanProductDto> findAll() {
        List<LoanRequest> entities = repository.findAll();
        return loanProductMapper.toDto(entities);
    }

    @Override
    public List<LoanProductDto> requestLoan(LoanRequestDto loanRequestDto) {

        /* check if user qualify for a loan
           1. Does not have a loan already
           2. Wallet is active
         */



        return null;
    }


}