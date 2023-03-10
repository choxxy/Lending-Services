package com.assessment.api.LoanOfferService.api.controller;


import com.assessment.api.LoanOfferService.LoanProductBuilder;
import com.assessment.api.LoanOfferService.api.service.LoanProductService;
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
public class LoanProductControllerTest {
    private static final String ENDPOINT_URL = "/api/loan-product";
    @InjectMocks
    private LoanProductController loanproductController;
    @Mock
    private LoanProductService loanproductService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(loanproductController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }


    @Test
    public void getById() throws Exception {
        Mockito.when(loanproductService.findById(ArgumentMatchers.anyLong())).thenReturn(LoanProductBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(loanproductService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(loanproductService);
    }


}