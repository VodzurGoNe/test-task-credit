package org.gruadov.solution.test_solution;

import org.gruzdov.solution.test_solution.entity.Bank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankEntityEqualsTest {

    @Test
    public void newBankShouldEntityEquals() {
        Bank firstBank = new Bank();
        firstBank.setTitle("KSK");
        Bank secondBank = new Bank();
        secondBank.setTitle("JBC");
        Assertions.assertNotEquals(firstBank, secondBank);
    }
}
