package com.assessment.api.MobileWalletService;


import com.assessment.CommonService.api.dto.WalletDto;

import java.util.Collections;
import java.util.List;

public class WalletBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static WalletDto getDto() {
        WalletDto dto = new WalletDto();
        dto.setWalletId("1");
        return dto;
    }
}