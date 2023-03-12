package com.assessment.api.LoanOfferService.api.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "loan_products")
public class LoanProduct {
    @Id
    @GeneratedValue
    Long Id;
    long maxAllowableLimit;
    float interest;
    int tenureInDays; //Days
}
