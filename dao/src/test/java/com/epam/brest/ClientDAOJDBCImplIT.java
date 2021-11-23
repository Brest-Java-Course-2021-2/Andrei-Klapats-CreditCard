package com.epam.brest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
@Transactional
@Rollback
public class ClientDAOJDBCImplIT {
    private final Logger logger = LogManager.getLogger(ClientDAOJDBCImplIT.class);

    private ClientDAOJDBCImpl clientDAOJDBC;

    public ClientDAOJDBCImplIT(@Autowired ClientDAO clientDAOJDBC) {
        this.clientDAOJDBC = (ClientDAOJDBCImpl) clientDAOJDBC;
    }

    @Test
    void findAll() {
        logger.debug("Execute test: findAll()");
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
