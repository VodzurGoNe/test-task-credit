package test_solution.service;


import org.springframework.data.domain.Page;
import test_solution.entity.Bank;

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
