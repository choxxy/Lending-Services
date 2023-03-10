package com.assessment.MobileWalletService.api.service;

import com.assessment.CommonService.api.dto.WalletDto;
import com.assessment.MobileWalletService.api.entity.Wallet;
import com.assessment.MobileWalletService.api.mapper.WalletMapper;
import com.assessment.MobileWalletService.api.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class WalletServiceImpl implements WalletService {
    private final WalletRepository repository;
    private final WalletMapper walletMapper;

    public WalletServiceImpl(WalletRepository repository, WalletMapper walletMapper) {
        this.repository = repository;
        this.walletMapper = walletMapper;
    }

    public WalletDto save(WalletDto walletDto) {
        Wallet entity = walletMapper.toEntity(walletDto);
        return walletMapper.toDto(repository.save(entity));
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public WalletDto findById(String id) {
        return walletMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<WalletDto> findByCondition(WalletDto walletDto, Pageable pageable) {
        Page<Wallet> entityPage = repository.findAll(pageable);
        List<Wallet> entities = entityPage.getContent();
        return new PageImpl<>(walletMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public WalletDto update(WalletDto walletDto, String id) {
        WalletDto data = findById(id);
        Wallet entity = walletMapper.toEntity(walletDto);
        BeanUtils.copyProperties(data, entity);
        return save(walletMapper.toDto(entity));
    }
}