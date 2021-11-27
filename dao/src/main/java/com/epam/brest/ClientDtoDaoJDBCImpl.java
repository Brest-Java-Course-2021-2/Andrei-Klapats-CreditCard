package com.epam.brest;

import com.epam.brest.dto.ClientDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDtoDaoJDBCImpl implements ClientDtoDAO{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${findAllWithAvgSumOfBalanceSql}")
    private String findAllWithAvgSumOfBalanceSql;

    public ClientDtoDaoJDBCImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<ClientDto> findAllWithAvgBalance() {
        List<ClientDto> clients = namedParameterJdbcTemplate.query(findAllWithAvgSumOfBalanceSql,
                BeanPropertyRowMapper.newInstance(ClientDto.class));
        return clients;
    }
}
