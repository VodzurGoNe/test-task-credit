package org.gruzdov.solution.test_solution.service;


import org.gruzdov.solution.test_solution.entity.Bank;

import java.util.List;
import java.util.UUID;

public interface BankService {
    List<Bank> getAllBanks();

    void saveBank(Bank bank);

    Bank getBank(UUID id);

    void deleteBank(UUID id);

    Bank findByTitle(String title);

}
