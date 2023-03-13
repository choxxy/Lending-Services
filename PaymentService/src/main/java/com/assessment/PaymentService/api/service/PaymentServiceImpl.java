package com.assessment.PaymentService.api.service;

import com.assessment.CommonService.api.dto.NotificationDto;
import com.assessment.CommonService.api.dto.PaymentDto;
import com.assessment.CommonService.api.dto.TransactionDto;
import com.assessment.CommonService.api.enums.LoanStatus;
import com.assessment.PaymentService.api.entity.Payment;
import com.assessment.PaymentService.api.mapper.PaymentMapper;
import com.assessment.PaymentService.api.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper paymentMapper;
    private final TransactionService transactionService;
    private final WebClient webClient;

    public PaymentServiceImpl(PaymentRepository repository,
                              PaymentMapper paymentMapper,
                              TransactionService transactionService,
                              WebClient webClient) {
        this.repository = repository;
        this.paymentMapper = paymentMapper;
        this.transactionService = transactionService;
        this.webClient = webClient;
    }

    public PaymentDto save(PaymentDto paymentDto) {
        Payment entity = paymentMapper.toEntity(paymentDto);
        return paymentMapper.toDto(repository.save(entity));
    }

    public PaymentDto findById(Long id) {
        return paymentMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<PaymentDto> findByCondition(PaymentDto paymentDto, Pageable pageable) {
        Page<Payment> entityPage = repository.findAll(pageable);
        List<Payment> entities = entityPage.getContent();
        return new PageImpl<>(paymentMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public PaymentDto update(PaymentDto paymentDto) {
        PaymentDto data = findById(paymentDto.getId());
        Payment entity = paymentMapper.toEntity(paymentDto);
        BeanUtils.copyProperties(data, entity);
        return save(paymentMapper.toDto(entity));
    }

    @Override
    public void makeBatchPayment() {
        // attempt to pay payments due.
        // save transaction to transaction table and send notification
    }

    @Override
    public void makePayment(Long loanId) {
        // check wallet balance and attempt payment.
        // save transaction to transaction table and send notification

        Payment payment = repository.findByLoanId(loanId).orElseThrow(ResourceNotFoundException::new);
        payment.setAmount(0.0f);
        update(paymentMapper.toDto(payment));


        TransactionDto transaction = new TransactionDto();
        transaction.setAmount(payment.getAmount());
        transaction.setEntryDate(new Date());
        transaction.setPaymentId(payment.getId());
        transactionService.insert(transaction);

        updateLoan(payment.getLoanId());

        sendNotification();
    }

    private String updateLoan(Long loanId) {

        return webClient.get()
                .uri("http://localhost:9091/api/loan/" + loanId + "/update-payment-status/"+ LoanStatus.PAID.name())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private String sendNotification() {

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setMail("user@test.com");
        notificationDto.setPhoneNumber("+254700000000");
        notificationDto.setMessage("Your loan has been paid successfully.");

        return webClient.post()
                .uri("http://localhost:9093/api/notification/notify")
                .bodyValue(notificationDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}