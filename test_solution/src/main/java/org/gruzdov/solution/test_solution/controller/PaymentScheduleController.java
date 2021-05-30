package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
import org.gruzdov.solution.test_solution.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@Controller
@RequestMapping("/payment_schedules")
public class PaymentScheduleController {
    private final PaymentScheduleService paymentScheduleService;

    @Autowired
    public PaymentScheduleController(PaymentScheduleService paymentScheduleService) {
        this.paymentScheduleService = paymentScheduleService;
    }

    @GetMapping("/payment_schedules_list/{creditOfferId}")
    public String viewHomePage(@PathVariable("creditOfferId") UUID creditOfferId
            , Model model) {

        model.addAttribute("listPaymentSchedules"
                , paymentScheduleService.findByCreditOfferId(creditOfferId));
        return "/payment_schedules/index";
    }

/*
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

 */

    @PostMapping("/save_payment_schedule")
    public String savePaymentSchedule(@ModelAttribute("paymentSchedule") @Valid PaymentSchedule paymentSchedule
            , BindingResult bindingResult) {

            if (bindingResult.hasErrors())
                return "/payment_schedules/update_payment_schedule";

        String creditOfferId = paymentSchedule.getCreditOffer().getId().toString();
        paymentScheduleService.savePaymentSchedule(paymentSchedule);
        return "redirect:/payment_schedules/payment_schedules_list/" + creditOfferId;
    }


    @GetMapping("/show_form_for_update/{paymentScheduleId}")
    public String showFormForUpdate(@PathVariable("paymentScheduleId") UUID paymentScheduleId
            , Model model) {

        PaymentSchedule paymentSchedule = paymentScheduleService.getPaymentSchedule(paymentScheduleId);

        model.addAttribute("paymentSchedule", paymentSchedule);
        return "payment_schedules/update_payment_schedule";
    }

    @GetMapping("/delete_payment_schedule/{paymentScheduleId}")
    public String deletePaymentSchedule(@PathVariable("paymentScheduleId") UUID paymentScheduleId) {

        String creditOfferId = paymentScheduleService.getPaymentSchedule(paymentScheduleId)
                .getCreditOffer().getId().toString();

        paymentScheduleService.deletePaymentSchedule(paymentScheduleId);
        return "redirect:/payment_schedules/payment_schedules_list/" + creditOfferId;
    }

}