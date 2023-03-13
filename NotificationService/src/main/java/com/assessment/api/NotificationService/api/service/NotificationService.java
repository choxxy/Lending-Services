package com.assessment.api.NotificationService.api.service;


import com.assessment.CommonService.api.dto.LoanRequestDto;
import com.assessment.CommonService.api.dto.NotificationDto;

public interface NotificationService {
    public void sendNotification(NotificationDto dto);
}
