package com.assessment.api.LoanOfferService.api.controller;

import com.assessment.CommonService.api.dto.LoanProductDto;
import com.assessment.CommonService.api.dto.LoanRequestDto;
import com.assessment.CommonService.api.enums.LoanStatus;
import com.assessment.api.LoanOfferService.LoanBuilder;
import com.assessment.api.LoanOfferService.LoanProductBuilder;
import com.assessment.api.LoanOfferService.LoanRequestDtoBuilder;
import com.assessment.api.LoanOfferService.api.service.LoanService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

class LoanControllerTest {

    private static final String ENDPOINT_URL = "/api/loan";
    private static final String PAYMENT_STATUS_ENDPOINT_URL = "/api/loan/{loanId}/update-payment-status/{status}";


    @InjectMocks
    private LoanController loanController;
    @Mock
    private LoanService loanService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(loanController)
                .build();
    }

    @Test
    void whenPostRequestToFindByIdAndValidLoanId_thenCorrectResponse() throws Exception {
        Mockito.when(loanService.findById(ArgumentMatchers.anyLong())).thenReturn(LoanBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.loanId", Is.is(1)));
        Mockito.verify(loanService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(loanService);
    }

    @Test
    void whenPostRequestToFindByIdAndInValidLoanId_thenCorrectResponse() throws Exception {
        Mockito.when(loanService.findById(ArgumentMatchers.anyLong())).thenThrow(new ResourceNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/-1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        Mockito.verify(loanService, Mockito.times(1)).findById(-1L);
        Mockito.verifyNoMoreInteractions(loanService);
    }

    @Test
    void updatePaymentStatus() throws Exception {
        Mockito.doNothing().when(loanService).updatePaymentStatus(ArgumentMatchers.anyLong(), ArgumentMatchers.eq(LoanStatus.PAID));

        String url = PAYMENT_STATUS_ENDPOINT_URL.replace("{loanId}", "1").replace("{status}",LoanStatus.PAID.name());
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(loanService, Mockito.times(1)).updatePaymentStatus(1L, LoanStatus.PAID);
        Mockito.verifyNoMoreInteractions(loanService);
    }


    @Test
    void whenPostRequestToRequestLoanAndValidLoanRequestDto_thenCorrectResponse() throws Exception {
        List<LoanProductDto> list = Collections.singletonList(LoanProductBuilder.getDto());
        Mockito.when(loanService.requestLoan(LoanRequestDtoBuilder.getDto())).thenReturn(list);

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL + "/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(LoanRequestDtoBuilder.getDto())))
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1))
                );

        Mockito.verify(loanService, Mockito.times(1)).requestLoan(ArgumentMatchers.any(LoanRequestDto.class));
        Mockito.verifyNoMoreInteractions(loanService);
    }

    @Test
    void whenPostRequestToRequestLoanAndInValidLoanRequestDto_thenCorrectResponse() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_URL + "/")
                        .content(CustomUtils.asJsonString(LoanRequestDtoBuilder.getDtoNoUserId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }


    @Test
    void processLoanRequest() throws Exception {
        Mockito.when(loanService.processLoanRequest(ArgumentMatchers.any(LoanRequestDto.class))).thenReturn(LoanBuilder.getDto());
        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL + "/process-loan-request")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(LoanRequestDtoBuilder.getDto())))
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.loanId", Is.is(1)),
                        MockMvcResultMatchers.status().isOk()
                );

        Mockito.verify(loanService, Mockito.times(1)).processLoanRequest(ArgumentMatchers.any(LoanRequestDto.class));
        Mockito.verifyNoMoreInteractions(loanService);
    }
}