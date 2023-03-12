package com.assessment.api.LoanOfferService;



import com.assessment.CommonService.api.dto.LoanDto;
import com.assessment.CommonService.api.dto.LoanProductDto;

import java.util.Collections;
import java.util.List;

public class LoanBuilder {
    public static LoanDto getDto() {
        LoanDto dto = new LoanDto();
        dto.setLoanId(1L);
        return dto;
    }
}