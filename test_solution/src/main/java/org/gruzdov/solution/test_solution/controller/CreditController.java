package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.Credit;
import org.gruzdov.solution.test_solution.service.BankService;
import org.gruzdov.solution.test_solution.service.ClientService;
import org.gruzdov.solution.test_solution.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/credits")
public class CreditController {
    private final ClientService clientService;
    private final BankService bankService;
    private final CreditService creditService;

    @Autowired
    public CreditController(ClientService clientService
            , BankService bankService
            , CreditService creditService) {

        this.clientService = clientService;
        this.bankService = bankService;
        this.creditService = creditService;
    }

    @GetMapping("/creditsList")
    public String viewHomePage(Model model) {
        return findPaginated(1, "title", "asc", model);
    }

    @GetMapping("/showNewCreditForm/{bankId}")
    public String showNewCreditForm(Model model
            , @PathVariable("bankId") UUID bankId) {

        Credit credit = new Credit();
        credit.setBank(bankService.getBank(bankId));
        model.addAttribute("credit", credit);

        return "credits/new_credit";
    }

    @PostMapping("/saveCredit")
    public String saveCredit(@ModelAttribute("credit") Credit credit) {

        System.out.println(credit.getBank());
        creditService.saveCredit(credit);
        return "redirect:/credits/creditsList";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") UUID id, Model model) {

        Credit credit = creditService.getCredit(id);

        model.addAttribute("credit", credit);
        return "credits/update_credit";
    }

    @GetMapping("/deleteCredit/{id}")
    public String deleteCredit(@PathVariable (value = "id") UUID id) {

        this.creditService.deleteCredit(id);
        return "redirect:/credits/creditsList";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Credit> page = creditService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Credit> listCredits = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCredits", listCredits);

        return "/credits/index";
    }
}