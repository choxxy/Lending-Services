package com.assessment.PaymentService.api.service;

import com.assessment.CommonService.api.dto.PaymentDto;
import com.assessment.PaymentService.api.entity.Payment;
import com.assessment.PaymentService.api.mapper.PaymentMapper;
import com.assessment.PaymentService.api.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository repository, PaymentMapper paymentMapper) {
        this.repository = repository;
        this.paymentMapper = paymentMapper;
    }

    public PaymentDto save(PaymentDto paymentDto) {
        Payment entity = paymentMapper.toEntity(paymentDto);
        return paymentMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public PaymentDto findById(Long id) {
        return paymentMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<PaymentDto> findByCondition(PaymentDto paymentDto, Pageable pageable) {
        Page<Payment> entityPage = repository.findAll(pageable);
        List<Payment> entities = entityPage.getContent();
        return new PageImpl<>(paymentMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public PaymentDto update(PaymentDto paymentDto, Long id) {
        PaymentDto data = findById(id);
        Payment entity = paymentMapper.toEntity(paymentDto);
        BeanUtils.copyProperties(data, entity);
        return save(paymentMapper.toDto(entity));
    }
}