package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.dao.CreditRepository;
import org.gruzdov.solution.test_solution.entity.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public void saveCredit(Credit credit) {
        creditRepository.save(credit);
    }

    @Nullable
    @Override
    public Credit getCredit(UUID id) {
        return creditRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCredit(UUID id) {
        creditRepository.deleteById(id);
    }

    @Override
    public List<Credit> findByBankId(UUID bankId) {
        return creditRepository.findByBankId(bankId);
    }

}
