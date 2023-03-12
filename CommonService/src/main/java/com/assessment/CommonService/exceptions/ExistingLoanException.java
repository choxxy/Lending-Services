package com.assessment.CommonService.exceptions;

public class ExistingLoanException extends RuntimeException{
    public ExistingLoanException(String errorMessage){
        super(errorMessage);
    }
}