package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.Client;
import org.gruzdov.solution.test_solution.service.BankService;
import org.gruzdov.solution.test_solution.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


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
        return findPaginated(bankId,1, "fio", "asc", model);
//        model.addAttribute("listClients", clientService.findByBankId(bankId));
//        return "/clients/index";
    }

    @GetMapping("/show_new_client_form/{bankId}")
    public String showNewClientForm(@PathVariable("bankId") UUID bankId, Model model) {
        Client client = new Client();
        client.setBank(bankService.getBank(bankId));
        model.addAttribute("client", client);
        return "clients/new_client";
    }

    @PostMapping("/save_client/")
    public String saveClient(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return client.getId() == null ? "/clients/new_client" : "/clients/update_client";
            }
        String bankId = client.getBank().getId().toString();
        clientService.saveClient(client);
        return "redirect:/clients/clients_list/" + bankId;
    }

    @GetMapping("/show_form_for_update/{clientId}")
    public String showFormForUpdate(@PathVariable("clientId") UUID clientId, Model model) {
        model.addAttribute("client", clientService.getClient(clientId));
        return "clients/update_client";
    }

    @GetMapping("/delete_client/{clientId}")
    public String deleteClient(@PathVariable("clientId") UUID clientId) {
        String bankId = clientService.getClient(clientId)
                .getBank().getId().toString();
        clientService.deleteClient(clientId);
        return "redirect:/clients/clients_list/" + bankId;
    }

    @GetMapping("/clients_list/{bankId}/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "bankId") UUID bankId,
                                @PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Client> page = clientService.findPaginated(bankId, pageNo, pageSize, sortField, sortDir);
        List<Client> listClients = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listClients", listClients);
        return "clients/index";
    }

}