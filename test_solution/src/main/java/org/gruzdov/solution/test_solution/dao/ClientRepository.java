package org.gruzdov.solution.test_solution.dao;

import org.gruzdov.solution.test_solution.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findAllByFio(String fio);
}
