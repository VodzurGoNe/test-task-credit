package org.gruzdov.solution.test_solution.worker.calculation_payment;

import org.gruzdov.solution.test_solution.entity.CreditOffer;
import org.gruzdov.solution.test_solution.entity.PaymentSchedule;

import java.util.List;

/**
 * @author Vladislav Gruzdov
 */
public interface CalculationPaymentWorker {

    String NAME = "solution_calculationPaymentWorker";

    List<PaymentSchedule> collectDataAboutCreditOffer(CreditOffer creditOffer);
}
