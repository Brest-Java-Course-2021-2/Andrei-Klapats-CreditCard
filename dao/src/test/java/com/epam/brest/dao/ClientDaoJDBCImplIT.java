package com.epam.brest.dao;

import com.epam.brest.model.Client;
import com.epam.brest.testdb.SpringJdbcConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJdbcTest
@Import({ClientDaoJDBCImpl.class})
@PropertySource({"classpath:dao.properties"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
class ClientDaoJDBCImplIT {

    private final Logger logger = LogManager.getLogger(ClientDaoJDBCImplIT.class);

    private ClientDaoJDBCImpl clientDaoJDBC;

    public ClientDaoJDBCImplIT(@Autowired ClientDao clientDaoJDBC) {
        this.clientDaoJDBC = (ClientDaoJDBCImpl) clientDaoJDBC;
    }

    @Test
    void findAll() {
        logger.debug("Execute test: findAll()");
        assertNotNull(clientDaoJDBC);
        assertNotNull(clientDaoJDBC.findAll());
    }

    @Test
    void create() {
        assertNotNull(clientDaoJDBC);
        int clientsSizeBefore = clientDaoJDBC.count();
        Client client = new Client("AB123459", "Ivan", "Ivanov");
        Integer newClientId = clientDaoJDBC.create(client);
        assertNotNull(newClientId);
        assertEquals((int) clientsSizeBefore, clientDaoJDBC.count() - 1);
    }

    @Test
    void tryToCreateEqualsClients() {
        assertNotNull(clientDaoJDBC);
        Client client = new Client("AB123459", "Ivan", "Ivanov");

        assertThrows(IllegalArgumentException.class, () -> {
            clientDaoJDBC.create(client);
            clientDaoJDBC.create(client);
        });
    }

    @Test
    void getClientById() {
        List<Client> clients = clientDaoJDBC.findAll();
        if (clients.size() == 0) {
            clientDaoJDBC.create(new Client("AB123459", "Ivan", "Ivanov"));
            clients = clientDaoJDBC.findAll();
        }

        Client clientSrc = clients.get(0);
        Client clientDst = clientDaoJDBC.getClientById(clientSrc.getClientId());
        assertEquals(clientSrc.getPassport(), clientDst.getPassport());
    }

    @Test
    void updateClient() {
        List<Client> clients = clientDaoJDBC.findAll();
        if (clients.size() == 0) {
            clientDaoJDBC.create(new Client("Test0000", "Test", "Test"));
            clients = clientDaoJDBC.findAll();
        }

        Client clientSrc = clients.get(0);
        clientSrc.setPassport(clientSrc.getPassport() + "_TEST");
        clientSrc.setLastname(clientSrc.getLastname() + "_TEST");
        clientDaoJDBC.update(clientSrc);

        Client clientDst = clientDaoJDBC.getClientById(clientSrc.getClientId());
        assertEquals(clientSrc.getPassport(), clientDst.getPassport());
    }

    @Test
    void deleteClient() {
        clientDaoJDBC.create(new Client("Test0000", "Test", "Test"));
        List<Client> clients = clientDaoJDBC.findAll();

        clientDaoJDBC.delete(clients.get(clients.size() - 1).getClientId());
        assertEquals(clients.size() - 1, clientDaoJDBC.findAll().size());
    }

    @Test
    void shouldCount() {
        assertNotNull(clientDaoJDBC);
        Integer quantity = clientDaoJDBC.count();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        assertEquals(Integer.valueOf(3), quantity);
    }
}
