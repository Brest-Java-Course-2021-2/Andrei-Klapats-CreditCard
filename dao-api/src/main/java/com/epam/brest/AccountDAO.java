package com.epam.brest;

import java.util.List;

public interface AccountDAO {

    List<Account> findAllAccountOfClient();
    Account getAccountById(Integer clientId);
    Integer create(Account account);
    Integer update(Account account);
    Integer delete (Integer accountId);
}
