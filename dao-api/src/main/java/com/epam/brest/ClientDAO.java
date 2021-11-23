package com.epam.brest;

import java.util.List;

public interface ClientDAO {
    List<Client> findAll();
    Client getClientById(Integer clientId);
    Integer create(Client client);
    Integer update(Client client);
    Integer delete (Integer clientId);
    Integer count();

}
