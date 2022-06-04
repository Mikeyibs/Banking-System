package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositCommandProcessorTest {
    DepositCommandProcessor depositProcessor;
    CreateCommandProcessor createProcessor;
    Bank bank;
    CommandStorage commands;

    @BeforeEach
    void setUp() {
        bank = new Bank(commands);
        depositProcessor = new DepositCommandProcessor(bank);
        createProcessor = new CreateCommandProcessor(bank);
    }

    @Test
    void valid_deposit_into_checking_using_processor() {
        createProcessor.createCheckingAccount("12345678", 0.06);
        depositProcessor.processor("deposit 12345678 500");
        Assertions.assertEquals(500, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void valid_deposit_into_checking_account() {
        createProcessor.createCheckingAccount("12345678", 0.06);
        depositProcessor.deposit("12345678", 500);
        Assertions.assertEquals(500, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void valid_deposit_into_savings_account() {
        createProcessor.createSavingsAccount("33445566", 0.06);
        depositProcessor.deposit("33445566", 500);
        Assertions.assertEquals(500, bank.getAccounts().get("33445566").getMoney());
    }
}
