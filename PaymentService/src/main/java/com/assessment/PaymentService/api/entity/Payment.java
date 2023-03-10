package com.assessment.PaymentService.api.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue
    Long Id;
    Long loanId;
    String walletId;
    String paymentDateTime;
    String amount;
}
