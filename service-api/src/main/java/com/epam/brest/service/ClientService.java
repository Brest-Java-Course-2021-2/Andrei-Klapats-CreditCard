package com.epam.brest.service;

import com.epam.brest.model.Client;

import java.util.List;

public interface ClientService {

    /**
     * Find all clients.
     *
     * @return clients list.
     */
    List<Client> findAll();

    /**
     * Find client by Id.
     *
     * @param clientId client Id.
     * @return client
     */
    Client getClientById(Integer clientId);

    /**
     * Persist new client.
     *
     * @param client Client.
     * @return persisted client id.
     */
    Integer create(Client client);

    /**
     * Update client.
     *
     * @param client Client.
     * @return number of updated records in the database.
     */
    Integer update(Client client);

    /**
     * Delete client.
     *
     * @param clientId client id.
     * @return number of updated records in the database.
     */
    Integer delete(Integer clientId);

    /**
     * Count clients.
     *
     * @return quantity of the clients.
     */
    Integer count();
}
