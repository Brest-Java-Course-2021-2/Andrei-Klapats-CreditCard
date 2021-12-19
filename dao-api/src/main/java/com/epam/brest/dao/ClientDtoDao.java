package com.epam.brest.dao;

import com.epam.brest.model.dto.ClientDto;
import java.util.List;

/**
 * ClientDto DAO Interface.
 */
public interface ClientDtoDao {

    /**
     * Get all client with average accounts' balance
     *
     * @return clients list.
     */
    List<ClientDto> findAllWithAvgBalance();

}
