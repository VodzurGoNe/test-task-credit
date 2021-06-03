package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.dao.BankRepository;
import org.gruzdov.solution.test_solution.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void saveBank(Bank bank) {
        bankRepository.save(bank);
    }

    @Nullable
    @Override
    public Bank getBank(UUID id) {
        return bankRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBank(UUID id) {
        bankRepository.deleteById(id);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }
}
