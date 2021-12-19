package com.epam.brest.service.rest;

import com.epam.brest.model.Client;
import com.epam.brest.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ClientServiceRest implements ClientService {

    private final Logger logger = LogManager.getLogger(ClientDtoServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public ClientServiceRest() {
    }

    public ClientServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Client> findAll() {
        logger.debug("findAll()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Client>) responseEntity.getBody();
    }

    public Client getClientById(Integer clientId) {
        logger.debug("findById({})", clientId);
        ResponseEntity<Client> responseEntity =
                restTemplate.getForEntity(url + "/" + clientId, Client.class);
        return responseEntity.getBody();
    }

    @Override
    public Integer create(Client client) {
        logger.debug("create({})", client);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, client, Integer.class);
        return (Integer) responseEntity.getBody();
    }

    @Override
    public Integer update(Client client) {

        logger.debug("update({})", client);


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Client> entity = new HttpEntity<>(client, headers);
        ResponseEntity<Integer> result = restTemplate.exchange(url, HttpMethod.PUT, entity, Integer.class);
        return result.getBody();
    }

    @Override
    public Integer delete(Integer clientId) {
        logger.debug("delete({})", clientId);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Client> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> result =
                restTemplate.exchange(url + "/" + clientId, HttpMethod.DELETE, entity, Integer.class);
        return result.getBody();
    }

    @Override
    public Integer count() {
        logger.debug("count()");
        ResponseEntity<Integer> responseEntity = restTemplate.getForEntity(url + "/count", Integer.class);
        return responseEntity.getBody();
    }
}
