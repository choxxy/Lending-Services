package com.assessment.api.LoanOfferService.api.dto;

public class LoanProductDto extends AbstractDto<Long> {
    private Long Id;
    private Long maxAllowableLimit;
    private float interest;
    private int tenure;

    public LoanProductDto() {
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getId() {
        return this.Id;
    }

    public void setMaxAllowableLimit(Long maxAllowableLimit) {
        this.maxAllowableLimit = maxAllowableLimit;
    }

    public Long getMaxAllowableLimit() {
        return this.maxAllowableLimit;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public float getInterest() {
        return this.interest;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public int getTenure() {
        return this.tenure;
    }
}