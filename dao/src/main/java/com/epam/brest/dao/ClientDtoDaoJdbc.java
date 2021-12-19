package com.epam.brest.dao;

import com.epam.brest.model.dto.ClientDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Client DTO DAO implementation.
 */
@Component
public class ClientDtoDaoJdbc implements ClientDtoDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${findAllWithAvgSumOfBalanceSql}")
    private String findAllWithAvgSumOfBalanceSql;

    public ClientDtoDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<ClientDto> findAllWithAvgBalance() {

        List<ClientDto> clients = namedParameterJdbcTemplate.query(findAllWithAvgSumOfBalanceSql,
                BeanPropertyRowMapper.newInstance(ClientDto.class));
        return clients;
    }

}
