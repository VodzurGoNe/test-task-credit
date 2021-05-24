package org.gruzdov.solution.test_solution.dao;

import org.gruzdov.solution.test_solution.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CreditRepository extends JpaRepository<Credit, UUID> {
}
