package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CreditOfferService {
    List<CreditOffer> getAllCreditOffers();

    void saveCreditOffer(CreditOffer creditOffer);

    CreditOffer getCreditOffer(UUID id);

    void deleteCreditOffer(UUID id);

//    CreditOffer findByTitle(String title);

    Page<CreditOffer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
