package org.gruzdov.solution.test_solution.service;


import org.gruzdov.solution.test_solution.dao.CreditRepository;
import org.gruzdov.solution.test_solution.entity.Credit;
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
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public List<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    @Override
    public void saveCredit(Credit credit) {
        creditRepository.save(credit);
    }

    @Override
    public Credit getCredit(UUID id) {
        Credit credit = null;
        Optional<Credit> optional = creditRepository.findById(id);
        if (optional.isPresent())
            credit = optional.get();

        return credit;
    }

    @Override
    public void deleteCredit(UUID id) {
        creditRepository.deleteById(id);
    }

    @Override
    public Credit findByTitle(String title) {
        Credit credit = creditRepository.findByTitle(title);

        return credit;
    }

    @Override
    public Page<Credit> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.creditRepository.findAll(pageable);
    }
}
