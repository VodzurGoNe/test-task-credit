package test_solution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import test_solution.entity.Credit;
import test_solution.service.BankService;
import test_solution.service.CreditService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/credits")
public class CreditController {
    private final BankService bankService;
    private final CreditService creditService;

    @Autowired
    public CreditController(BankService bankService
            , CreditService creditService) {

        this.bankService = bankService;
        this.creditService = creditService;
    }

    @GetMapping("/credits_list/{bankId}")
    public String viewHomePage(@PathVariable("bankId") UUID bankId
            , Model model) {
            //, @RequestParam(defaultValue = "0")) {
        return findPaginated(bankId,1, "title", "asc", model);
//        model.addAttribute("listCredits", creditService.findByBankId(bankId));
//        return "/credits/index";
    }

    @GetMapping("/show_new_credit_form/{bankId}")
    public String showNewCreditForm(Model model
            , @PathVariable("bankId") UUID bankId) {

        Credit credit = new Credit();
        credit.setBank(bankService.getBank(bankId));
        model.addAttribute("credit", credit);

        return "credits/new_credit";
    }

    @PostMapping("/save_credit")
    public String saveCredit(@ModelAttribute("credit") @Valid Credit credit
            , BindingResult bindingResult) {

            if (bindingResult.hasErrors()) {
                return credit.getId() == null ? "/credits/new_credit"
                        : "/credits/update_credit";
            }

        String bankId = credit.getBank().getId().toString();
        creditService.saveCredit(credit);
        return "redirect:/credits/credits_list/" + bankId;
    }


    @GetMapping("/show_form_for_update/{creditId}")
    public String showFormForUpdate(@PathVariable("creditId") UUID creditId
            , Model model) {

        model.addAttribute("credit", creditService.getCredit(creditId));
        return "credits/update_credit";
    }

    @GetMapping("/delete_credit/{creditId}")
    public String deleteCredit(@PathVariable("creditId") UUID creditId) {

        String bankId = creditService.getCredit(creditId)
                .getBank().getId().toString();
        creditService.deleteCredit(creditId);
        return "redirect:/credits/credits_list/" + bankId;
    }

    @GetMapping("/credits_list/{bankId}/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "bankId") UUID bankId,
                                @PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Credit> page = creditService.findPaginated(bankId, pageNo, pageSize, sortField, sortDir);
        List<Credit> listCredits = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCredits", listCredits);
        return "credits/index";
    }
}