package com.assessment.PaymentService.api.controller;

import com.assessment.CommonService.api.dto.PaymentDto;
import com.assessment.PaymentService.api.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/payment")
@RestController
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated PaymentDto paymentDto) {
        paymentService.save(paymentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{loanId}/make-payment")
    public ResponseEntity<Void> makePayment(@PathVariable("loanId") Long loanId) {
        paymentService.makePayment(loanId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> findById(@PathVariable("id") Long id) {
        PaymentDto payment = paymentService.findById(id);
        return ResponseEntity.ok(payment);
    }


}