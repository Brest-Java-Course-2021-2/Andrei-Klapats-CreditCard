package com.epam.brest.rest.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Integer id) {
        super("Client not found for id: " + id);
    }
}
