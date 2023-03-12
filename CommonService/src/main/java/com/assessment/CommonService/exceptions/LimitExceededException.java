package com.assessment.CommonService.exceptions;

public class LimitExceededException extends RuntimeException{
    public LimitExceededException(String errorMessage){
        super(errorMessage);
    }
}