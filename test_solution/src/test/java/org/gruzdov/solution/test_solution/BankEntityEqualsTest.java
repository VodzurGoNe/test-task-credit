package org.gruzdov.solution.test_solution;

import org.gruzdov.solution.test_solution.entity.Bank;
import org.gruzdov.solution.test_solution.global.factory.SimpleFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Vladislav Gruzdov
 */
public class BankEntityEqualsTest {

    @Test
    public void newBankShouldEntityEquals() {
        Bank firstBank = SimpleFactory.create(Bank.class);
        firstBank.setTitle("KSK");
        Bank secondBank = SimpleFactory.create(Bank.class);
        secondBank.setTitle("JBC");

        Assertions.assertTrue(firstBank.hashCode() != secondBank.hashCode());
        Assertions.assertNotEquals(firstBank, secondBank);
    }

}
