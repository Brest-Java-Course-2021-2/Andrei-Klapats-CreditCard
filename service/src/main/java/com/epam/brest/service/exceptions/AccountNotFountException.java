package com.epam.brest.service.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;

public class AccountNotFountException extends EmptyResultDataAccessException {
    public AccountNotFountException(Integer id) {
        super("Account not found for id: " + id, 1);
    }
}
