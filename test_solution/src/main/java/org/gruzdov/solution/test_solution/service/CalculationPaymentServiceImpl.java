package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.dao.PaymentScheduleRepository;
import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.gruzdov.solution.test_solution.entity.PaymentSchedule;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class CalculationPaymentServiceImpl implements CalculationPaymentService {
    private final PaymentScheduleRepository paymentScheduleRepository;
    private final CreditOfferService creditOfferService;

    public CalculationPaymentServiceImpl(PaymentScheduleRepository paymentScheduleRepository
            , CreditOfferService creditOfferService) {
        this.paymentScheduleRepository = paymentScheduleRepository;
        this.creditOfferService = creditOfferService;
    }

    @Override
    public void calculationPaymentSchedule(CreditOffer creditOffer) {

        if (creditOffer.getPaymentSchedules() != null) {
            paymentScheduleRepository.deleteByCreditOfferId(creditOffer.getId());
        }

        BigDecimal creditOfferAmount = creditOffer.getAmount();
        BigDecimal firstPay = creditOffer.getFirstPay();
        BigDecimal percent = creditOffer.getCredit().getPercent();
        Integer periodInMonths = creditOffer.getPeriodInMonths();
        BigDecimal remains = creditOfferAmount.subtract(firstPay);
        BigDecimal repaymentInMonth = remains.divide(BigDecimal.valueOf(periodInMonths)
                ,2, RoundingMode.HALF_EVEN);
        BigDecimal percentMonth = percent.divide(BigDecimal.valueOf(12)
                ,4, RoundingMode.HALF_EVEN);

        BigDecimal monthPay;
        BigDecimal monthPayForPercent = remains.multiply(percentMonth)
                .divide(BigDecimal.valueOf(100)
                        , 2
                        , RoundingMode.HALF_EVEN);

        BigDecimal percentSum = BigDecimal.ZERO;

        for (int i = 0; i < periodInMonths; i++) {

            percentSum = percentSum.add(monthPayForPercent);
            monthPay = repaymentInMonth.add(monthPayForPercent);
            remains = remains.subtract(repaymentInMonth);

            paymentScheduleRepository.save(
            PaymentSchedule.builder().paymentAmount(monthPay)
                    .paymentDate(LocalDate.now().plusMonths(i + 1))
                    .amountOfTheBody(repaymentInMonth)
                    .amountOfThePercent(monthPayForPercent)
                    .remains(remains)
                    .creditOffer(creditOffer)
                    .build());

        }

        creditOffer.setPercentSum(percentSum);
        creditOfferService.saveCreditOffer(creditOffer);
    }

}
