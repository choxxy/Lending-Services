package com.assessment.api.MobileWalletService;


import com.assessment.CommonService.api.dto.WalletDto;
import com.assessment.MobileWalletService.api.controller.WalletController;
import com.assessment.MobileWalletService.api.service.WalletService;
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
public class WalletControllerTest {
    private static final String ENDPOINT_URL = "/api/wallet";
    @InjectMocks
    private WalletController walletController;
    @Mock
    private WalletService walletService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(walletController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }


    @Test
    public void getById() throws Exception {
        Mockito.when(walletService.findById(ArgumentMatchers.anyString())).thenReturn(WalletBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(walletService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(walletService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(walletService.save(ArgumentMatchers.any(WalletDto.class))).thenReturn(WalletBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(WalletBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(walletService, Mockito.times(1)).save(ArgumentMatchers.any(WalletDto.class));
        Mockito.verifyNoMoreInteractions(walletService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(walletService.update(ArgumentMatchers.any(), ArgumentMatchers.anyString())).thenReturn(WalletBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(WalletBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(walletService, Mockito.times(1)).update(ArgumentMatchers.any(WalletDto.class), ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(walletService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(walletService).deleteById(ArgumentMatchers.anyString());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(WalletBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(walletService, Mockito.times(1)).deleteById(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(walletService);
    }
}