package com.epam.brest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
public class ClientServiceImplTest {

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
        Client client = new Client("Ivan","ivanov","HB1234567");
        Integer newClientId = clientService.create(client);
        assertNotNull(newClientId);
        assertEquals(clientSizeBefore, clientService.count() - 1);
    }

    @Test
    void tryToCreateEqualsClient() {
        assertNotNull(clientService);
        Client client = new Client("Aleksandr","Aleksandrov","HB2345678");

        assertThrows(IllegalArgumentException.class, () -> {
            clientService.create(client);
            clientService.create(client);
        });
   }
}
