package org.gruzdov.solution.test_solution.service;

import org.gruzdov.solution.test_solution.entity.CreditOffer;

import java.math.BigDecimal;

public interface CalculationPaymentService {
    void collectingDataAboutCreditOffer(CreditOffer creditOffer);
    void calculationAndCreatePaymentSchedule(Integer periodInMonths, BigDecimal remains,
                                             BigDecimal monthlyPaymentToBodyCredit,
                                             BigDecimal monthlyPaymentToPercentCredit, CreditOffer creditOffer);
}
