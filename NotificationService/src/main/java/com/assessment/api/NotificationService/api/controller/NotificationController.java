package com.assessment.api.NotificationService.api.controller;

import com.assessment.CommonService.api.dto.NotificationDto;
import com.assessment.api.NotificationService.api.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/notify")
    public ResponseEntity<Boolean> sendNotification(@RequestBody NotificationDto dto) {
        notificationService.sendNotification(dto);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
