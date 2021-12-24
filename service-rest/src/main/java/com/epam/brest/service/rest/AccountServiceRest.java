package com.epam.brest.service.rest;

import com.epam.brest.model.Account;
import com.epam.brest.model.Client;
import com.epam.brest.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;



@Service
public class AccountServiceRest implements AccountService {


    private final Logger logger = LogManager.getLogger(AccountServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public AccountServiceRest() {
    }

    public AccountServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Account> findAll() {
        logger.debug("findAll()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Account>) responseEntity.getBody();
    }

    @Override
    public Account getAccountById(Integer accountId) {
        logger.debug("findById({})", accountId);
        ResponseEntity<Account> responseEntity =
                restTemplate.getForEntity(url + "/" + accountId, Account.class);
        return responseEntity.getBody();
    }

    @Override
    public Account getAccountByClientId(Integer clientId) {
        logger.debug("findByClientId({})", clientId);
        ResponseEntity<Account> responseEntity =
                restTemplate.getForEntity(url + "/" + clientId, Account.class);
        return responseEntity.getBody();
    }

    @Override
    public Integer create(Account account) {
        logger.debug("create({})", account);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, account, Integer.class);
        return (Integer) responseEntity.getBody();
    }

    @Override
    public Integer update(Account account) {
        logger.debug("update({})", account);


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Account> entity = new HttpEntity<>(account, headers);
        ResponseEntity<Integer> result = restTemplate.exchange(url, HttpMethod.PUT, entity, Integer.class);
        return result.getBody();
    }

    @Override
    public Integer delete(Integer accountId) {
        logger.debug("delete({})", accountId);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Account> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> result =
                restTemplate.exchange(url + "/" + accountId, HttpMethod.DELETE, entity, Integer.class);
        return result.getBody();
    }

    @Override
    public Integer count() {
        logger.debug("count()");
        ResponseEntity<Integer> responseEntity = restTemplate.getForEntity(url + "/count", Integer.class);
        return responseEntity.getBody();
    }
}
