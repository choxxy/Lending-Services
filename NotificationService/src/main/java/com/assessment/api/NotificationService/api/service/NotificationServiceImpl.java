package com.assessment.api.NotificationService.api.service;


import com.assessment.CommonService.api.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendNotification(NotificationDto dto) {
        log.info(dto.getMessage());
    }
}
