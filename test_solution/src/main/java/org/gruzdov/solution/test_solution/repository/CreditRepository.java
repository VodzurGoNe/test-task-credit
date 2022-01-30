package org.gruzdov.solution.test_solution.repository;

import org.gruzdov.solution.test_solution.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CreditRepository extends JpaRepository<Credit, UUID> {

    List<Credit> findByBankId(UUID bankId);
}
