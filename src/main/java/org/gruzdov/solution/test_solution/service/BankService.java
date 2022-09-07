package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.entity.Bank;

import java.util.List;
import java.util.UUID;

/**
 * @author Vladislav Gruzdov
 */
public interface BankService {

    void saveBank(Bank bank);

    Bank getBank(UUID id);

    void deleteBank(UUID id);

    List<Bank> getAllBanks();
}
