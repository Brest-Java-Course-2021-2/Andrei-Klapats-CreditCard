package com.epam.brest.service.impl;

import com.epam.brest.model.dto.ClientDto;
import com.epam.brest.service.ClientDtoService;
import com.epam.brest.service.config.ServiceTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Import({ServiceTestConfig.class})
@PropertySource({"classpath:dao.properties"})
@Transactional
class ClientDtoServiceImplIT {

    @Autowired
    ClientDtoService clientDtoService;

    @Test
    public void shouldFindAllWithAvgSalary() {
        List<ClientDto> clients = clientDtoService.findAllWithAvgBalance();
        assertNotNull(clients);
        assertTrue(clients.size() > 0);
        assertTrue(clients.get(0).getAvgBalance().intValue() > 0);
    }
}
