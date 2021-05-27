package org.gruzdov.solution.test_solution.controller;

import java.util.List;
import java.util.UUID;

import org.gruzdov.solution.test_solution.entity.Client;
import org.gruzdov.solution.test_solution.service.BankService;
import org.gruzdov.solution.test_solution.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final BankService bankService;

    @Autowired
    public ClientController(ClientService clientService
            , BankService bankService) {

        this.clientService = clientService;
        this.bankService = bankService;
    }

    @GetMapping("/clientsList")
    public String viewHomePage(Model model) {
        return findPaginated(1, "fio", "asc", model);
    }

    @GetMapping("/showNewClientForm/{bankId}")
    public String showNewClientForm(Model model
            , @PathVariable("bankId") UUID bankId) {

        Client client = new Client();
        client.setBank(bankService.getBank(bankId));
        model.addAttribute("client", client);

        return "clients/new_client";
    }

    @PostMapping("/saveClient")
    public String saveClient(@ModelAttribute("client") Client client) {

        System.out.println(client.getBank());
        clientService.saveClient(client);
        return "redirect:/clients/clientsList";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") UUID id, Model model) {

        Client client = clientService.getClient(id);

        model.addAttribute("client", client);
        return "clients/update_client";
    }

    @GetMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable (value = "id") UUID id) {

        this.clientService.deleteClient(id);
        return "redirect:/clients/clientsList";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Client> page = clientService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Client> listClients = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listClients", listClients);

        return "/clients/index";
    }
}