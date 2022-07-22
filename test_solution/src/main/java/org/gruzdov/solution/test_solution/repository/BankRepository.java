package org.gruzdov.solution.test_solution.repository;

import org.gruzdov.solution.test_solution.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Vladislav Gruzdov
 */
@Repository
public interface BankRepository extends JpaRepository<Bank, UUID> {
}
