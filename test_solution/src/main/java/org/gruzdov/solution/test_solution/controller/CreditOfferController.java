package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.Client;
import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.gruzdov.solution.test_solution.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@Controller
@RequestMapping("/credit_offers")
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

    @GetMapping("/credit_offers_list/{clientId}")
    public String viewHomePage(@PathVariable("clientId") UUID clientId, Model model) {
        model.addAttribute("listCreditOffers", creditOfferService.findByClientId(clientId));
        return "/credit_offers/index";
    }

    @GetMapping("/show_new_credit_offer_form/{clientId}")
    public String showNewCreditOfferForm(@PathVariable("clientId") UUID clientId, Model model) {
        Client client = clientService.getClient(clientId);
        CreditOffer creditOffer = new CreditOffer();
        creditOffer.setClient(client);
        creditOffer.setBank(bankService.getBank(client.getBank().getId()));
        model.addAttribute("creditOffer", creditOffer);
        return "credit_offers/new_credit_offer";
    }

    @PostMapping("/save_credit_offer")
    public String saveCreditOffer(@ModelAttribute("creditOffer") @Valid CreditOffer creditOffer,
                                  BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return creditOffer.getId() == null
                        ? "/credit_offers/new_credit_offer"
                        : "/credit_offers/update_credit_offer";
            }
            calculationPaymentService.calculationPaymentSchedule(creditOffer);
        String clientId = creditOffer.getClient().getId().toString();
        return "redirect:/credit_offers/credit_offers_list/" + clientId;
    }

    @GetMapping("/show_form_for_update/{creditOfferId}")
    public String showFormForUpdate(@PathVariable("creditOfferId") UUID creditOfferId, Model model) {
        model.addAttribute("creditOffer", creditOfferService.getCreditOffer(creditOfferId));
        return "credit_offers/update_credit_offer";
    }

    @GetMapping("/delete_credit_offer/{creditOfferId}")
    public String deleteCreditOffer(@PathVariable("creditOfferId") UUID creditOfferId) {
        String clientId = creditOfferService.getCreditOffer(creditOfferId)
                .getClient().getId().toString();
        creditOfferService.deleteCreditOffer(creditOfferId);
        return "redirect:/credit_offers/credit_offers_list/" + clientId;
    }

}