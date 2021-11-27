package com.epam.brest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService{

    private final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

   private final ClientDAO clientDAO;

    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public Client getClientById(Integer clientId) {
        logger.debug("Get department by id = {}", clientId);
        return this.clientDAO.getClientById(clientId);
    }

    @Override
    @Transactional
    public Integer create(Client client) {
        return null;
    }

    @Override
    public Integer update(Client client) {
        return null;
    }

    @Override
    public Integer delete(Integer clientId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer count() {
        return null;
    }
}
