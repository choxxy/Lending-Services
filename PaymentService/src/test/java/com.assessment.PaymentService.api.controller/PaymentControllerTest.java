package com.assessment.PaymentService.api.controller;

import com.assessment.CommonService.api.dto.PaymentDto;
import com.assessment.PaymentService.api.service.PaymentService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PaymentControllerTest {
    private static final String ENDPOINT_URL = "/api/payment";
    @InjectMocks
    private PaymentController paymentController;
    @Mock
    private PaymentService paymentService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(paymentController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }


    @Test
    public void findById() throws Exception {
        Mockito.when(paymentService.findById(ArgumentMatchers.anyLong())).thenReturn(PaymentDtoBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(paymentService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(paymentService);
    }

    @Test
    public void makePayment() throws Exception {
        Mockito.doNothing().when(paymentService).makePayment(ArgumentMatchers.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1/make-payment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(paymentService, Mockito.times(1)).makePayment(ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(paymentService);
    }

}