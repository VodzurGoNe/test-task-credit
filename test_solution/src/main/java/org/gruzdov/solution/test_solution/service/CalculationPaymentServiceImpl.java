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

    public CalculationPaymentServiceImpl(PaymentScheduleRepository paymentScheduleRepository) {
        this.paymentScheduleRepository = paymentScheduleRepository;
    }

    @Override
    public void calculationPaymentSchedule(CreditOffer creditOffer) {

        BigDecimal amount = creditOffer.getAmount();
        //Сумма кредита
        BigDecimal firstPay = creditOffer.getFirstPay();
        //Первый взнос
        BigDecimal percent = creditOffer.getCredit().getPercent();
        //Процент
        Integer periodInMonths = creditOffer.getPeriodInMonths();
        //Срок кредитования
        BigDecimal remains = amount.subtract(firstPay);

        BigDecimal repaymentInMonth = remains.divide(BigDecimal.valueOf(periodInMonths)
                ,2, RoundingMode.HALF_EVEN);
        //Перерасчёт с учётом первого платежа
        BigDecimal percentMonth = percent.divide(BigDecimal.valueOf(12)
                ,4, RoundingMode.HALF_EVEN);
        //Ежемесяный платёж без процентов

        PaymentSchedule paymentSchedule;

        BigDecimal monthPay;
        for (int i = 0; i < periodInMonths; i++) {
            BigDecimal monthPayForPercent = remains.multiply(percentMonth)
                    .divide(BigDecimal.valueOf(100)
                            ,2
                            ,RoundingMode.HALF_EVEN);
            //Отчисления по процентам
            monthPay = repaymentInMonth.add(monthPayForPercent);
            //Общий платёж
            remains = remains.subtract(repaymentInMonth);
            //Остаток
            paymentSchedule = new PaymentSchedule();

            paymentSchedule.setId(i + 1);
            paymentSchedule.setPaymentAmount(monthPay);

            paymentSchedule.setPaymentDate(LocalDate.now().plusMonths(i + 1));
            paymentSchedule.setAmountOfTheBody(repaymentInMonth);
            paymentSchedule.setAmountOfThePercent(monthPayForPercent);
            paymentSchedule.setRemains(remains);
            paymentSchedule.setCreditOffer(creditOffer);

            paymentScheduleRepository.save(paymentSchedule);
        }
    }
}
