package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
import org.gruzdov.solution.test_solution.service.PaymentScheduleService;
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
@RequestMapping("/payment_schedules")
public class PaymentScheduleController {

    private final PaymentScheduleService paymentScheduleService;

    @Autowired
    public PaymentScheduleController(PaymentScheduleService paymentScheduleService) {
        this.paymentScheduleService = paymentScheduleService;
    }

    @GetMapping("/payment_schedules_list/{creditOfferId}")
    public String viewHomePage(@PathVariable("creditOfferId") UUID creditOfferId, Model model) {
        model.addAttribute("listPaymentSchedules", paymentScheduleService.findByCreditOfferId(creditOfferId));
        return "/payment_schedules/index";
    }

    @PostMapping("/save_payment_schedule")
    public String savePaymentSchedule(@ModelAttribute("paymentSchedule") @Valid PaymentSchedule paymentSchedule,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/payment_schedules/update_payment_schedule";
        }

        UUID creditOfferId = paymentSchedule.getCreditOffer().getId();
        paymentScheduleService.savePaymentSchedule(paymentSchedule);
        return String.format("redirect:/payment_schedules/payment_schedules_list/%s", creditOfferId);
    }

    @GetMapping("/show_form_for_update/{paymentScheduleId}")
    public String showFormForUpdate(@PathVariable("paymentScheduleId") UUID paymentScheduleId, Model model) {
        model.addAttribute("paymentSchedule", paymentScheduleService.getPaymentSchedule(paymentScheduleId));
        return "payment_schedules/update_payment_schedule";
    }

    @GetMapping("/delete_payment_schedule/{paymentScheduleId}")
    public String deletePaymentSchedule(@PathVariable("paymentScheduleId") UUID paymentScheduleId) {
        UUID creditOfferId = paymentScheduleService.getPaymentSchedule(paymentScheduleId).getCreditOffer().getId();
        paymentScheduleService.deletePaymentSchedule(paymentScheduleId);
        return String.format("redirect:/payment_schedules/payment_schedules_list/%s", creditOfferId);
    }
}