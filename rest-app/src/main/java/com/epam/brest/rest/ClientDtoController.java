package com.epam.brest.rest;

import com.epam.brest.dao.ClientDaoJDBCImpl;
import com.epam.brest.model.dto.ClientDto;
import com.epam.brest.service.ClientDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@CrossOrigin
public class ClientDtoController {

    private static final Logger logger = LogManager.getLogger(ClientDaoJDBCImpl.class);

    private final ClientDtoService clientDtoService;

    public ClientDtoController(ClientDtoService clientDtoService) {
        this.clientDtoService = clientDtoService;
    }

    /**
     * Get clients Dtos.
     *
     * @return Client Dtos collection.
     */
    @GetMapping(value = "/client-dtos")
    public final Collection<ClientDto> clientDtos() {

        logger.debug("clientDtos()");
        return clientDtoService.findAllWithAvgBalance();
    }
}
