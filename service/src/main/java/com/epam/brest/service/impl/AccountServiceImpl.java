package com.epam.brest.service.impl;

import com.epam.brest.dao.AccountDao;
import com.epam.brest.model.Account;
import com.epam.brest.service.AccountService;
import com.epam.brest.service.exceptions.AccountNotFountException;
import com.epam.brest.service.exceptions.ClientNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final Logger logger = LogManager.getLogger(AccountServiceImpl.class);
    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        logger.trace("findAll()");
        return accountDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccountById(Integer accountId) {
        logger.debug("Get account by account id = {}", accountId);
        try {
            return this.accountDao.getAccountByAccountId(accountId);
        } catch (EmptyResultDataAccessException e) {
            throw new AccountNotFountException(accountId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccountByClientId(Integer clientId) {
        logger.debug("Get account by client id = {}", clientId);
        try {
            return this.accountDao.getAccountByClientId(clientId);
        } catch (EmptyResultDataAccessException e) {
            throw new AccountNotFountException(clientId);
        }
    }

    @Override
    @Transactional
    public Integer create(Account account) {
        logger.debug("create({})", account);
        return this.accountDao.create(account);
    }

    @Override
    @Transactional
    public Integer update(Account account) {
        logger.debug("update({})", account);
        return this.accountDao.updateAccountBalance(account);
    }

    @Override
    @Transactional
    public Integer delete(Integer accountId) {
        logger.debug("delete account with id = {}", accountId);
        return this.accountDao.delete(accountId);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer count() {
        logger.debug("count()");
        return this.accountDao.count();
    }
}
