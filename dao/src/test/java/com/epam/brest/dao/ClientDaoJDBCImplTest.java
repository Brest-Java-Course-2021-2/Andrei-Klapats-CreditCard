package com.epam.brest.dao;

import com.epam.brest.model.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class ClientDaoJDBCImplTest {

    @InjectMocks
    private ClientDaoJDBCImpl clientDAOJDBC;
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<RowMapper<Client>> captor;
    @AfterEach
    public void check(){
        Mockito.verifyNoMoreInteractions(namedParameterJdbcTemplate);
    }

    @Test
    public void findAll() {
//        String sql="select";
//        ReflectionTestUtils.setField(clientDAOJDBC,"sqlGetClientById",sql);

        Client client = new Client();
        List<Client> list = Collections.singletonList(client);
        Mockito.when(namedParameterJdbcTemplate.query(any(),
                        ArgumentMatchers.<RowMapper<Client>>any()))
                .thenReturn(list);

        List<Client> result = clientDAOJDBC.findAll();

//        Mockito.verify(namedParameterJdbcTemplate).query(eq(sql), captor.capture()) ;

//        RowMapper<Client> mapper = captor.getValue();
//        Assertions.assertNotNull(mapper);
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertSame(client, result.get(0));
    }


    @Test
    public void getClientById() {

        int i = 0;
        Client client = new Client();

        Mockito.when(namedParameterJdbcTemplate.queryForObject(
                any(),
                ArgumentMatchers.<SqlParameterSource>any(),
                ArgumentMatchers.<RowMapper<Client>>any())).thenReturn(client);
        Client result = clientDAOJDBC.getClientById(i);
        Assertions.assertNotNull(result);
        Assertions.assertSame(client, result);
    }

    @Test
    public Integer create(Client client) {
        return null;
    }


    @Test
    public Integer update(Client client) {
        return null;
    }

    @Test
    public Integer delete(Integer clientId) {
        return null;
    }


    @Test
    public Integer count() {
        return null;
    }
}
