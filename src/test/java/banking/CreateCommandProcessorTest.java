package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateCommandProcessorTest {
    CreateCommandProcessor createProcessor;
    Bank bank;
    CommandStorage commands;

    @BeforeEach
    void setUp() {
        bank = new Bank(commands);
        createProcessor = new CreateCommandProcessor(bank);
    }

    @Test
    void valid_checking_account_creation() {
        createProcessor.createCheckingAccount("12345678", 0.06);
        Assertions.assertTrue(bank.accountExistsByQuickID("12345678"));
    }

    @Test
    void valid_cd_account_creation() {
        createProcessor.createCDAccount("12345678", 0.06, 500);
        Assertions.assertTrue(bank.accountExistsByQuickID("12345678"));
    }

    @Test
    void valid_savings_account_creation() {
        createProcessor.createSavingsAccount("12345678", 0.06);
        Assertions.assertTrue(bank.accountExistsByQuickID("12345678"));
    }
}
