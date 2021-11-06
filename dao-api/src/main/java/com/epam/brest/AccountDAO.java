package com.epam.brest;

import com.epam.brest.com.epam.brest.Account;

import java.util.List;

public interface AccountDAO {
    List<Account> findAll();
    Integer create(Account creditAccount);
    Integer update(Account creditAccount);
    Integer delete (Account account);
}
