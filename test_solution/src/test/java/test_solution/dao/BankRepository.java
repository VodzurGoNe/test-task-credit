package test_solution.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import test_solution.entity.Bank;

import java.util.UUID;


public interface BankRepository extends JpaRepository<Bank, UUID> {
    Bank findByTitle(String title);
}
