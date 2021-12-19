package com.epam.brest.service.rest;

import com.epam.brest.model.dto.ClientDto;
import com.epam.brest.service.config.ServiceRestTestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@Import({ServiceRestTestConfig.class})
class ClientDtoServiceRestTest {

    private final Logger logger = LogManager.getLogger(ClientDtoServiceRestTest.class);

    public static final String URL = "http://localhost:8088/client-dtos";

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    ClientDtoServiceRest clientDtoService;

    @BeforeEach
    public void before() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        clientDtoService = new ClientDtoServiceRest(URL, restTemplate);
    }

    @Test
    void shouldFindAllWithAvgBalance() throws Exception {

        logger.debug("shouldFindAllClients()");
        // given
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Arrays.asList(create(0), create(1))))
                );

        // when
        List<ClientDto> list = clientDtoService.findAllWithAvgBalance();

        // then
        mockServer.verify();
        assertNotNull(list);
        assertTrue(list.size() > 0);

    }

    private ClientDto create(int index) {
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(index);
        clientDto.setFirstName("Ivan" + index);
        clientDto.setLastName("Ivanov" + index);
        clientDto.setPassportNumber("AB1234565" + index);
        clientDto.setAvgBalance(BigDecimal.valueOf(100 + index));
        return clientDto;
    }
}
