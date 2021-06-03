package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.dao.ClientRepository;
import org.gruzdov.solution.test_solution.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Nullable
    @Override
    public Client getClient(UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> findByBankId(UUID bankId) {
        return clientRepository.findByBankId(bankId);
    }
}
