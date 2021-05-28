package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.Client;
import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
import org.gruzdov.solution.test_solution.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/paymentSchedules")
public class PaymentScheduleController {
    private final ClientService clientService;
    private final BankService bankService;
    private final CreditService creditService;
    private final CreditOfferService creditOfferService;
    private final CalculationPaymentService calculationPaymentService;
    private final PaymentScheduleService paymentScheduleService;

    @Autowired
    public PaymentScheduleController(ClientService clientService
            , BankService bankService
            , CreditService creditService
            , CreditOfferService creditOfferService
            , CalculationPaymentService calculationPaymentService
            , PaymentScheduleService paymentScheduleService) {

        this.clientService = clientService;
        this.bankService = bankService;
        this.creditService = creditService;
        this.creditOfferService = creditOfferService;
        this.calculationPaymentService = calculationPaymentService;
        this.paymentScheduleService = paymentScheduleService;
    }

    @GetMapping("/paymentSchedulesList")
    public String viewHomePage(Model model) {
        return findPaginated(1, "paymentDate", "asc", model);
    }



    @GetMapping("/showNewPaymentScheduleForm/{clientId}")
    public String showNewPaymentScheduleForm(Model model
            , @PathVariable("clientId") UUID clientId) {

        Client client = clientService.getClient(clientId);

//        CreditOffer creditOffer = new CreditOffer();
//        creditOffer.setClient(client);
//        creditOffer.setBank(bankService.getBank(client.getBank().getId()));
//        model.addAttribute("creditOffer", creditOffer);

        return "credit_offers/new_credit_offer";
    }

    @PostMapping("/savePaymentSchedule")
    public String savePaymentSchedule(@ModelAttribute("creditOffer") CreditOffer creditOffer) {

        creditOfferService.saveCreditOffer(creditOffer);
        calculationPaymentService.calculationPaymentSchedule(creditOffer);

        return "redirect:/paymentSchedules/paymentSchedulesList";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") UUID id, Model model) {

        CreditOffer creditOffer = creditOfferService.getCreditOffer(id);

        model.addAttribute("creditOffer", creditOffer);
        return "payment_schedules/update_payment_schedule";
    }

    @GetMapping("/deletePaymentSchedule/{id}")
    public String deletePaymentSchedule(@PathVariable (value = "id") UUID id) {

        this.creditOfferService.deleteCreditOffer(id);
        return "redirect:/paymentSchedules/paymentSchedulesList";
    }


    @GetMapping("/paymentSchedules/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<PaymentSchedule> page = paymentScheduleService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<PaymentSchedule> listPaymentSchedules = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listPaymentSchedules", listPaymentSchedules);

        return "/payment_schedules/index";
    }

}