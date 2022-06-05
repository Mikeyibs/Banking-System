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
    CommandStorage commands;

    @BeforeEach
    void setUp() {
        bank = new Bank(commands);
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
        Assertions.assertEquals(4.8052835215844425, bank.getAccounts().get("87654321").getMoney());
    }

    @Test
    void valid_process_withdraw_from_checking_account_twice() {
        commandProcessor.processor("create checking 12345678 1.0");
        commandProcessor.processor("deposit 12345678 1000");
        commandProcessor.processor("withdraw 12345678 100");
        commandProcessor.processor("withdraw 12345678 200");

        Assertions.assertEquals(700, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void valid_process_withdraw_from_savings_account_twice() {
        commandProcessor.processor("create savings 12345678 1.0");
        commandProcessor.processor("deposit 12345678 1000");
        commandProcessor.processor("withdraw 12345678 100");
        commandProcessor.processor("pass 1");
        commandProcessor.processor("withdraw 12345678 200");

        Assertions.assertEquals(700.75, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void valid_process_transfer_between_checking_and_checking_accounts() {
        commandProcessor.processor("create checking 12345678 1.0");
        commandProcessor.processor("deposit 12345678 1000");
        commandProcessor.processor("create checking 22334455 1.0");
        commandProcessor.processor("deposit 22334455 500");

        commandProcessor.processor("transfer 12345678 22334455 200");
        Assertions.assertEquals(700, bank.getAccounts().get("22334455").getMoney());
        Assertions.assertEquals(800, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void valid_process_transfer_between_checking_and_savings_accounts() {
        commandProcessor.processor("create checking 12345678 1.0");
        commandProcessor.processor("deposit 12345678 1000");
        commandProcessor.processor("create savings 22334455 1.0");
        commandProcessor.processor("deposit 22334455 500");

        commandProcessor.processor("transfer 12345678 22334455 200");
        Assertions.assertEquals(700, bank.getAccounts().get("22334455").getMoney());
        Assertions.assertEquals(800, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void valid_pass_time_command() {
        commandProcessor.processor("create savings 12345678 1.0");
        commandProcessor.processor("deposit 12345678 1000");
        commandProcessor.processor("pass 2");

        Assertions.assertEquals(1001.6673611111112, bank.getAccounts().get("12345678").getMoney());
        Assertions.assertEquals(2, bank.getAccounts().get("12345678").getMonth());
        Assertions.assertEquals(true, bank.getAccounts().get("12345678").getWithdrawRestriction());
    }

    @Test
    void correct_restriction_after_withdraw_savings() {
        commandProcessor.processor(VALID_SAVINGS_CMD);

        commandProcessor.processor(VALID_DEPOSIT_SAVINGS_CMD);
        commandProcessor.processor("withdraw 33225566 300");
        Assertions.assertEquals(200, bank.getAccounts().get("33225566").getMoney());
        Assertions.assertEquals(false, bank.getAccounts().get("33225566").getWithdrawRestriction());
    }
}
