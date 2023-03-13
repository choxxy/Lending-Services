package com.assessment.PaymentService.api.service;

import com.assessment.CommonService.api.dto.TransactionDto;
import com.assessment.PaymentService.api.entity.Transaction;
import com.assessment.PaymentService.api.mapper.TransactionMapper;
import com.assessment.PaymentService.api.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    public TransactionServiceImpl(TransactionRepository repository, TransactionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TransactionDto insert(TransactionDto dto) {
        Transaction entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public List<TransactionDto> findByPaymentId(Long paymentId) {
        return mapper.toDto(repository.findByPaymentId(paymentId));
    }
}