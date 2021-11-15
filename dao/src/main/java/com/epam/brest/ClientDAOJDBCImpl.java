package com.epam.brest;

import com.epam.brest.com.epam.brest.Account;
import com.epam.brest.com.epam.brest.Client;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientDAOJDBCImpl implements ClientDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String SQL_ALL_CLIENTS="select c.firstname c.client_id  from client c order by c.firstname";

    public ClientDAOJDBCImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Client> findAll() {
    return namedParameterJdbcTemplate.query(SQL_ALL_CLIENTS,new ClientRowMapper());

    }

    @Override
    public Integer create(Client client) {
        return null;
    }

    @Override
    public Integer update(Client client) {
        return null;
    }

    @Override
    public Integer delete(Client client) {
        return null;
    }


    private class ClientRowMapper implements RowMapper<Client> {

        @Override
        public Client mapRow(ResultSet resultSet, int i) throws SQLException {
            Client client = new Client();
            client.setClientId(resultSet.getInt("client_id"));
            client.setFirstName(resultSet.getString("firstname"));
            return client;
        }
    }
}
