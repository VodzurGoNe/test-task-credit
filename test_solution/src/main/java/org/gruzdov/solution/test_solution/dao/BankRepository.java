package org.gruzdov.solution.test_solution.dao;

import org.gruzdov.solution.test_solution.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<Bank, UUID> {
    Bank findByTitle(String title);
}