package com.assessment.PaymentService.api.mapper;

import com.assessment.CommonService.api.dto.PaymentDto;
import com.assessment.CommonService.api.mapper.EntityMapper;
import com.assessment.PaymentService.api.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends EntityMapper<PaymentDto, Payment> {
}