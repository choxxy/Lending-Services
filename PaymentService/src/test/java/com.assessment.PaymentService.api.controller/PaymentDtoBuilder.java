package com.assessment.PaymentService.api.controller;

import com.assessment.CommonService.api.dto.PaymentDto;

public class PaymentDtoBuilder {
    public static PaymentDto getDto() {
        PaymentDto dto = new PaymentDto();
        dto.setId(1L);
        return dto;
    }
}