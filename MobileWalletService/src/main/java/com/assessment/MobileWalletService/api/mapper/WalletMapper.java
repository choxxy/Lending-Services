package com.assessment.MobileWalletService.api.mapper;

import com.assessment.CommonService.api.dto.WalletDto;
import com.assessment.CommonService.api.mapper.EntityMapper;
import com.assessment.MobileWalletService.api.entity.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper extends EntityMapper<WalletDto, Wallet> {
}