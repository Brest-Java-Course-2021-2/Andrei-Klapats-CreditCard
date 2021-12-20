package com.epam.brest.dao;

import com.epam.brest.model.Account;
import com.epam.brest.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


@Component
public class AccountDaoJDBSImpl implements AccountDao {


    private final Logger logger = LogManager.getLogger(AccountDaoJDBSImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public AccountDaoJDBSImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Value("${SQL_ALL_ACCOUNTS}")
    private String sqlGetAllAccounts;
    @Value("${SQL_ACCOUNT_BY_CLIENT_ID}")
    private String sqlAccountByClientId;

    @Override
    public List<Account> findAll() {
        logger.debug("Start: findAll()");
        return namedParameterJdbcTemplate.query(sqlGetAllAccounts, new AccountRowMapper());
    }

    @Override
    public Account getAccountByClientId(Integer clientId) {
        logger.debug(" Get account by Client Id = {}",clientId);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("clientId", clientId);


        //TO DO: fix problem when are return more than 1 object

        return namedParameterJdbcTemplate.queryForObject(sqlAccountByClientId,sqlParameterSource,new AccountRowMapper());
    }

    @Override
    public Account getAccountByAccountId(Integer accountId) {
        return null;
    }

    @Override
    public Account getAccountByDateOfCreate(Date dateOfCreate) {
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

    @Override
    public Integer count() {
        return null;
    }


    private static class AccountRowMapper implements RowMapper<Account> {

        @Override
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            Account account = new Account();
            account.setAccountId(resultSet.getInt("accountId"));
            account.setAccountData(resultSet.getString("info"));
            account.setAccountBalance(resultSet.getBigDecimal("balance"));
            account.setDateOfCreate(resultSet.getDate("date_of_create"));
            account.setClientID(resultSet.getInt("clientId"));

            return account;
        }


    }
}
