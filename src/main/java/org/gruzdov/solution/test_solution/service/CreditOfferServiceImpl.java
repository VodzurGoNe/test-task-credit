package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.gruzdov.solution.test_solution.repository.CreditOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Vladislav Gruzdov
 */
@Service
public class CreditOfferServiceImpl implements CreditOfferService {

    private final CreditOfferRepository creditOfferRepository;

    @Autowired
    public CreditOfferServiceImpl(CreditOfferRepository creditOfferRepository) {
        this.creditOfferRepository = creditOfferRepository;
    }

    @Override
    public void saveCreditOffer(CreditOffer creditOffer) {
        creditOfferRepository.save(creditOffer);
    }

    @Nullable
    @Override
    public CreditOffer getCreditOffer(UUID id) {
        return creditOfferRepository.findById(id)
                .orElse(null);
    }

    @Override
    public void deleteCreditOffer(UUID id) {
        creditOfferRepository.deleteById(id);
    }

    @Override
    public List<CreditOffer> findByClientId(UUID clientId) {
        return creditOfferRepository.findByClientId(clientId);
    }
}
