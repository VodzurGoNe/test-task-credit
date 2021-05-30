package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface PaymentScheduleService {
    List<PaymentSchedule> getAllPaymentSchedules();

    void savePaymentSchedule(PaymentSchedule paymentSchedule);

    PaymentSchedule getPaymentSchedule(UUID id);

    void deletePaymentSchedule(UUID id);

    List<PaymentSchedule> findByCreditOfferId(UUID creditOfferId);

    Page<PaymentSchedule> findPaginated(int pageNo, int pageSize
            , String sortField, String sortDirection, UUID id);

}
