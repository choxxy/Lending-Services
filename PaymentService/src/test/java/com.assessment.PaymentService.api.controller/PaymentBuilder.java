package com.assessment.PaymentService.api.controller;

import com.assessment.CommonService.api.dto.PaymentDto;

import java.util.Collections;
import java.util.List;

public class PaymentBuilder {
    public static List<Long> getIds() {
        return Collections.singletonList(1L);
    }

    public static PaymentDto getDto() {
        PaymentDto dto = new PaymentDto();
        dto.setId(1L);
        return dto;
    }
}