package com.assessment.CommonService.api.dto;

import lombok.Data;

@Data
public class LoanProductDto {
    Long Id;
    Long maxAllowableLimit;
    float interest;
    int tenureInDays; //Days
}
