package org.gruzdov.solution.test_solution.dao;

import org.gruzdov.solution.test_solution.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BankRepository extends JpaRepository<Bank, UUID> {
    Bank findByTitle(String title);
}
