package com.epam.brest.service;

import com.epam.brest.model.dto.ClientDto;

import java.util.List;

public interface ClientDtoService {

    /**
     * Get list of client Dto.
     *
     * @return list of client Dto.
     */
    List<ClientDto> findAllWithAvgBalance();
}
