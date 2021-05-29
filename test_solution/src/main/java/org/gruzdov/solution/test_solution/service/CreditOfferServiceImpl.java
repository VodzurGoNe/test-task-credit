package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.dao.CreditOfferRepository;
import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreditOfferServiceImpl implements CreditOfferService {
    private final CreditOfferRepository creditOfferRepository;

    @Autowired
    public CreditOfferServiceImpl(CreditOfferRepository creditOfferRepository) {
        this.creditOfferRepository = creditOfferRepository;
    }

    @Override
    public List<CreditOffer> getAllCreditOffers() {
        return creditOfferRepository.findAll();
    }

    @Override
    public void saveCreditOffer(CreditOffer creditOffer) {
        creditOfferRepository.save(creditOffer);
    }

    @Override
    public CreditOffer getCreditOffer(UUID id) {
        CreditOffer creditOffer = null;
        Optional<CreditOffer> optional = creditOfferRepository.findById(id);
        if (optional.isPresent())
            creditOffer = optional.get();

        return creditOffer;
    }

    @Override
    public void deleteCreditOffer(UUID id) {
        creditOfferRepository.deleteById(id);
    }

    @Override
    public List<CreditOffer> findByBankId(UUID bankId) {
        return creditOfferRepository.findByBankId(bankId);
    }

    @Override
    public List<CreditOffer> findByClientId(UUID clientId) {
        return creditOfferRepository.findByClientId(clientId);
    }

/*
    @Override
    public Page<CreditOffer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.creditOfferRepository.findAll(pageable);
    }

 */
}
