package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.entity.CreditOffer;

import java.util.List;
import java.util.UUID;

public interface CreditOfferService {
    void saveCreditOffer(CreditOffer creditOffer);
    CreditOffer getCreditOffer(UUID id);
    void deleteCreditOffer(UUID id);
    List<CreditOffer> findByClientId(UUID clientId);

}
