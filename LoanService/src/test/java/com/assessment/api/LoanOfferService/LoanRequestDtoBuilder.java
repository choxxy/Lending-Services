package com.assessment.api.LoanOfferService;


import com.assessment.CommonService.api.dto.LoanRequestDto;

public class LoanRequestDtoBuilder {
    public static LoanRequestDto getDto() {
        LoanRequestDto dto = new LoanRequestDto();
        dto.setUserId(1L);
        dto.setWalletAccountId("1");
        return dto;
    }

    public static LoanRequestDto getDtoNoUserId() {
        LoanRequestDto dto = new LoanRequestDto();
        dto.setWalletAccountId("1");
        return dto;
    }
}