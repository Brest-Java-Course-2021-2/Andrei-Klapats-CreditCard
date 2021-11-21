package com.epam.brest;

public interface ClientService {

    Client getClientById(Integer clientId);

    Integer create(Client client);

    Integer update(Client client);

    Integer delete(Client client);

    Integer count();


}
