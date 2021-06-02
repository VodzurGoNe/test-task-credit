package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.dao.PaymentScheduleRepository;
import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentScheduleServiceImpl implements PaymentScheduleService {
    private final PaymentScheduleRepository paymentScheduleRepository;

    @Autowired
    public PaymentScheduleServiceImpl(PaymentScheduleRepository paymentScheduleRepository) {
        this.paymentScheduleRepository = paymentScheduleRepository;
    }

    @Override
    public void savePaymentSchedule(PaymentSchedule paymentSchedule) {
        paymentScheduleRepository.save(paymentSchedule);
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
    public List<PaymentSchedule> findByCreditOfferId(UUID creditOfferId) {
        return paymentScheduleRepository.findByCreditOfferId(creditOfferId);
    }

}
