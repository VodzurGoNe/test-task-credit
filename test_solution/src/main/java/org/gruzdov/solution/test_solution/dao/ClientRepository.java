package org.gruzdov.solution.test_solution.dao;

import org.gruzdov.solution.test_solution.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findAllByFio(String fio);
    List<Client> findByBankId(UUID bankId);
    Page<Client> findPaginatedByBankId(UUID bankId, Pageable pageable);
}