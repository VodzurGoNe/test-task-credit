package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CalculationPaymentServiceImpl implements CalculationPaymentService {
    private final CreditOfferService creditOfferService;
    private final PaymentScheduleService paymentScheduleService;
    private final EntityManager entityManager;

    public CalculationPaymentServiceImpl(CreditOfferService creditOfferService,
                                         PaymentScheduleService paymentScheduleService,
                                         EntityManager entityManager) {
        this.creditOfferService = creditOfferService;
        this.paymentScheduleService = paymentScheduleService;
        this.entityManager = entityManager;
    }

    @Override
    public void collectDataAboutCreditOffer(CreditOffer creditOffer) {
        if (creditOffer.getId() != null) {
            creditOffer = mergeAndClearPaymentScheduleList(creditOffer);
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
        calculateAndCreatePaymentSchedules(periodInMonths, remains, monthlyPaymentToBodyCredit,
                monthlyPaymentToPercentCredit, creditOffer);
    }

    private void calculateAndCreatePaymentSchedules(Integer periodInMonths, BigDecimal remains,
                                                    BigDecimal monthlyPaymentToBodyCredit,
                                                    BigDecimal monthlyPaymentToPercentCredit, CreditOffer creditOffer) {
        BigDecimal monthPay, percentSum = BigDecimal.ZERO;
        List<PaymentSchedule> paymentScheduleList = new ArrayList<>(periodInMonths);
        for (int i = 0; i < periodInMonths; i++) {
            percentSum = percentSum.add(monthlyPaymentToPercentCredit);
            monthPay = monthlyPaymentToBodyCredit.add(monthlyPaymentToPercentCredit);
            remains = remains.subtract(monthlyPaymentToBodyCredit);
            paymentScheduleList.add(
                    PaymentSchedule.builder()
                            .paymentAmount(monthPay)
                            .paymentDate(LocalDate.now().plusMonths(i + 1))
                            .amountOfTheBody(monthlyPaymentToBodyCredit)
                            .amountOfThePercent(monthlyPaymentToPercentCredit)
                            .remains(remains.compareTo(BigDecimal.ZERO) > 0 ? remains : BigDecimal.ZERO)
                            .creditOffer(creditOffer)
                            .build()
            );
        }
        creditOffer.setPercentSum(percentSum);
        saveAll(creditOffer, paymentScheduleList);
    }

    private void saveAll(CreditOffer creditOffer, List<PaymentSchedule> paymentScheduleList) {
        creditOfferService.saveCreditOffer(creditOffer);
        paymentScheduleService.saveAllPaymentSchedules(paymentScheduleList);
    }

    private CreditOffer mergeAndClearPaymentScheduleList(CreditOffer creditOffer) {
        creditOffer = entityManager.merge(creditOffer);
        paymentScheduleService.deleteAllByCreditOfferId(creditOffer.getId());
        return creditOffer;
    }
}
