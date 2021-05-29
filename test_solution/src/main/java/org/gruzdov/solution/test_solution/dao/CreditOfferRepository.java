package org.gruzdov.solution.test_solution.dao;

import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface CreditOfferRepository extends JpaRepository<CreditOffer, UUID> {
    List<CreditOffer> findByBankId(UUID bankId);
    List<CreditOffer> findByClientId(UUID bankId);
}
