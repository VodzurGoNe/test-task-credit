package test_solution.service;

import org.springframework.stereotype.Service;
import test_solution.dao.PaymentScheduleRepository;
import test_solution.entity.CreditOffer;
import test_solution.entity.PaymentSchedule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class CalculationPaymentServiceTestImplTest implements CalculationPaymentServiceTest {
    private final PaymentScheduleRepository paymentScheduleRepository;
    private final CreditOfferService creditOfferService;

    public CalculationPaymentServiceTestImplTest(PaymentScheduleRepository paymentScheduleRepository
            , CreditOfferService creditOfferService) {
        this.paymentScheduleRepository = paymentScheduleRepository;
        this.creditOfferService = creditOfferService;
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
        //Перерасчёт с учётом первого платежа
        BigDecimal repaymentInMonth = remains.divide(BigDecimal.valueOf(periodInMonths)
                ,2, RoundingMode.HALF_EVEN);
        //Ежемесячная сумма гашения тела кредита
        BigDecimal percentMonth = percent.divide(BigDecimal.valueOf(12)
                ,4, RoundingMode.HALF_EVEN);
        //Ежемесячный процент


        BigDecimal monthPay;
        BigDecimal monthPayForPercent = remains.multiply(percentMonth)
                .divide(BigDecimal.valueOf(100)
                        , 2
                        , RoundingMode.HALF_EVEN);
        //Отчисления по процентам
        BigDecimal percentSum = new BigDecimal("0");

        PaymentSchedule paymentSchedule;
        for (int i = 0; i < periodInMonths; i++) {
            percentSum = percentSum.add(monthPayForPercent);
            //Расчет итоговой суммы процентов по кредиту
            monthPay = repaymentInMonth.add(monthPayForPercent);
            //Общий платёж

            //Ежемесяный платёж без процентов

            remains = remains.subtract(repaymentInMonth);
            //Остаток
            paymentSchedule = new PaymentSchedule();

            //paymentSchedule.setId(i + 1);
            paymentSchedule.setPaymentAmount(monthPay);

            paymentSchedule.setPaymentDate(LocalDate.now().plusMonths(i + 1));
            paymentSchedule.setAmountOfTheBody(repaymentInMonth);
            paymentSchedule.setAmountOfThePercent(monthPayForPercent);
            paymentSchedule.setRemains(remains);
            paymentSchedule.setCreditOffer(creditOffer);

            paymentScheduleRepository.save(paymentSchedule);
        }

        creditOffer.setPercentSum(percentSum);
        creditOfferService.saveCreditOffer(creditOffer);
    }
}
