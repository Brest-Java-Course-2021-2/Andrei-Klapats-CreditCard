package com.epam.brest.service.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;

public class ClientNotFoundException extends EmptyResultDataAccessException {
    public ClientNotFoundException(Integer id) {
        super("Client not found for id: " + id, 1);
    }
}
