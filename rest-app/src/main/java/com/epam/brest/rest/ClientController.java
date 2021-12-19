package com.epam.brest.rest;

import com.epam.brest.dao.ClientDaoJDBCImpl;
import com.epam.brest.model.Client;
import com.epam.brest.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@CrossOrigin
public class ClientController {

    private static final Logger logger = LogManager.getLogger(ClientDaoJDBCImpl.class);

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/clients")
    public final Collection<Client> clients() {

        logger.debug("clients()");
        return clientService.findAll();
    }

    @GetMapping(value = "/clients/{id}")
    public final Client getClientById(@PathVariable Integer id) {

        logger.debug("getClientById({})", id);
        Client client = clientService.getClientById(id);
        return client;
    }

    @PostMapping(path = "/clients", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createClient(@RequestBody Client client) {

        logger.debug("createClient({})", client);
        Integer id = clientService.create(client);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/clients", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> updateClient(@RequestBody Client client) {

        logger.debug("updateClient({})", client);
        int result = clientService.update(client);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/clients/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteClient(@PathVariable Integer id) {

        logger.debug("deleteClient({})", id);
        int result = clientService.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
