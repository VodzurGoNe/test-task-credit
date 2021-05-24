package org.gruzdov.solution.test_solution.service;


import org.gruzdov.solution.test_solution.entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    List<Client> getAllClients();

    void saveClient(Client client);

    Client getClient(UUID id);

    void deleteClient(UUID id);

    List<Client> findAllByFio(String fio);

}
