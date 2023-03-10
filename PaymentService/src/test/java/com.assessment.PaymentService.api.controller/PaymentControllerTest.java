package com.assessment.PaymentService.api.controller;

import com.assessment.CommonService.api.dto.PaymentDto;
import com.assessment.PaymentService.api.service.PaymentService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

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
    public void findAllByPage() throws Exception {
        Page<PaymentDto> page = new PageImpl<>(Collections.singletonList(PaymentBuilder.getDto()));

        Mockito.when(paymentService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(paymentService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(paymentService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(paymentService.findById(ArgumentMatchers.anyLong())).thenReturn(PaymentBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(paymentService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(paymentService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(paymentService.save(ArgumentMatchers.any(PaymentDto.class))).thenReturn(PaymentBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(PaymentBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(paymentService, Mockito.times(1)).save(ArgumentMatchers.any(PaymentDto.class));
        Mockito.verifyNoMoreInteractions(paymentService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(paymentService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(PaymentBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(PaymentBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(paymentService, Mockito.times(1)).update(ArgumentMatchers.any(PaymentDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(paymentService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(paymentService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(PaymentBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(paymentService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(paymentService);
    }
}