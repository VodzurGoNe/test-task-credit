package test_solution.service;

import org.springframework.data.domain.Page;
import test_solution.entity.Credit;

import java.util.List;
import java.util.UUID;

public interface CreditService {
    List<Credit> getAllCredits();

    void saveCredit(Credit credit);

    Credit getCredit(UUID id);

    void deleteCredit(UUID id);

    Credit findByTitle(String title);

    List<Credit> findByBankId(UUID bankId);

    Page<Credit> findPaginated(int pageNo, int pageSize
            , String sortField, String sortDirection);

    Page<Credit> findPaginated(UUID bankId, int pageNo, int pageSize
            , String sortField, String sortDirection);

}
