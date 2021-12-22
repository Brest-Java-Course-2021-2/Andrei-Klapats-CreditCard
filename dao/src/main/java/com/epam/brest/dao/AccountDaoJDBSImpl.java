package com.epam.brest.dao;

import com.epam.brest.model.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    private String sqlGetAccountByClientId;
    @Value("${SQL_ACCOUNT_BY_ID}")
    private String sqlGetAccountById;
    @Value("${SQL_ACCOUNT_BY_CREATE_DATE}")
    private String sqlGetAccountByDateOfCreate;
    @Value("${SQL_CREATE_ACCOUNT}")
    private String sqlCreateAccount;
    @Value("${SQL_CHECK_UNIQUE_ACCOUNT}")
    private String sqlCheckUniqueAccount;
    @Value("${SQL_ACCOUNT_COUNT}")
    public String sqlAccountCount;

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

        return namedParameterJdbcTemplate.queryForObject(sqlGetAccountByClientId,sqlParameterSource,new AccountRowMapper());
    }

    @Override
    public Account getAccountByAccountId(Integer accountId) {
        logger.debug(" Get account by Account Id = {}",accountId);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("accountId", accountId);

        return namedParameterJdbcTemplate.queryForObject(sqlGetAccountById,sqlParameterSource,new AccountRowMapper());
    }

    @Override
    public Account getAccountByDateOfCreate(Date dateOfCreate) {
        logger.debug(" Get date of create = {}",dateOfCreate);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("dateOfCreate", dateOfCreate);
        return namedParameterJdbcTemplate.queryForObject(sqlGetAccountByDateOfCreate,sqlParameterSource,new AccountRowMapper());
    }

    @Override
    public Integer create(Account account) {
        logger.debug("Create account: {}", account);
        if (!ifAccountUnique(account.getAccountData())) {
            logger.warn("Account with the same data {} already exists.", account.getAccountData());
            throw new IllegalArgumentException("Account with the same data already exists in DB.");
        }

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("accountId", account.getAccountId())
                        .addValue("accountData", account.getAccountData())
                        .addValue("accountBalance", account.getAccountBalance())
                        .addValue("dateOfCreate",account.getDateOfCreate())
                        .addValue("clientId",account.getClientId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlCreateAccount, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }
    private boolean ifAccountUnique(String accountData) {
        logger.debug("Check Client passport: {} on unique", accountData);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("accountData", accountData);
        return namedParameterJdbcTemplate.queryForObject(sqlCheckUniqueAccount, sqlParameterSource, Integer.class) == 0;
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
        logger.debug("count()");
        return namedParameterJdbcTemplate
                .queryForObject(sqlAccountCount, new MapSqlParameterSource(), Integer.class);
    }


    private static class AccountRowMapper implements RowMapper<Account> {

        @Override
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            Account account = new Account();
            account.setAccountId(resultSet.getInt("accountId"));
            account.setAccountData(resultSet.getString("info"));
            account.setAccountBalance(resultSet.getBigDecimal("balance"));
            account.setDateOfCreate(resultSet.getDate("date_of_create"));
            account.setClientId(resultSet.getInt("clientId"));

            return account;
        }


    }
}
