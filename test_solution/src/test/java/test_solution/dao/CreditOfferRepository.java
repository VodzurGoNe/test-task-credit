package test_solution.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import test_solution.entity.CreditOffer;

import java.util.List;
import java.util.UUID;


public interface CreditOfferRepository extends JpaRepository<CreditOffer, UUID> {
    List<CreditOffer> findByBankId(UUID bankId);
    List<CreditOffer> findByClientId(UUID bankId);
}
