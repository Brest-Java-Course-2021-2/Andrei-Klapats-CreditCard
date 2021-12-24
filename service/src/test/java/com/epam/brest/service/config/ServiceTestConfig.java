package com.epam.brest.service.config;

import com.epam.brest.dao.*;
import com.epam.brest.service.AccountService;
import com.epam.brest.service.ClientDtoService;
import com.epam.brest.service.ClientService;
import com.epam.brest.service.impl.AccountServiceImpl;
import com.epam.brest.service.impl.ClientDtoServiceImpl;
import com.epam.brest.service.impl.ClientServiceImpl;
import com.epam.brest.testdb.SpringJdbcConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ServiceTestConfig extends SpringJdbcConfig {

    @Bean
    ClientDtoDao clientDtoDao() {
        return new ClientDtoDaoJdbc(namedParameterJdbcTemplate());
    }

    @Bean
    ClientDtoService clientDtoService() {
        return new ClientDtoServiceImpl(clientDtoDao());
    }

    @Bean
    ClientDao clientDao() {
        return new ClientDaoJDBCImpl(namedParameterJdbcTemplate());
    }

    @Bean
    ClientService clientService() {
        return new ClientServiceImpl(clientDao());
    }

    @Bean
    AccountDao accountDao() {return new AccountDaoJDBSImpl(namedParameterJdbcTemplate()); }

    @Bean
    AccountService accountService(){return  new AccountServiceImpl(accountDao());}
}
