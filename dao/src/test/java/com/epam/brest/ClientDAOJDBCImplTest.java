package com.epam.brest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml","classpath*:test-jdbs-conf.xml"})
class ClientDAOJDBCImplTest {

    private ClientDAOJDBCImpl clientDAOJDBC;

    public ClientDAOJDBCImplTest(@Autowired ClientDAO clientDAOJDBC) {
        this.clientDAOJDBC = (ClientDAOJDBCImpl) clientDAOJDBC;
    }

    @Test
    void findAll() {
        assertNotNull(clientDAOJDBC);
        assertNotNull(clientDAOJDBC.findAll());

    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}