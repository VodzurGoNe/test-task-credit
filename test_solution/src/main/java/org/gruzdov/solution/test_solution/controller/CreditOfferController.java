package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.Client;
import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.gruzdov.solution.test_solution.service.*;
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
    private final CreditOfferService creditOfferService;
    private final CalculationPaymentService calculationPaymentService;

    @Autowired
    public CreditOfferController(ClientService clientService
            , BankService bankService
            , CreditOfferService creditOfferService
            , CalculationPaymentService calculationPaymentService) {

        this.clientService = clientService;
        this.bankService = bankService;
        this.creditOfferService = creditOfferService;
        this.calculationPaymentService = calculationPaymentService;
    }

    @GetMapping("/creditOffersList")
    public String viewHomePage(Model model) {
        return findPaginated(1, "named", "asc", model);
    }

    @GetMapping("/showNewCreditOfferForm/{clientId}")
    public String showNewCreditOfferForm(Model model
            , @PathVariable("clientId") UUID clientId) {

        Client client = clientService.getClient(clientId);

        CreditOffer creditOffer = new CreditOffer();
        creditOffer.setClient(client);
        creditOffer.setBank(bankService.getBank(client.getBank().getId()));
        model.addAttribute("creditOffer", creditOffer);

        return "credit_offers/new_credit_offer";
    }

    @PostMapping("/saveCreditOffer")
    public String saveCreditOffer(@ModelAttribute("creditOffer") CreditOffer creditOffer) {

        //creditOfferService.saveCreditOffer(creditOffer);
        //if (creditOffer.getPercentSum().intValue() == 0)
            calculationPaymentService.calculationPaymentSchedule(creditOffer);
        //else creditOfferService.saveCreditOffer(creditOffer);
        return "redirect:/creditsOffers/creditOffersList";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") UUID id, Model model) {

        CreditOffer creditOffer = creditOfferService.getCreditOffer(id);

        model.addAttribute("creditOffer", creditOffer);
        return "credit_offers/update_credit_offer";
    }

    @GetMapping("/deleteCreditOffer/{id}")
    public String deleteCreditOffer(@PathVariable (value = "id") UUID id) {

        this.creditOfferService.deleteCreditOffer(id);
        return "redirect:/creditsOffers/creditOffersList";
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

        return "/credit_offers/index";
    }
}