package com.epam.brest;

import com.epam.brest.dto.ClientDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class ClientDtoServiceImpl implements ClientDtoService{


    private final ClientDtoDAO clientDtoDAO;

    public ClientDtoServiceImpl(ClientDtoDAO clientDtoDAO) {
        this.clientDtoDAO = clientDtoDAO;
    }
    @Override
    public List<ClientDto> findAllWithAvgSBalance() {
        return clientDtoDAO.findAllWithAvgBalance();
    }
}
