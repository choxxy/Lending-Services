package com.assessment.MobileWalletService.api.entity;

import com.assessment.CommonService.api.dto.WalletStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    String walletId;
    String walletName;
    float walletBalance;
    float loanLimit;
    float loanBalance;
    WalletStatus status;
}