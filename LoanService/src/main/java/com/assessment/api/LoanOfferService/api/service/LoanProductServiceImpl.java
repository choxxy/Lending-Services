package com.assessment.api.LoanOfferService.api.service;

import com.assessment.CommonService.api.dto.LoanProductDto;
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
    private final LoanProductMapper mapper;

    public LoanProductServiceImpl(LoanProductRepository repository, LoanProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public LoanProductDto findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<LoanProductDto> getLoanLadder(long userLoanLimit) {
        List<LoanProduct> entities = repository.findByMaxAllowableLimitLessThanEqual(userLoanLimit);
        return mapper.toDto(entities);
    }


    @Override
    public List<LoanProductDto> findAll() {
        List<LoanProduct> entities = repository.findAll();
        return mapper.toDto(entities);
    }
}