package org.gruzdov.solution.test_solution.dao;

import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, UUID> {
}
