package org.gruzdov.solution.test_solution.service;

import lombok.RequiredArgsConstructor;
import org.gruzdov.solution.test_solution.repository.PaymentScheduleRepository;
import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaymentScheduleServiceImpl implements PaymentScheduleService {

    private final PaymentScheduleRepository paymentScheduleRepository;

    @Override
    public void savePaymentSchedule(PaymentSchedule paymentSchedule) {
        paymentScheduleRepository.save(paymentSchedule);
    }

    @Override
    public void saveAllPaymentSchedules(List<PaymentSchedule> paymentScheduleList) {
        paymentScheduleRepository.saveAll(paymentScheduleList);
    }

    @Nullable
    @Override
    public PaymentSchedule getPaymentSchedule(UUID id) {
        return paymentScheduleRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePaymentSchedule(UUID id) {
        paymentScheduleRepository.deleteById(id);
    }

    @Override
    public void deleteAllByCreditOfferId(UUID creditOfferId) {
        paymentScheduleRepository.deleteAllByCreditOfferId(creditOfferId);
    }

    @Override
    public List<PaymentSchedule> findByCreditOfferId(UUID creditOfferId) {
        return paymentScheduleRepository.findByCreditOfferId(creditOfferId);
    }
}
