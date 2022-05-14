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
    void process_valid_deposit_into_checking() {
        commandProcessor.processor(VALID_CHECKING_CMD);

        commandProcessor.processor(VALID_DEPOSIT_CHECKING_CMD);

        Assertions.assertEquals(500, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void process_valid_deposit_into_savings() {
        commandProcessor.processor(VALID_SAVINGS_CMD);

        commandProcessor.processor(VALID_DEPOSIT_SAVINGS_CMD);

        Assertions.assertEquals(500, bank.getAccounts().get("33225566").getMoney());
    }

    @Test
    void parse_string_returns_proper_string() {
        String test = commandProcessor.parseString(VALID_CHECKING_CMD, 0);
        Assertions.assertEquals(test, "create");
    }

    @Test
    void get_type_function_returns_proper_type() {
        String test = commandProcessor.getType(VALID_CHECKING_CMD);
        Assertions.assertEquals(test, "checking");
    }

    @Test
    void get_id_function_returns_proper_id() {
        String test = commandProcessor.getID(VALID_CHECKING_CMD);
        Assertions.assertEquals(test, "12345678");
    }

    @Test
    void get_apr_function_returns_proper_apr() {
        Double test = commandProcessor.getAPR(VALID_CHECKING_CMD);
        Assertions.assertEquals(test, 0.06);
    }

    @Test
    void get_amount_function_returns_proper_amount() {
        Double test = commandProcessor.getAmount(VALID_CD_CMD);
        Assertions.assertEquals(test, 2000);
    }
}
