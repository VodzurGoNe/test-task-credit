package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
import org.gruzdov.solution.test_solution.worker.calculation_payment.CalculationPaymentWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

/**
 * @author Vladislav Gruzdov
 */
@Service
public class CalculationPaymentServiceImpl implements CalculationPaymentService {

    private final CreditOfferService creditOfferService;
    private final PaymentScheduleService paymentScheduleService;
    private final CalculationPaymentWorker calculationPaymentWorker;
    private final EntityManager entityManager;

    @Autowired
    public CalculationPaymentServiceImpl(CreditOfferService creditOfferService,
                                         PaymentScheduleService paymentScheduleService,
                                         CalculationPaymentWorker calculationPaymentWorker,
                                         EntityManager entityManager) {
        this.creditOfferService = creditOfferService;
        this.paymentScheduleService = paymentScheduleService;
        this.calculationPaymentWorker = calculationPaymentWorker;
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void collectDataAboutCreditOffer(CreditOffer creditOffer) {
        UUID creditOfferId = creditOffer.getId();
        CreditOffer reloadedCreditOffer = creditOfferService.getCreditOffer(creditOfferId);
        if (reloadedCreditOffer != null) {
            creditOffer = entityManager.merge(creditOffer);
            paymentScheduleService.deleteAllByCreditOfferId(creditOfferId);
        }

        List<PaymentSchedule> paymentScheduleList = calculationPaymentWorker.collectDataAboutCreditOffer(creditOffer);
        saveAll(creditOffer, paymentScheduleList);
    }

    private void saveAll(CreditOffer creditOffer, List<PaymentSchedule> paymentScheduleList) {
        creditOfferService.saveCreditOffer(creditOffer);
        paymentScheduleService.saveAllPaymentSchedules(paymentScheduleList);
    }

}