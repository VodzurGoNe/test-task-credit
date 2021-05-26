package org.gruzdov.solution.test_solution.service;


import org.gruzdov.solution.test_solution.entity.Bank;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface BankService {
    List<Bank> getAllBanks();

    void saveBank(Bank bank);

    Bank getBank(UUID id);

    void deleteBank(UUID id);

    Bank findByTitle(String title);

    Page<Bank> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
