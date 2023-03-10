package com.assessment.api.NotificationService.api.service;


import com.assessment.CommonService.api.dto.LoanRequestDto;

public interface NotificationService {
    public void sendNotification(LoanRequestDto loanRequestDto);
}
