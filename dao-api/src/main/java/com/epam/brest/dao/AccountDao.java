package com.epam.brest.dao;

import com.epam.brest.model.Account;
import java.util.Date;
import java.util.List;

public interface AccountDao {

    List<Account> findAll();

    Account getAccountByClientId(Integer clientId);

    Account getAccountByAccountId(Integer accountId);

    Account getAccountByDateOfCreate(Date dateOfCreate);

    Integer create(Account account);

    Integer updateAccountBalance(Account account);

    Integer delete(Integer accountId);

    Integer count();

}
