package org.gruzdov.solution.test_solution.worker.calculation_payment;

import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladislav Gruzdov
 */
@Component(CalculationPaymentWorker.NAME)
public class CalculationPaymentWorkerImpl implements CalculationPaymentWorker {

    @Transactional
    @Override
    public List<PaymentSchedule> collectDataAboutCreditOffer(CreditOffer creditOffer) {
        BigDecimal firstPay = creditOffer.getFirstPay() != null
                ? creditOffer.getFirstPay()
                : BigDecimal.ZERO;

        BigDecimal remainingCreditAmount = creditOffer.getAmount().subtract(firstPay);

        BigDecimal monthlyPaymentToBodyCredit = remainingCreditAmount
                .divide(BigDecimal.valueOf(creditOffer.getPeriodInMonths()), 2, RoundingMode.HALF_EVEN);

        BigDecimal interestRatePerMonth = creditOffer.getCredit().getPercent()
                .divide(BigDecimal.valueOf(12), 4, RoundingMode.HALF_EVEN);

        BigDecimal monthlyPaymentToPercentCredit = remainingCreditAmount.multiply(interestRatePerMonth)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);

        return calculateAndCreatePaymentSchedules(
                remainingCreditAmount, monthlyPaymentToBodyCredit,
                monthlyPaymentToPercentCredit, creditOffer
        );
    }

    private List<PaymentSchedule> calculateAndCreatePaymentSchedules(BigDecimal remainingCreditAmount,
                                                                     BigDecimal monthlyPaymentToBodyCredit,
                                                                     BigDecimal monthlyPaymentToPercentCredit,
                                                                     CreditOffer creditOffer) {
        int periodInMonths = creditOffer.getPeriodInMonths();
        BigDecimal monthPay, percentSum = BigDecimal.ZERO;

        List<PaymentSchedule> paymentScheduleList = new ArrayList<>(periodInMonths);
        for (int i = 0; i < periodInMonths; i++) {
            monthPay = monthlyPaymentToBodyCredit.add(monthlyPaymentToPercentCredit);
            percentSum = percentSum.add(monthlyPaymentToPercentCredit);
            remainingCreditAmount = remainingCreditAmount.subtract(monthlyPaymentToBodyCredit);

            paymentScheduleList.add(
                    PaymentSchedule.builder()
                            .paymentAmount(monthPay)
                            .paymentDate(LocalDate.now().plusMonths(i + 1))
                            .amountOfTheBody(monthlyPaymentToBodyCredit)
                            .amountOfThePercent(monthlyPaymentToPercentCredit)
                            .remains(remainingCreditAmount.compareTo(BigDecimal.ZERO) > 0
                                    ? remainingCreditAmount
                                    : BigDecimal.ZERO)
                            .creditOffer(creditOffer)
                            .build()
            );
        }
        creditOffer.setPercentSum(percentSum);

        return paymentScheduleList;
    }

}
