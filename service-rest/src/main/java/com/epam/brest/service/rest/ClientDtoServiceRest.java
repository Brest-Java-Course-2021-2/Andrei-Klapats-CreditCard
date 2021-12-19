package com.epam.brest.service.rest;

import com.epam.brest.model.dto.ClientDto;
import com.epam.brest.service.ClientDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClientDtoServiceRest implements ClientDtoService {

    private final Logger logger = LogManager.getLogger(ClientDtoServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public ClientDtoServiceRest() {
        // empty default constructor
    }

    public ClientDtoServiceRest(String url, RestTemplate restTemplate) {
        this();
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ClientDto> findAllWithAvgBalance() {
        logger.debug("findAllWithAvgSalary()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<ClientDto>) responseEntity.getBody();
    }
}
