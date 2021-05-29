package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.Client;
import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.gruzdov.solution.test_solution.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
@RequestMapping("/payment_schedules")
public class PaymentScheduleController {
    private final ClientService clientService;
    private final CreditOfferService creditOfferService;
    private final CalculationPaymentService calculationPaymentService;
    private final PaymentScheduleService paymentScheduleService;

    @Autowired
    public PaymentScheduleController(ClientService clientService
            , CreditOfferService creditOfferService
            , CalculationPaymentService calculationPaymentService
            , PaymentScheduleService paymentScheduleService) {

        this.clientService = clientService;
        this.creditOfferService = creditOfferService;
        this.calculationPaymentService = calculationPaymentService;
        this.paymentScheduleService = paymentScheduleService;
    }

    @GetMapping("/payment_schedules_list/{creditOfferId}")
    public String viewHomePage(@PathVariable("creditOfferId") UUID creditOfferId
            , Model model) {

        model.addAttribute("listPaymentSchedules"
                , paymentScheduleService.findByCreditOfferId(creditOfferId));
        return "/payment_schedules/index";
    }



    @GetMapping("/show_new_payment_schedule_form/{clientId}")
    public String showNewPaymentScheduleForm(@PathVariable("clientId") UUID clientId
            , Model model) {

        Client client = clientService.getClient(clientId);

       // PaymentSchedule paySchedule = new PaymentSchedule();
//        CreditOffer creditOffer = new CreditOffer();
//        creditOffer.setClient(client);
//        creditOffer.setBank(bankService.getBank(client.getBank().getId()));
//        model.addAttribute("creditOffer", creditOffer);

        return "credit_offers/new_credit_offer";
    }

    @PostMapping("/save_payment_schedule")
    public String savePaymentSchedule(@ModelAttribute("creditOffer") CreditOffer creditOffer) {

        creditOfferService.saveCreditOffer(creditOffer);
        calculationPaymentService.calculationPaymentSchedule(creditOffer);

        return "redirect:/payment_schedules/payment_schedules_list";
    }


    @GetMapping("/show_form_for_update/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") UUID id, Model model) {

        CreditOffer creditOffer = creditOfferService.getCreditOffer(id);

        model.addAttribute("creditOffer", creditOffer);
        return "payment_schedules/update_payment_schedule";
    }

    @GetMapping("/delete_payment_schedule/{id}")
    public String deletePaymentSchedule(@PathVariable (value = "id") UUID id) {

        this.creditOfferService.deleteCreditOffer(id);
        return "redirect:/payment_schedules/payment_schedules_list";
    }

}