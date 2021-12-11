package com.epam.brest;

import com.epam.brest.dto.ClientDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
public class ClientDtoServiceImplTest {
    @Autowired
    ClientDtoService clientDtoService;

    @Test
    public void findAllWithAvgSBalance() {
//        List<ClientDto> clients = clientDtoService.findAllWithAvgSBalance();
//        assertNotNull(clients);
//        assertTrue(clients.size() > 0);
//        assertTrue(clients.get(0).getAvgBalance().intValue() > 0);
    }
}
