package com.epam.brest;


import com.epam.brest.dto.ClientDto;

import java.util.List;

/**
 * ClientDto DAO Interface.
 */
public interface ClientDtoDAO {

        /**
         * Get all clients with avg balance by client.
         *
         * @return clients list.
         */
        List<ClientDto> findAllWithAvgBalance();
}
