import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommadValidationTest {
    public static final String QUICK_ID = ("12345678");
    public static final double APR = (0.06);
    public static final double MONEY = (2000);

    Bank bank;
    Account checking;
    Account savings;
    Account cd;
    CommandValidator commandValidator;

    @BeforeEach
    void set_up() {
        bank = new Bank();
        checking = new Checking(APR, 0);
        savings = new Savings(APR, 0);
        cd = new CD(APR, MONEY);
        commandValidator = new CommandValidator(bank);
    }

    @Test
    void valid_create_checking_command() {
        boolean actual = commandValidator.validate("create checking 12345678 0.06 ");
        Assertions.assertTrue(actual);
    }
}
