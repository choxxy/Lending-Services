package com.assessment.PaymentService.api.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "payments")
public class Transaction {
    @Id
    @GeneratedValue
    Long Id;
    Long paymentId;
    Date entryDate;
    float amount;
}
