package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {
    private static final String VALID_CHECKING_CMD = "create checking 12345678 0.06";
    private static final String VALID_SAVINGS_CMD = "create savings 33225566 0.06";
    private static final String VALID_CD_CMD = "create cd 87654321 0.06 2000";
    private static final String VALID_DEPOSIT_CHECKING_CMD = "deposit 12345678 500";
    private static final String VALID_DEPOSIT_SAVINGS_CMD = "deposit 33225566 500";
    Bank bank;
    CommandProcessor commandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void process_valid_create_checking_command() {
        commandProcessor.processor(VALID_CHECKING_CMD);
        Assertions.assertTrue(bank.accountExistsByQuickID("12345678"));
    }

    @Test
    void process_valid_create_savings_command() {
        commandProcessor.processor(VALID_SAVINGS_CMD);
        Assertions.assertTrue(bank.accountExistsByQuickID("33225566"));
    }

    @Test
    void process_valid_cd_command() {
        commandProcessor.processor((VALID_CD_CMD));
        Assertions.assertTrue(bank.accountExistsByQuickID("87654321"));
    }

    @Test
    void process_two_valid_create_commands() {
        commandProcessor.processor(VALID_CHECKING_CMD);
        commandProcessor.processor(VALID_SAVINGS_CMD);

        Assertions.assertTrue(bank.accountExistsByQuickID("12345678"));
        Assertions.assertTrue(bank.accountExistsByQuickID("33225566"));
    }

    @Test
    void process_valid_deposit_into_checking() {
        commandProcessor.processor(VALID_CHECKING_CMD);

        commandProcessor.processor(VALID_DEPOSIT_CHECKING_CMD);

        Assertions.assertEquals(500, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void process_valid_deposit_into_checking_twice() {
        commandProcessor.processor(VALID_CHECKING_CMD);

        commandProcessor.processor(VALID_DEPOSIT_CHECKING_CMD);
        commandProcessor.processor(VALID_DEPOSIT_CHECKING_CMD);

        Assertions.assertEquals(1000, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void process_valid_deposit_into_savings() {
        commandProcessor.processor(VALID_SAVINGS_CMD);

        commandProcessor.processor(VALID_DEPOSIT_SAVINGS_CMD);

        Assertions.assertEquals(500, bank.getAccounts().get("33225566").getMoney());
    }

    @Test
    void process_valid_deposit_into_savings_twice() {
        commandProcessor.processor(VALID_SAVINGS_CMD);

        commandProcessor.processor(VALID_DEPOSIT_SAVINGS_CMD);
        commandProcessor.processor(VALID_DEPOSIT_SAVINGS_CMD);

        Assertions.assertEquals(1000, bank.getAccounts().get("33225566").getMoney());
    }

    @Test
    void process_back_to_back_deposits_from_two_different_account_types() {
        commandProcessor.processor(VALID_CHECKING_CMD);
        commandProcessor.processor(VALID_SAVINGS_CMD);

        commandProcessor.processor(VALID_DEPOSIT_CHECKING_CMD);
        commandProcessor.processor(VALID_DEPOSIT_SAVINGS_CMD);

        Assertions.assertEquals(500, bank.getAccounts().get("33225566").getMoney());
        Assertions.assertEquals(500, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void process_withdraw_from_checking_command() {
        commandProcessor.processor(VALID_CHECKING_CMD);

        commandProcessor.processor(VALID_DEPOSIT_CHECKING_CMD);
        commandProcessor.processor("withdraw 12345678 300");
        Assertions.assertEquals(200, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void process_withdraw_from_savings_command() {
        commandProcessor.processor(VALID_SAVINGS_CMD);

        commandProcessor.processor(VALID_DEPOSIT_SAVINGS_CMD);
        commandProcessor.processor("withdraw 33225566 300");
        Assertions.assertEquals(200, bank.getAccounts().get("33225566").getMoney());
    }

    @Test
    void process_withdraw_from_cd_command() {
        commandProcessor.processor(VALID_CD_CMD);
        commandProcessor.processor("pass 12");
        commandProcessor.processor("withdraw 87654321 2000");
        Assertions.assertEquals(0, bank.getAccounts().get("87654321").getMoney());
    }
}
