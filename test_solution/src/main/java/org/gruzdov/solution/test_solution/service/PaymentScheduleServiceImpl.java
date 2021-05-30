package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.dao.PaymentScheduleRepository;
import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
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
public class PaymentScheduleServiceImpl implements PaymentScheduleService {
    private final PaymentScheduleRepository paymentScheduleRepository;

    @Autowired
    public PaymentScheduleServiceImpl(PaymentScheduleRepository paymentScheduleRepository) {
        this.paymentScheduleRepository = paymentScheduleRepository;
    }

    @Override
    public List<PaymentSchedule> getAllPaymentSchedules() {
        return paymentScheduleRepository.findAll();
    }

    @Override
    public void savePaymentSchedule(PaymentSchedule paymentSchedule) {
        paymentScheduleRepository.save(paymentSchedule);
    }

    @Override
    public PaymentSchedule getPaymentSchedule(UUID id) {
        PaymentSchedule paymentSchedule = null;
        Optional<PaymentSchedule> optional = paymentScheduleRepository.findById(id);
        if (optional.isPresent())
            paymentSchedule = optional.get();

        return paymentSchedule;
    }

    @Override
    public void deletePaymentSchedule(UUID id) {
        paymentScheduleRepository.deleteById(id);
    }

    @Override
    public List<PaymentSchedule> findByCreditOfferId(UUID creditOfferId) {
        return paymentScheduleRepository.findByCreditOfferId(creditOfferId);
    }

    @Override
    public Page<PaymentSchedule> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection
            , UUID id // creditOfferId
    ) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        //return findAllByCreditOfferId(id);
        return paymentScheduleRepository.findAll(pageable);
    }
}
