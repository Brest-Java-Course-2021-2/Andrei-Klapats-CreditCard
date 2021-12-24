package com.epam.brest.service.impl;


import com.epam.brest.model.Account;
import com.epam.brest.model.Client;
import com.epam.brest.service.AccountService;
import com.epam.brest.service.config.ServiceTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import({ServiceTestConfig.class})
@PropertySource({"classpath:dao.properties"})
@Transactional
public class AccountServiceImplIT {
    @Autowired
    AccountService accountService;

    @Test
    void shouldCount() {
        assertNotNull(accountService);
        Integer quantity = accountService.count();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        assertEquals(Integer.valueOf(5), quantity);
    }

    @Test
    void create() {
        assertNotNull(accountService);
        Integer accountSizeBefore = accountService.count();
        assertNotNull(accountSizeBefore);
        Account account = new Account((int)Math.random()*10, "BY00QWERTY3014124583883000", new BigDecimal(1000),
                new Date(),2);
        Integer newAccountId = accountService.create(account);
        assertNotNull(newAccountId);
        assertEquals(accountSizeBefore, accountService.count() - 1);
    }

    @Test
    void tryToCreateEqualsAccounts() {
        assertNotNull(accountService);
        Account account = new Account((int)Math.random()*10, "BY00QWERTY3014124583883000", new BigDecimal(1000),
                new Date(),2);

        assertThrows(IllegalArgumentException.class, () -> {
            accountService.create(account);
            accountService.create(account);
        });
    }

}
