package com.epam.brest.rest;

import com.epam.brest.model.dto.ClientDto;
import com.epam.brest.service.ClientDtoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class ClientDtoControllerTest {

    @InjectMocks
    private ClientDtoController clientDtoController;

    @Mock
    private ClientDtoService clientDtoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientDtoController)
                .build();
    }

    @AfterEach
    public void end() {
        Mockito.verifyNoMoreInteractions(clientDtoService);
    }

    @Test
    public void shouldFindAllWithAvgSalary() throws Exception {

        Mockito.when(clientDtoService.findAllWithAvgBalance()).thenReturn(Arrays.asList(create(0), create(1)));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/client-dtos")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].clientId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].passport", Matchers.is("pd001")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstname", Matchers.is("firstname1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastname", Matchers.is("lastname1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].avgBalance", Matchers.is(1000)))


                .andExpect(MockMvcResultMatchers.jsonPath("$[1].clientId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].passport", Matchers.is("pd002")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstname", Matchers.is("firstname2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastname", Matchers.is("lastname2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].avgSalary", Matchers.is(2000)))
        ;

        Mockito.verify(clientDtoService).findAllWithAvgBalance();
    }

    private ClientDto create(int index) {
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(index);
        clientDto.setPassportNumber("pd010101" + index);
        clientDto.setFirstName("Ivan" + index);
        clientDto.setLastName("Ivanov" + index);
        clientDto.setAvgBalance(BigDecimal.valueOf(100 + index));
        return clientDto;
    }
}
