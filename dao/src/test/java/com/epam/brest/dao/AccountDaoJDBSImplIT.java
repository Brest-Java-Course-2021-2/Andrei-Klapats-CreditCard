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
        Account accountDst = accountDaoJDBS.getAccountByClientId(accountSrc.getClientId());
        assertEquals(accountSrc.getClientId(),accountDst.getClientId());
    }


    @Test
    void getAccountById(){
        List<Account> accountList = accountDaoJDBS.findAll();


        Account accountSrc = accountList.get(0);
        Account accountDst = accountDaoJDBS.getAccountByAccountId(accountSrc.getAccountId());
        assertEquals(accountSrc.getAccountId(),accountDst.getAccountId());
    }

    @Test
    void getAccountByDateOfCreate(){
        List<Account> accountList = accountDaoJDBS.findAll();
        Account accountSrc = accountList.get(0);
        Account accountDst = accountDaoJDBS.getAccountByDateOfCreate(accountSrc.getDateOfCreate());
        assertEquals(accountSrc.getDateOfCreate(),accountDst.getDateOfCreate());
    }

    @Test
    void createAccount(){
        assertNotNull(accountDaoJDBS);
        int accountSizeBefore = accountDaoJDBS.count();
        Account account = new Account((int)Math.random()*10, "BY00QWERTY3014124583883000", new BigDecimal(1000),
                new Date(),2);
        Integer newAccountId = accountDaoJDBS.create(account);
        assertNotNull(newAccountId);
        assertEquals((int) accountSizeBefore, accountDaoJDBS.count() - 1);
    }

    @Test
    void updateBalance() {
        List<Account> account = accountDaoJDBS.findAll();
        if (account.size() == 0) {
            accountDaoJDBS.create(new Account((int)Math.random()*10, "BY00QWERTY3014124583883000", new BigDecimal(1000),
                    new Date(),2));
            account = accountDaoJDBS.findAll();
        }

        Account accountSrc = account.get(0);
        accountSrc.setAccountBalance(accountSrc.getAccountBalance().add(BigDecimal.valueOf(15.15)));

        accountDaoJDBS.updateAccountBalance(accountSrc);

        Account accountDst = accountDaoJDBS.getAccountByClientId(accountSrc.getClientId());
        assertEquals(accountSrc.getAccountBalance(), accountDst.getAccountBalance());
    }
    @Test
    void deleteAccount() {
        accountDaoJDBS.create(new Account((int)Math.random()*10, "BY00QWERTY3014124583883000", new BigDecimal(1000),
                new Date(),2));
        List<Account> account = accountDaoJDBS.findAll();

        accountDaoJDBS.delete(account.get(account.size() - 1).getClientId());
        assertEquals(account.size() - 1, accountDaoJDBS.findAll().size());
    }
}
