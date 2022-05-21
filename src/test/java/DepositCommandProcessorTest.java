import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositCommandProcessorTest {
    DepositCommandProcessor depositProcessor;
    CreateCommandProcessor createProcessor;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
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

    @Test
    void get_deposit_amount_returns_correct_val() {
        Double test = depositProcessor.getDeposit("deposit 12345678 1500");
        Assertions.assertEquals(1500, test);
    }

    @Test
    void get_deposit_id_returns_correct_id() {
        String test = depositProcessor.getDepositID("deposit 12345678 1500");
        Assertions.assertEquals("12345678", test);
    }
}
