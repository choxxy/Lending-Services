package com.assessment.api.LoanOfferService;



import com.assessment.CommonService.api.dto.LoanProductDto;

import java.util.Collections;
import java.util.List;

public class LoanProductBuilder {
    public static LoanProductDto getDto() {
        LoanProductDto dto = new LoanProductDto();
        dto.setId(1L);
        return dto;
    }
}