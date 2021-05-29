package org.gruzdov.solution.test_solution.dao;

import org.gruzdov.solution.test_solution.entity.Credit;
import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, UUID> {
    List<PaymentSchedule> findByCreditOfferId(UUID creditOfferId);
}
