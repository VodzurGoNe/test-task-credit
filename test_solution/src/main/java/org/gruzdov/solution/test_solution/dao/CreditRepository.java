package org.gruzdov.solution.test_solution.dao;

import org.gruzdov.solution.test_solution.entity.Credit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CreditRepository extends JpaRepository<Credit, UUID> {
    Credit findByTitle(String title);
    List<Credit> findByBankId(UUID bankId);
    Page<Credit> findPaginatedByBankId(UUID bankId, Pageable pageable);
}