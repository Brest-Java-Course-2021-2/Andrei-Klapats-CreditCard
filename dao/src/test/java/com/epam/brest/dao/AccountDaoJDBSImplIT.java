package com.epam.brest.dao;


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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJdbcTest
@Import({AccountDaoJDBSImpl.class})
@PropertySource({"classpath:dao.properties"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
public class AccountDaoJDBSImplIT {


    private final Logger logger = LogManager.getLogger(AccountDaoJDBSImpl.class);

    private AccountDaoJDBSImpl accountDaoJDBS;

    public AccountDaoJDBSImplIT(@Autowired AccountDao accountDaoJDBS) {
        this.accountDaoJDBS = (AccountDaoJDBSImpl) accountDaoJDBS;
    }


    @Test
    void findAll() {
        logger.debug("Execute test: findAll()");
        assertNotNull(accountDaoJDBS);
        assertNotNull(accountDaoJDBS.findAll());
    }
}
