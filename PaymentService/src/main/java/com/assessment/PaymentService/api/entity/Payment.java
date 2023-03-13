package com.assessment.PaymentService.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long loanId;
    private String walletAccountId;
    private Date entryDate;
    private float amount;
    private Date dueDate;
}
