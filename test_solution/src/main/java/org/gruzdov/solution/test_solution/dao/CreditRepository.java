package org.gruzdov.solution.test_solution.dao;

import org.gruzdov.solution.test_solution.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface CreditRepository extends JpaRepository<Credit, UUID> {
    Credit findByTitle(String title);
    List<Credit> findByBankId(UUID bankId);
}
