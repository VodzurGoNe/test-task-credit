package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.gruzdov.solution.test_solution.service.BankService;
import org.gruzdov.solution.test_solution.service.ClientService;
import org.gruzdov.solution.test_solution.service.CreditOfferService;
import org.gruzdov.solution.test_solution.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/creditsOffers")
public class CreditOfferController {
    private final ClientService clientService;
    private final BankService bankService;
    private final CreditService creditService;
    private final CreditOfferService creditOfferService;

    @Autowired
    public CreditOfferController(ClientService clientService, BankService bankService
            , CreditService creditService, CreditOfferService creditOfferService) {
        this.clientService = clientService;
        this.bankService = bankService;
        this.creditService = creditService;
        this.creditOfferService = creditOfferService;
    }

    @GetMapping("/creditsOffersList")
    public String viewHomePage(Model model) {
        return findPaginated(1, "title", "asc", model);
    }

    @GetMapping("/showNewCreditOfferForm/{bankId}")
    public String showNewCreditOfferForm(Model model
            , @PathVariable("bankId") UUID bankId) {

        CreditOffer creditOffer = new CreditOffer();
        //credit.setBank(bankService.getBank(bankId));
        model.addAttribute("creditOffer", creditOffer);

        return "creditOffers/new_creditOffer";
    }

    @PostMapping("/saveCreditOffer")
    public String saveCreditOffer(@ModelAttribute("creditOffer") CreditOffer creditOffer) {
        creditOfferService.saveCreditOffer(creditOffer);
        return "redirect:/creditOffers/creditOffersList";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") UUID id, Model model) {

        CreditOffer creditOffer = creditOfferService.getCreditOffer(id);

        model.addAttribute("creditOffer", creditOffer);
        return "creditOffers/update_creditOffer";
    }

    @GetMapping("/deleteCreditOffer/{id}")
    public String deleteCreditOffer(@PathVariable (value = "id") UUID id) {

        this.creditOfferService.deleteCreditOffer(id);
        return "redirect:/creditOffers/creditOffersList";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<CreditOffer> page = creditOfferService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<CreditOffer> listCreditOffers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCreditOffers", listCreditOffers);

        return "/creditOffers/index";
    }
}