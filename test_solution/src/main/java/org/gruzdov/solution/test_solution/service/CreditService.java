package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.entity.Credit;

import java.util.List;
import java.util.UUID;

public interface CreditService {
    void saveCredit(Credit credit);

    Credit getCredit(UUID id);

    void deleteCredit(UUID id);

    List<Credit> findByBankId(UUID bankId);
}
