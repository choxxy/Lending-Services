package com.assessment.MobileWalletService.api.service;

import com.assessment.CommonService.api.dto.LoanLimitDto;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class WalletServiceImpl implements WalletService {
    public WalletServiceImpl() {
    }

    @Override
    public LoanLimitDto getLoanLimit(String accountId) {
        List<Long> limits = Lists.newArrayList(1000L, 1500L, 2000L, 2500L);
        Collections.shuffle(limits);
        return new LoanLimitDto(accountId, limits.get(0));
    }
}