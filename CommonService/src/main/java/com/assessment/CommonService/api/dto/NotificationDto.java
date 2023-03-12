package com.assessment.CommonService.api.dto;

import lombok.Data;

@Data
public class NotificationDto {
    String phoneNumber;
    String mail;
    String message;
}
