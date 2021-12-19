package com.epam.brest.rest;

import com.epam.brest.model.Client;
import com.epam.brest.rest.exception.CustomExceptionHandler;
import com.epam.brest.rest.exception.ErrorResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


import static com.epam.brest.model.constants.ClientConstants.*;
import static com.epam.brest.rest.exception.CustomExceptionHandler.CLIENT_NOT_FOUND;
import static com.epam.brest.rest.exception.CustomExceptionHandler.VALIDATION_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
public class ClientControllerIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientControllerIT.class);

    public static final String CLIENT_ENDPOINT = "/clients";

    @Autowired
    private ClientController clientController;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;


    MockMvcClientService clientService = new MockMvcClientService();

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    public void shouldFindAllClients() throws Exception {

        // given
        Client client = new Client(RandomStringUtils.randomAlphabetic(CLIENT_PASSPORT_SIZE),
                RandomStringUtils.randomAlphabetic(CLIENT_FIRSTNAME_SIZE),
                RandomStringUtils.randomAlphabetic(CLIENT_LASTNAME_SIZE));
        Integer id = clientService.create(client);

        // when
        List<Client> clients = clientService.findAll();

        // then
        assertNotNull(clients);
        assertTrue(clients.size() > 0);
    }

    @Test
    public void shouldCreateDepartment() throws Exception {
        Client client = new Client(RandomStringUtils.randomAlphabetic(CLIENT_PASSPORT_SIZE),
                RandomStringUtils.randomAlphabetic(CLIENT_FIRSTNAME_SIZE),
                RandomStringUtils.randomAlphabetic(CLIENT_LASTNAME_SIZE));
        Integer id = clientService.create(client);
        assertNotNull(id);
    }

    @Test
    public void shouldFindDepartmentById() throws Exception {

        // given
        Client client = new Client(RandomStringUtils.randomAlphabetic(CLIENT_PASSPORT_SIZE),
                RandomStringUtils.randomAlphabetic(CLIENT_FIRSTNAME_SIZE),
                RandomStringUtils.randomAlphabetic(CLIENT_LASTNAME_SIZE));
        Integer id = clientService.create(client);

        assertNotNull(id);

        // when
        Optional<Client> optionalDepartment = clientService.findById(id);

        // then
        assertTrue(optionalDepartment.isPresent());
        assertEquals(optionalDepartment.get().getClientId(), id);
        assertEquals(client.getPassport(), optionalDepartment.get().getPassport());
    }

    @Test
    public void shouldUpdateClient() throws Exception {

        // given
        Client client = new Client(RandomStringUtils.randomAlphabetic(CLIENT_PASSPORT_SIZE),
                RandomStringUtils.randomAlphabetic(CLIENT_FIRSTNAME_SIZE),
                RandomStringUtils.randomAlphabetic(CLIENT_LASTNAME_SIZE));
        Integer id = clientService.create(client);
        assertNotNull(id);

        Optional<Client> departmentOptional = clientService.findById(id);
        assertTrue(departmentOptional.isPresent());

        departmentOptional.get()
                .setPassport(RandomStringUtils.randomAlphabetic(CLIENT_PASSPORT_SIZE))
                .setFirstName(RandomStringUtils.randomAlphabetic(CLIENT_FIRSTNAME_SIZE))
                .setLastname(RandomStringUtils.randomAlphabetic(CLIENT_LASTNAME_SIZE));

        // when
        int result = clientService.update(departmentOptional.get());

        // then
        assertTrue(1 == result);

        Optional<Client> updatedDepartmentOptional = clientService.findById(id);
        assertTrue(updatedDepartmentOptional.isPresent());
        assertEquals(updatedDepartmentOptional.get().getClientId(), id);
        assertEquals(updatedDepartmentOptional.get().getPassport(), departmentOptional.get().getPassport());

    }

    @Test
    public void shouldDeleteClient() throws Exception {
        // given
        Client client = new Client(RandomStringUtils.randomAlphabetic(CLIENT_PASSPORT_SIZE),
                RandomStringUtils.randomAlphabetic(CLIENT_FIRSTNAME_SIZE),
                RandomStringUtils.randomAlphabetic(CLIENT_LASTNAME_SIZE));
        Integer id = clientService.create(client);

        List<Client> clients = clientService.findAll();
        assertNotNull(clients);

        // when
        int result = clientService.delete(id);

        // then
        assertTrue(1 == result);

        List<Client> currentClients = clientService.findAll();
        assertNotNull(currentClients);

        assertTrue(clients.size() - 1 == currentClients.size());
    }

    @Test
    public void shouldReturnDepartmentNotFoundError() throws Exception {

        LOGGER.debug("shouldReturnClientNotFoundError()");
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.get(CLIENT_ENDPOINT + "/999999")
                                .accept(MediaType.APPLICATION_JSON)
                        ).andExpect(status().isNotFound())
                        .andReturn().getResponse();
        assertNotNull(response);
        ErrorResponse errorResponse = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        assertNotNull(errorResponse);
        assertEquals(errorResponse.getMessage(), CLIENT_NOT_FOUND);
    }

    @Test
    public void shouldFailOnCreateClientWithDuplicatePassport() throws Exception {
        Client client1 = new Client(RandomStringUtils.randomAlphabetic(CLIENT_PASSPORT_SIZE),
                RandomStringUtils.randomAlphabetic(CLIENT_FIRSTNAME_SIZE),
                RandomStringUtils.randomAlphabetic(CLIENT_LASTNAME_SIZE));
        Integer id = clientService.create(client1);
        assertNotNull(id);

        Client client2 = new Client(client1.getPassport(), client1.getFirstname(), client1.getLastname());

        MockHttpServletResponse response =
                mockMvc.perform(post(CLIENT_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(client2))
                                .accept(MediaType.APPLICATION_JSON)
                        ).andExpect(status().isUnprocessableEntity())
                        .andReturn().getResponse();

        assertNotNull(response);
        ErrorResponse errorResponse = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        assertNotNull(errorResponse);
        assertEquals(errorResponse.getMessage(), VALIDATION_ERROR);
    }

    class MockMvcClientService {

        public List<Client> findAll() throws Exception {
            LOGGER.debug("findAll()");
            MockHttpServletResponse response = mockMvc.perform(get(CLIENT_ENDPOINT)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);

            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Client>>() {
            });
        }

        public Optional<Client> findById(Integer id) throws Exception {

            LOGGER.debug("findById({})", id);
            MockHttpServletResponse response = mockMvc.perform(get(CLIENT_ENDPOINT + "/" + id)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return Optional.of(objectMapper.readValue(response.getContentAsString(), Client.class));
        }

        public Integer create(Client client) throws Exception {

            LOGGER.debug("create({})", client);
            String json = objectMapper.writeValueAsString(client);
            MockHttpServletResponse response =
                    mockMvc.perform(post(CLIENT_ENDPOINT)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json)
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isOk())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

        private int update(Client client) throws Exception {

            LOGGER.debug("update({})", client);
            MockHttpServletResponse response =
                    mockMvc.perform(put(CLIENT_ENDPOINT)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(client))
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isOk())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

        private int delete(Integer departmentId) throws Exception {

            LOGGER.debug("delete(id:{})", departmentId);
            MockHttpServletResponse response = mockMvc.perform(
                            MockMvcRequestBuilders.delete(new StringBuilder(CLIENT_ENDPOINT).append("/")
                                            .append(departmentId).toString())
                                    .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();

            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }
    }
}
