package org.gruzdov.solution.test_solution.service;


import org.gruzdov.solution.test_solution.dao.ClientRepository;
import org.gruzdov.solution.test_solution.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client getClient(UUID id) {
        Client client = null;
        Optional<Client> optional = clientRepository.findById(id);
        if (optional.isPresent())
            client = optional.get();

        return client;
    }

    @Override
    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> findAllByFio(String fio) {
        List<Client> clients = clientRepository.findAllByFio(fio);

        return clients;
    }

    @Override
    public Page<Client> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.clientRepository.findAll(pageable);
    }

}
