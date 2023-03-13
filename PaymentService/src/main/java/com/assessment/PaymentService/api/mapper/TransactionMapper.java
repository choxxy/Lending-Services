package com.assessment.PaymentService.api.mapper;

import com.assessment.CommonService.api.dto.TransactionDto;
import com.assessment.CommonService.api.mapper.EntityMapper;
import com.assessment.PaymentService.api.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends EntityMapper<TransactionDto, Transaction> {
}