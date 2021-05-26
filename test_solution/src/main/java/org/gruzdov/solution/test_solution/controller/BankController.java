package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.Bank;
import org.gruzdov.solution.test_solution.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
public class BankController {
    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/bankList")
    public String viewHomePage(Model model) {
        return findPaginated(1, "title", "asc", model);
    }

    @GetMapping("/showNewBankForm")
    public String showNewBankForm(Model model) {

        Bank bank = new Bank();
        model.addAttribute("bank", bank);

        return "new_bank";
    }

    @PostMapping("/saveBank")
    public String saveBank(@ModelAttribute("bank") Bank bank) {
        bankService.saveBank(bank);
        return "redirect:/bankList";
    }

    @GetMapping("/showBank/{id}")
    public String showBank(@PathVariable ( value = "id") UUID id, Model model) {

        Bank bank = bankService.getBank(id);

        model.addAttribute("bank", bank);
        return "show_bank";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") UUID id, Model model) {

        Bank bank = bankService.getBank(id);

        model.addAttribute("bank", bank);
        return "update_bank";
    }

    @GetMapping("/deleteBank/{id}")
    public String deleteBank(@PathVariable (value = "id") UUID id) {

        this.bankService.deleteBank(id);
        return "redirect:/bankList";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Bank> page = bankService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Bank> listBanks = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listBanks", listBanks);

        return "index";
    }
}