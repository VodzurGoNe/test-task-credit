package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.Bank;
import org.gruzdov.solution.test_solution.entity.Client;
import org.gruzdov.solution.test_solution.global.factory.SimpleFactory;
import org.gruzdov.solution.test_solution.service.BankService;
import org.gruzdov.solution.test_solution.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author Vladislav Gruzdov
 */
@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final BankService bankService;

    @Autowired
    public ClientController(ClientService clientService, BankService bankService) {
        this.clientService = clientService;
        this.bankService = bankService;
    }

    @GetMapping("/clients_list/{bankId}")
    public String viewHomePage(@PathVariable("bankId") UUID bankId, Model model) {
        model.addAttribute("listClients", clientService.findByBankId(bankId));
        return "/clients/index";
    }

    @GetMapping("/show_new_client_form/{bankId}")
    public String showNewClientForm(@PathVariable("bankId") UUID bankId, Model model) {
        Client client = SimpleFactory.create(Client.class);
        Bank bank = bankService.getBank(bankId);
        client.setBank(bank);

        model.addAttribute("client", client);
        return "clients/new_client";
    }

    @PostMapping("/save_client/")
    public String saveClient(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return client.getId() == null ? "/clients/new_client" : "/clients/update_client";
        }

        clientService.saveClient(client);
        return String.format("redirect:/clients/clients_list/%s", client.getBank().getId());
    }

    @GetMapping("/show_form_for_update/{clientId}")
    public String showFormForUpdate(@PathVariable("clientId") UUID clientId, Model model) {
        model.addAttribute("client", clientService.getClient(clientId));
        return "clients/update_client";
    }

    @GetMapping("/delete_client/{clientId}")
    public String deleteClient(@PathVariable("clientId") UUID clientId) {
        UUID bankId = clientService.getClient(clientId).getBank().getId();
        clientService.deleteClient(clientId);
        return String.format("redirect:/clients/clients_list/%s", bankId);
    }
}