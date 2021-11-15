package com.epam.brest;


import com.epam.brest.com.epam.brest.Client;

import java.util.List;

public interface ClientDAO {
    List<Client> findAll();
    Integer create(Client client);
    Integer update(Client client);
    Integer delete (Client client);
}
