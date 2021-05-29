package org.gruzdov.solution.test_solution.controller;

import org.gruzdov.solution.test_solution.entity.Bank;
import org.gruzdov.solution.test_solution.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class BankController {
    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/bank_list")
    public String viewHomePage(Model model) {

        model.addAttribute("listBanks", bankService.getAllBanks());

        return "index";
    }

    @GetMapping("/show_new_bank_form")
    public String showNewBankForm(Model model) {

        model.addAttribute("bank", new Bank());
        return "new_bank";
    }

    @PostMapping("/save_bank")
    public String saveBank(@ModelAttribute("bank") @Valid Bank bank
            , BindingResult bindingResult) {

            if (bindingResult.hasErrors())
                return "new_bank";

        bankService.saveBank(bank);
        return "redirect:/bank_list";
    }

    @GetMapping("/show_bank/{bankId}")
    public String showBank(@PathVariable("bankId") UUID bankId
            , Model model) {

        model.addAttribute("bank", bankService.getBank(bankId));
        return "show_bank";
    }

    @GetMapping("/show_form_for_update/{bankId}")
    public String showFormForUpdate(@PathVariable("bankId") UUID bankId
            , Model model) {

        model.addAttribute("bank", bankService.getBank(bankId));
        return "update_bank";
    }

    @GetMapping("/delete_bank/{bankId}")
    public String deleteBank(@PathVariable("bankId") UUID bankId) {

        bankService.deleteBank(bankId);
        return "redirect:/bank_list";
    }
/*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

 */

}