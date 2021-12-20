package com.epam.brest.dao;


import com.epam.brest.model.Account;
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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void getAccountByClientId() {
        List<Account> accountList = accountDaoJDBS.findAll();
//        if(accountList.size()==0){
//
//        }


//        Account accountSrc = new Account(1, "BY01QWERTY3014124583883000", new BigDecimal(1000), new Date(),1);
        Account accountSrc = accountList.get(0);
        Account accountDst = accountDaoJDBS.getAccountByClientId(accountSrc.getClientID());
        assertEquals(accountSrc.getClientID(),accountDst.getClientID());
    }
}
