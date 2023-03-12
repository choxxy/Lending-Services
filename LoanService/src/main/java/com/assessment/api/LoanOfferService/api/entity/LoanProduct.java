package com.assessment.api.LoanOfferService.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue
    Long Id;
    Long maxAllowableLimit;
    float interest;
    int tenure; //Days
}
