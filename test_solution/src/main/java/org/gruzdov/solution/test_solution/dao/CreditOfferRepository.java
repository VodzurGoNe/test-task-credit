package org.gruzdov.solution.test_solution.dao;

import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CreditOfferRepository extends JpaRepository<CreditOffer, UUID> {
}
