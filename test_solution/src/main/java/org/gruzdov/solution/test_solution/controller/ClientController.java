package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.Client;
import org.gruzdov.solution.test_solution.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<Client> showAllClients() {
        List<Client> allClients = clientService.getAllClients();

        return allClients;
    }

    @GetMapping("/clients/{id}")
    public Client getClient(@PathVariable UUID id) {
        Client client = clientService.getClient(id);

        //if (client == null)
            //throw new NoSuchEmployeeException("There is no employee with ID = " +
                    //id + " int Database");

        return client;
    }

    @PostMapping("/clients")
    public Client addNewClient(@RequestBody Client client) {
        clientService.saveClient(client);

        return client;
    }

    @PutMapping("/clients")
    public Client updateClient(@RequestBody Client client) {
        clientService.saveClient(client);

        return client;
    }

    @DeleteMapping("/clients/{id}")
    public String deleteClient(@PathVariable UUID id) {
        Client client = clientService.getClient(id);
       /* if (client == null)
            throw new NoSuchEmployeeException("There is no employee with ID = " +
                    id + " in Database");

        */

        clientService.deleteClient(id);

        return "Employee with ID = " + id + " was deleted";
    }

    @GetMapping("/clients/name/{fio}")
    public List<Client> showAllClientByName(@PathVariable String fio) {
        List<Client> clients = clientService.findAllByFio(fio);

        return clients;
    }

}
