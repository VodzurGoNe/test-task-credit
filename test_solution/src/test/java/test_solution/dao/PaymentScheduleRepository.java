package test_solution.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import test_solution.entity.PaymentSchedule;

import java.util.List;
import java.util.UUID;


public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, UUID> {
    List<PaymentSchedule> findByCreditOfferId(UUID creditOfferId);
}
