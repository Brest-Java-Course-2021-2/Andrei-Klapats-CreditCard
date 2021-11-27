package com.epam.brest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDAOJDBCImpl implements AccountDAO{
    private final Logger LOGGER = LogManager.getLogger(AccountDAOJDBCImpl.class);
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    public AccountDAOJDBCImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }

    @Override
    public List<Account> findAllAccountOfClient() {
        return null;
    }

    @Override
    public Account getAccountById(Integer clientId) {
        return null;
    }

    @Override
    public Integer create(Account account) {
        return null;
    }

    @Override
    public Integer update(Account account) {
        return null;
    }

    @Override
    public Integer delete(Integer accountId) {
        return null;
    }
}
