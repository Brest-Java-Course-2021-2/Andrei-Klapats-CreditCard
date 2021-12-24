package com.epam.brest.service.impl;

import com.epam.brest.model.Client;
import com.epam.brest.service.ClientService;
import com.epam.brest.service.config.ServiceTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@Import({ServiceTestConfig.class})
@PropertySource({"classpath:dao.properties"})
@Transactional
class ClientServiceImplIT {

    @Autowired
    ClientService clientService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCount() {
        assertNotNull(clientService);
        Integer quantity = clientService.count();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        assertEquals(Integer.valueOf(3), quantity);
    }

    @Test
    void create() {
        assertNotNull(clientService);
        Integer clientSizeBefore = clientService.count();
        assertNotNull(clientSizeBefore);
        Client client = new Client("AB0010203","Ivan","Petrov");
        Integer newClientId = clientService.create(client);
        assertNotNull(newClientId);
        assertEquals(clientSizeBefore, clientService.count() - 1);
    }

    @Test
    void tryToCreateEqualsClients() {
        assertNotNull(clientService);
        Client client = new Client("AB0010203","Ivan","Petrov");

        assertThrows(IllegalArgumentException.class, () -> {
            clientService.create(client);
            clientService.create(client);
        });
    }
}
