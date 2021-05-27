package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.entity.Credit;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CreditService {
    List<Credit> getAllCredits();

    void saveCredit(Credit credit);

    Credit getCredit(UUID id);

    void deleteCredit(UUID id);

    Credit findByTitle(String title);

    Page<Credit> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
