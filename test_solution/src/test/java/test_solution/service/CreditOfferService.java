package test_solution.service;

import test_solution.entity.CreditOffer;

import java.util.List;
import java.util.UUID;

public interface CreditOfferService {
    List<CreditOffer> getAllCreditOffers();

    void saveCreditOffer(CreditOffer creditOffer);

    CreditOffer getCreditOffer(UUID id);

    void deleteCreditOffer(UUID id);

    List<CreditOffer> findByBankId(UUID bankId);

    List<CreditOffer> findByClientId(UUID clientId);

//    Page<CreditOffer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
