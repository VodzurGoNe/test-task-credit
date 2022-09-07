package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.Bank;
import org.gruzdov.solution.test_solution.entity.Client;
import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.gruzdov.solution.test_solution.service.BankService;
import org.gruzdov.solution.test_solution.service.CalculationPaymentService;
import org.gruzdov.solution.test_solution.service.ClientService;
import org.gruzdov.solution.test_solution.service.CreditOfferService;
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
@RequestMapping("/credit_offers")
public class CreditOfferController {

    private final ClientService clientService;
    private final BankService bankService;
    private final CreditOfferService creditOfferService;
    private final CalculationPaymentService calculationPaymentService;

    @Autowired
    public CreditOfferController(ClientService clientService, BankService bankService,
                                 CreditOfferService creditOfferService,
                                 CalculationPaymentService calculationPaymentService) {
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
        Bank bank = bankService.getBank(client.getBank().getId());
        CreditOffer creditOffer = CreditOffer.builder()
                .client(client)
                .bank(bank)
                .build();

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

        calculationPaymentService.collectDataAboutCreditOffer(creditOffer);
        UUID clientId = creditOffer.getClient().getId();
        return String.format("redirect:/credit_offers/credit_offers_list/%s", clientId);
    }

    @GetMapping("/show_form_for_update/{creditOfferId}")
    public String showFormForUpdate(@PathVariable("creditOfferId") UUID creditOfferId, Model model) {
        model.addAttribute("creditOffer", creditOfferService.getCreditOffer(creditOfferId));
        return "credit_offers/update_credit_offer";
    }

    @GetMapping("/delete_credit_offer/{creditOfferId}")
    public String deleteCreditOffer(@PathVariable("creditOfferId") UUID creditOfferId) {
        UUID clientId = creditOfferService.getCreditOffer(creditOfferId).getClient().getId();
        creditOfferService.deleteCreditOffer(creditOfferId);
        return String.format("redirect:/credit_offers/credit_offers_list/%s", clientId);
    }
}