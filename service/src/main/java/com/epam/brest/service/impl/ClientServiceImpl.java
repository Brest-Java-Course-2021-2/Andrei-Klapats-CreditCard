package com.epam.brest.service.impl;

import com.epam.brest.dao.ClientDao;
import com.epam.brest.model.Client;
import com.epam.brest.service.ClientService;
import com.epam.brest.service.exceptions.ClientNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

    private final ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        logger.trace("findAll()");
        return clientDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client getClientById(Integer clientId) {
        logger.debug("Get client by id = {}", clientId);
        try {
            return this.clientDao.getClientById(clientId);
        } catch (EmptyResultDataAccessException e) {
            throw new ClientNotFoundException(clientId);
        }
    }

    @Override
    @Transactional
    public Integer create(Client client) {
        logger.debug("create({})", client);
        return this.clientDao.create(client);
    }

    @Override
    @Transactional
    public Integer update(Client client) {
        logger.debug("update({})", client);
        return this.clientDao.update(client);
    }

    @Override
    @Transactional
    public Integer delete(Integer clientId) {
        logger.debug("delete client with id = {}", clientId);
        return this.clientDao.delete(clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer count() {
        logger.debug("count()");
        return this.clientDao.count();
    }
}
