package com.epam.brest;

import com.epam.brest.dto.ClientDto;

import java.util.List;

public interface ClientDtoService {

    /**
     * Get list of department Dto.
     *
     * @return list of department Dto.
     */
    List<ClientDto> findAllWithAvgSBalance();
}
