package com.epam.brest.dao;


import com.epam.brest.Client;
import com.epam.brest.ClientDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ClientDAOJDBCImpl implements ClientDAO {
    private final Logger LOGGER = LogManager.getLogger(ClientDAOJDBCImpl.class);


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${SQL_CLIENT_COUNT}")
    public String sqlClientCount;

    @Value("${SQL_ALL_CLIENTS}")
    private String sqlGetAllClients;

    @Value("${SQL_CLIENT_BY_ID}")
    private String sqlGetClientById;

    @Value("${SQL_CHECK_UNIQUE_CLIENT_PASSPORT}")
    private String sqlCheckUniqueClientPassport;

    @Value("${SQL_CREATE_CLIENT}")
    private String sqlCreateClient;

    @Value("${SQL_UPDATE_CLIENT_PASSPORT}")
    private String sqlUpdateClientPassport;

    @Value("${SQL_DELETE_CLIENT_BY_ID}")
    private String sqlDeleteClientById;


    public ClientDAOJDBCImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Client> findAll() {
        LOGGER.debug("Start: findAll()");
        return namedParameterJdbcTemplate.query(sqlGetAllClients, new ClientRowMapper());
    }

    @Override
    public Client getClientById(Integer clientId) {
        LOGGER.debug("Get client by id = {}", clientId);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("client_Id", clientId);
        return namedParameterJdbcTemplate.queryForObject(sqlGetClientById, sqlParameterSource, new ClientRowMapper());
    }

    @Override
    public Integer create(Client client) {
        LOGGER.debug("Create client: {}", client);


        if (!isClientUnique(client.getPassportNumber())) {
            LOGGER.warn("Client with the same passport number {} already exists.", client.getPassportNumber());
            throw new IllegalArgumentException("Client with the same passport already exists in DB.");
        }

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("lastname", client.getLastName())
                        .addValue("firstname", client.getFirstName())
                        .addValue("passport", client.getPassportNumber());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlCreateClient, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    private boolean isClientUnique(String passport) {
        LOGGER.debug("Check ClientPassport: {} on unique", passport);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("passport", passport);
        return namedParameterJdbcTemplate.queryForObject(sqlCheckUniqueClientPassport, sqlParameterSource, Integer.class) == 0;
    }

    @Override
    public Integer update(Client client) {

        LOGGER.debug("Update client passport: {}", client);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("passport", client.getPassportNumber())
                        .addValue("client_Id", client.getClientId());
        return namedParameterJdbcTemplate.update(sqlUpdateClientPassport, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer clientId) {
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("client_Id", clientId);
        return namedParameterJdbcTemplate.update(sqlDeleteClientById, sqlParameterSource);
    }

    @Override
    public Integer count() {

        LOGGER.debug("count()");
        return namedParameterJdbcTemplate
                .queryForObject(sqlClientCount, new MapSqlParameterSource(), Integer.class);
    }


    private static class ClientRowMapper implements RowMapper<Client> {

        @Override
        public Client mapRow(ResultSet resultSet, int i) throws SQLException {
            Client client = new Client();
            client.setClientId(resultSet.getInt("client_id"));
            client.setFirstName(resultSet.getString("firstname"));
            client.setLastName(resultSet.getString("lastname"));
            client.setPassportNumber(resultSet.getString("passport"));
            return client;
        }


    }


}
