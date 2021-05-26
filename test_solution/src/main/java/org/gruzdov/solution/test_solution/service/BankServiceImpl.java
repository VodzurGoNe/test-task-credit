package org.gruzdov.solution.test_solution.service;


import org.gruzdov.solution.test_solution.dao.BankRepository;
import org.gruzdov.solution.test_solution.entity.Bank;
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
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public void saveBank(Bank bank) {
        bankRepository.save(bank);
    }

    @Override
    public Bank getBank(UUID id) {
        Bank bank = null;
        Optional<Bank> optional = bankRepository.findById(id);
        if (optional.isPresent())
            bank = optional.get();

        return bank;
    }

    @Override
    public void deleteBank(UUID id) {
        bankRepository.deleteById(id);
    }

    @Override
    public Bank findByTitle(String title) {
        Bank bank = bankRepository.findByTitle(title);

        return bank;
    }

    @Override
    public Page<Bank> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.bankRepository.findAll(pageable);
    }
}
