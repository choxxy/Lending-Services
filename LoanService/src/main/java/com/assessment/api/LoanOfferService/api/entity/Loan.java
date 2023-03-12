package com.assessment.api.LoanOfferService.api.entity;

import com.assessment.CommonService.api.enums.LoanStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue
    private Long loanId;
    private Long userId;
    private Long loanProductId;
    private String walletAccountId;
    private float loanAmount;
    private float interest;
    private float totalAmount;
    private Date createdOn;
    private Date dueDate;
    private LoanStatus status;

}
