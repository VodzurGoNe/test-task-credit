package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.entity.PaymentSchedule;

import java.util.List;
import java.util.UUID;

public interface PaymentScheduleService {
    void savePaymentSchedule(PaymentSchedule paymentSchedule);
    PaymentSchedule getPaymentSchedule(UUID id);
    void deletePaymentSchedule(UUID id);
    List<PaymentSchedule> findByCreditOfferId(UUID creditOfferId);

}
