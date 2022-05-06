import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountCreationCommandValidationTest {
    public static final String QUICK_ID = ("12345678");
    public static final double APR = (0.06);
    public static final double MONEY = (2000);

    Bank bank;
    Account checking;
    Account savings;
    Account cd;
    CreateCommandValidator commandValidator;

    @BeforeEach
    void set_up() {
        bank = new Bank();
        checking = new Checking(APR, 0);
        savings = new Savings(APR, 0);
        cd = new CD(APR, MONEY);

        commandValidator = new CreateCommandValidator(bank);
    }

    @Test
    void empty_id_input() {
        boolean actual = commandValidator.validateID("");
        assertFalse(actual);
    }

    @Test
    void valid_eight_number_id_input() {
        boolean actual = commandValidator.validateID(QUICK_ID);
        assertTrue(actual);
    }

    @Test
    void invalid_nine_number_id_input() {
        boolean actual = commandValidator.validateID("123456789");
        assertFalse(actual);
    }

    @Test
    void invalid_seven_number_id_input() {
        boolean actual = commandValidator.validateID("1234567");
        assertFalse(actual);
    }
}
