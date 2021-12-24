package com.epam.brest.service;



import com.epam.brest.model.Account;

import java.util.List;

public interface AccountService {
    /**
     * Find all accounts.
     *
     * @return accounts list.
     */
    List<Account> findAll();

    /**
     * Find account by Id.
     *
     * @param accountId account Id.
     * @return account
     */
    Account getAccountById(Integer accountId);

    /**
     * Find account by clientId.
     *
     * @param clientId client Id.
     * @return accounts list
     */
    Account getAccountByClientId(Integer clientId);

    /**
     * Persist new account.
     *
     * @param account Account.
     * @return persisted account id.
     */
    Integer create(Account account);

    /**
     * Update account.
     *
     * @param account Account.
     * @return number of updated records in the database.
     */
    Integer update(Account account);

    /**
     * Delete account.
     *
     * @param accountId account id.
     * @return number of updated records in the database.
     */
    Integer delete(Integer accountId);

    /**
     * Count accounts.
     *
     * @return quantity of the accounts.
     */
    Integer count();
}
