package com.assessment.api.LoanOfferService.api.service;

import com.assessment.CommonService.api.dto.*;
import com.assessment.CommonService.api.enums.LoanStatus;
import com.assessment.CommonService.exceptions.ExistingLoanException;
import com.assessment.api.LoanOfferService.api.entity.Loan;
import com.assessment.api.LoanOfferService.api.mapper.LoanMapper;
import com.assessment.api.LoanOfferService.api.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
public class LoanServiceImpl implements LoanService {
    private final LoanRepository repository;
    private final LoanProductService loanProductService;
    private final LoanMapper loanMapper;
    private final WebClient webClient;

    public LoanServiceImpl(LoanRepository repository,
                           LoanProductService loanProductService,
                           LoanMapper loanMapper,
                           WebClient webClient) {
        this.repository = repository;
        this.loanProductService = loanProductService;
        this.loanMapper = loanMapper;
        this.webClient = webClient;
    }

    @Override
    public LoanDto findById(long id) {
        return loanMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public boolean hasActiveLoan(long userId) {
        return repository.findLoanByUserIdAndStatus(userId, LoanStatus.ACTIVE) != null;
    }

    @Override
    public List<LoanDto> findByStatus(LoanStatus status) {
        List<Loan> entities = repository.findLoanByStatus(status);
        return loanMapper.toDto(entities);
    }

    @Override
    public List<LoanDto> findAll() {
        return loanMapper.toDto(repository.findAll());
    }

    @Override
    public LoanDto updateLoan(LoanDto loanDto) {
        Loan loan = repository.findById(loanDto.getLoanId()).orElseThrow(ResourceNotFoundException::new);
        BeanUtils.copyProperties(loanDto, loan);
        return loanMapper.toDto(repository.save(loan));
    }

    @Override
    public LoanDto createLoan(LoanDto loanDto) {
        Loan loan = loanMapper.toEntity(loanDto);
        return loanMapper.toDto(repository.save(loan));
    }

    @Override
    public void updatePaymentStatus(Long loanId) {
        Loan loan = repository.findById(loanId).orElseThrow(ResourceNotFoundException::new);
        loan.setStatus(LoanStatus.PAID);
        repository.save(loan);
    }

    private Long getLoanLimit(String accountId) {

        return webClient.get()
                .uri("http://localhost:9094/api/wallet/" + accountId + "/loan-limit")
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }

    private String postPayment(PaymentDto paymentDto) {

        return webClient.post()
                .uri("http://localhost:9092/api/payment/")
                .bodyValue(paymentDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private String sendNotification(Long amount) {

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setMail("user@test.com");
        notificationDto.setPhoneNumber("+254700000000");
        notificationDto.setMessage(String.format("Your loan of %d has been approved and credited into your account.", amount));

        return webClient.post()
                .uri("http://localhost:9093/api/notification/notify")
                .bodyValue(notificationDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


    @Override
    public List<LoanProductDto> requestLoan(LoanRequestDto loanRequestDto) {
        if (hasActiveLoan(loanRequestDto.getUserId()))
            throw new ExistingLoanException("You have an existing loan.");

        Long loanLimit = getLoanLimit(loanRequestDto.getWalletAccountId());

        // get user loan limit
        return loanProductService.getLoanLadder(loanLimit);
    }

    @Override
    public LoanDto processLoanRequest(LoanRequestDto loanRequestDto) {

        LoanProductDto loanProduct = loanProductService.findById(loanRequestDto.getLoanProductId());
        float interestRate = loanProduct.getInterest();
        Long amount = loanProduct.getMaxAllowableLimit();
        int tenure = loanProduct.getTenureInDays();
        Calendar calendar = Calendar.getInstance();

        float interest = getInterestAmount(interestRate, amount);
        float totalAmount = interest + amount;

        LoanDto loanDto = new LoanDto();
        loanDto.setLoanAmount(loanProduct.getMaxAllowableLimit());
        loanDto.setLoanProductId(loanProduct.getId());
        loanDto.setStatus(LoanStatus.ACTIVE);
        loanDto.setInterest(interest);
        loanDto.setTotalAmount(totalAmount);
        loanDto.setUserId(loanRequestDto.getUserId());
        loanDto.setCreatedOn(calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, tenure);
        loanDto.setDueDate(calendar.getTime());

        loanDto = createLoan(loanDto);

        // create new payment record

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setLoanId(loanDto.getLoanId());
        paymentDto.setWalletAccountId(loanRequestDto.getWalletAccountId());
        paymentDto.setDueDate(calendar.getTime());
        paymentDto.setEntryDate(new Date());
        paymentDto.setAmount(totalAmount);
        paymentDto.setBalance(totalAmount);

        postPayment(paymentDto);

        sendNotification(amount);

        return loanDto;
    }

    private float getInterestAmount(float interest, long amount) {
        return ((interest / 100) * amount);
    }


}