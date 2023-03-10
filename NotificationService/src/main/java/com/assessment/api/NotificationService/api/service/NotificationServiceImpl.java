package com.assessment.api.NotificationService.api.service;


import com.assessment.CommonService.api.dto.LoanRequestDto;
import com.assessment.CommonService.api.dto.LoanRequestStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendNotification(LoanRequestDto loanRequestDto) {

        if (loanRequestDto.getRequestStatus() == LoanRequestStatus.APPROVED) {
            log.info("Your request of {} has been approved and deposited into your wallet.", loanRequestDto.getLoanAmount());
        } else {
            log.info("Your request of {} has been declined. {}", loanRequestDto.getLoanAmount(),
                    loanRequestDto.getMessage());
        }


    }
}
