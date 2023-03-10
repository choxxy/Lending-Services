package com.assessment.MobileWalletService.api.controller;

import com.assessment.CommonService.api.dto.WalletDto;
import com.assessment.MobileWalletService.api.service.WalletService;
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

@RequestMapping("/api/wallet")
@RestController
@Slf4j
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated WalletDto walletDto) {
        walletService.save(walletDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDto> findById(@PathVariable("id") String id) {
        WalletDto wallet = walletService.findById(id);
        return ResponseEntity.ok(wallet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        Optional.ofNullable(walletService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        walletService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<WalletDto>> pageQuery(WalletDto walletDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<WalletDto> walletPage = walletService.findByCondition(walletDto, pageable);
        return ResponseEntity.ok(walletPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated WalletDto walletDto, @PathVariable("id") String id) {
        walletService.update(walletDto, id);
        return ResponseEntity.ok().build();
    }
}