package com.epam.brest.service.rest;

import com.epam.brest.model.Client;
import com.epam.brest.service.config.ServiceRestTestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static com.epam.brest.model.constants.ClientConstants.CLIENT_PASSPORT_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@Import({ServiceRestTestConfig.class})
public class ClientServiceRestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceRestTest.class);

    public static final String CLIENTS_URL = "http://localhost:8088/clients";

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    ClientServiceRest clientService;

    @BeforeEach
    public void before() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        clientService = new ClientServiceRest(CLIENTS_URL, restTemplate);
    }

    @Test
    public void shouldFindAllClients() throws Exception {

        LOGGER.debug("shouldFindAllClients()");
        // given
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CLIENTS_URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Arrays.asList(create(0), create(1))))
                );

        // when
        List<Client> clients = clientService.findAll();

        // then
        mockServer.verify();
        assertNotNull(clients);
        assertTrue(clients.size() > 0);
    }

    @Test
    public void shouldCreateClient() throws Exception {

        LOGGER.debug("shouldCreateClient()");
        // given
        Client client = new Client()
                .setFirstName(RandomStringUtils.randomAlphabetic(CLIENT_PASSPORT_SIZE)) // change constant
                .setLastname(RandomStringUtils.randomAlphabetic(CLIENT_PASSPORT_SIZE))//change constant
                .setPassport(RandomStringUtils.randomAlphabetic(CLIENT_PASSPORT_SIZE));

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CLIENTS_URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );
        // when
        Integer id = clientService.create(client);

        // then
        mockServer.verify();
        assertNotNull(id);
    }

    @Test
    public void shouldFindClientById() throws Exception {

        // given
        Integer id = 1;
        Client client = new Client()
                .setClientId(id)
                .setPassport(RandomStringUtils.randomAlphabetic(CLIENT_PASSPORT_SIZE));

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CLIENTS_URL + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(client))
                );

        // when
        Client resultClient = clientService.getClientById(id);

        // then
        mockServer.verify();
        assertNotNull(resultClient);
        assertEquals(resultClient.getClientId(), id);
        assertEquals(resultClient.getPassport(), client.getPassport());
    }

    @Test
    public void shouldUpdateClient() throws Exception {

        // given
        Integer id = 1;
        Client client = new Client()
                .setClientId(id)
                .setPassport(RandomStringUtils.randomAlphabetic(CLIENT_PASSPORT_SIZE));

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CLIENTS_URL)))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );

        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CLIENTS_URL + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(client))
                );

        // when
        int result = clientService.update(client);
        Client updatedClient = clientService.getClientById(id);

        // then
        mockServer.verify();
        assertTrue(1 == result);

        assertNotNull(updatedClient);
        assertEquals(updatedClient.getClientId(), id);
        assertEquals(updatedClient.getPassport(), client.getPassport());
    }

    @Test
    public void shouldDeleteClient() throws Exception {

        // given
        Integer id = 1;
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(CLIENTS_URL + "/" + id)))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );
        // when
        int result = clientService.delete(id);

        // then
        mockServer.verify();
        assertTrue(1 == result);
    }

    private Client create(int index) {
        Client client = new Client();
        client.setClientId(index);
        client.setPassport("AB" + index);
        return client;
    }
}

