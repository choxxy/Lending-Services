package com.assessment.CommonService.api.dto;

import com.assessment.CommonService.api.enums.LoanStatus;
import lombok.Data;

import java.util.Date;

@Data
public class LoanDto {
    private Long loanId;
    private Long loanProductId;
    private String walletAccountId;
    private Long userId;
    private float interest;
    private float loanAmount;
    private float totalAmount;
    private Date createdOn;
    private Date dueDate;
    private LoanStatus status;
}
