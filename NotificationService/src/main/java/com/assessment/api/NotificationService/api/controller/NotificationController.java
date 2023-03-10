package com.assessment.api.NotificationService.api.controller;

import com.assessment.CommonService.api.dto.LoanRequestDto;
import com.assessment.api.NotificationService.api.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/")
    public void sendNotification(@RequestBody LoanRequestDto loanRequestDto) {
        notificationService.sendNotification(loanRequestDto);
    }
}
