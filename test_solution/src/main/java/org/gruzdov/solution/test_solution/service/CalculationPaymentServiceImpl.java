package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.dao.PaymentScheduleRepository;
import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculationPaymentServiceImpl implements CalculationPaymentService {
    private final PaymentScheduleRepository paymentScheduleRepository;
    private final CreditOfferService creditOfferService;
    private final EntityManager entityManager;

    public CalculationPaymentServiceImpl(PaymentScheduleRepository paymentScheduleRepository,
                                         CreditOfferService creditOfferService,
                                         EntityManager entityManager) {
        this.paymentScheduleRepository = paymentScheduleRepository;
        this.creditOfferService = creditOfferService;
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void calculationPaymentSchedule(CreditOffer creditOffer) {
        if (creditOffer.getId() != null) {
            creditOffer = entityManager.merge(creditOffer);
            paymentScheduleRepository.deleteAllByCreditOfferId(creditOffer.getId());
        }

        BigDecimal creditOfferAmount = creditOffer.getAmount();
        BigDecimal firstPay = creditOffer.getFirstPay() != null ? creditOffer.getFirstPay() : BigDecimal.ZERO;
        BigDecimal percent = creditOffer.getCredit().getPercent();
        Integer periodInMonths = creditOffer.getPeriodInMonths();
        BigDecimal remains = creditOfferAmount.subtract(firstPay);
        BigDecimal monthlyPaymentToBodyCredit = remains.divide(BigDecimal.valueOf(periodInMonths),
                2, RoundingMode.HALF_EVEN);
        BigDecimal interestRatePerMonth = percent.divide(BigDecimal.valueOf(12),
                4, RoundingMode.HALF_EVEN);
        BigDecimal monthlyPaymentToPercentCredit = remains.multiply(interestRatePerMonth)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
        BigDecimal monthPay;
        BigDecimal percentSum = BigDecimal.ZERO;
        List<PaymentSchedule> paymentScheduleList = new ArrayList<>(periodInMonths);
        for (int i = 0; i < periodInMonths; i++) {
            percentSum = percentSum.add(monthlyPaymentToPercentCredit);
            monthPay = monthlyPaymentToBodyCredit.add(monthlyPaymentToPercentCredit);
            remains = remains.subtract(monthlyPaymentToBodyCredit);
            paymentScheduleList.add(PaymentSchedule.builder().paymentAmount(monthPay)
                    .paymentDate(LocalDate.now().plusMonths(i + 1))
                    .amountOfTheBody(monthlyPaymentToBodyCredit)
                    .amountOfThePercent(monthlyPaymentToPercentCredit)
                    .remains(remains.compareTo(BigDecimal.ZERO) > 0 ? remains : BigDecimal.ZERO)
                    .creditOffer(creditOffer)
                    .build());
        }
        creditOffer.setPercentSum(percentSum);
        creditOfferService.saveCreditOffer(creditOffer);
        paymentScheduleRepository.saveAll(paymentScheduleList);
    }

}
