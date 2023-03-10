package com.assessment.MobileWalletService.api.service;

import com.assessment.CommonService.api.dto.WalletDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface WalletService {

    public WalletDto save(WalletDto walletDto);

    public void deleteById(String id);

    public WalletDto findById(String id);

    public Page<WalletDto> findByCondition(WalletDto walletDto, Pageable pageable);

    public WalletDto update(WalletDto walletDto, String id);
}