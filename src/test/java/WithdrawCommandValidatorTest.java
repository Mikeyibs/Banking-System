import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawCommandValidatorTest {
    public static final String QUICK_ID = ("12345678");
    public static final double APR = (0.06);
    public static final double MONEY = (2000);
    public static final String COMMAND = ("withdraw 12345678 250");

    Bank bank;
    Account checking;
    Account savings;
    Account cd;
    WithdrawCommandValidator commandValidator;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        checking = new Checking(APR, 0);
        savings = new Savings(APR, 0);
        cd = new CD(APR, MONEY);

        commandValidator = new WithdrawCommandValidator(bank);
    }

    @Test
    void valid_max_withdraw_amount_from_checking_account() {
        bank.addAccount(QUICK_ID, checking);

        boolean actual = commandValidator.validateWithdrawAmount("400", QUICK_ID);
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_max_withdraw_amount_from_savings_account() {
        bank.addAccount(QUICK_ID, savings);

        boolean actual = commandValidator.validateWithdrawAmount("1000", QUICK_ID);
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_min_withdraw_amount_from_checking_account() {
        bank.addAccount(QUICK_ID, checking);

        boolean actual = commandValidator.validateWithdrawAmount("0", QUICK_ID);
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_min_withdraw_amount_from_savings_account() {
        bank.addAccount(QUICK_ID, savings);

        boolean actual = commandValidator.validateWithdrawAmount("0", QUICK_ID);
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_withdraw_amount_with_decimals_from_checking_account() {
        bank.addAccount(QUICK_ID, checking);

        boolean actual = commandValidator.validateWithdrawAmount("350.50", QUICK_ID);
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_withdraw_amount_with_decimals_from_savings_account() {
        bank.addAccount(QUICK_ID, savings);

        boolean actual = commandValidator.validateWithdrawAmount("500.50", QUICK_ID);
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_withdraw_amount_from_checking_account() {
        bank.addAccount(QUICK_ID, checking);

        boolean actual = commandValidator.validateWithdrawAmount("150", QUICK_ID);
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_withdraw_amount_from_savings_account() {
        bank.addAccount(QUICK_ID, savings);

        boolean actual = commandValidator.validateWithdrawAmount("450", QUICK_ID);
        Assertions.assertTrue(actual);
    }

    @Test
    void invalid_negative_withdraw_amount_from_checking_account() {
        bank.addAccount(QUICK_ID, checking);

        boolean actual = commandValidator.validateWithdrawAmount("-50", QUICK_ID);
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_negative_withdraw_amount_from_savings_account() {
        bank.addAccount(QUICK_ID, savings);

        boolean actual = commandValidator.validateWithdrawAmount("-50", QUICK_ID);
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_greater_then_max_withdraw_amount_from_checking_account() {
        bank.addAccount(QUICK_ID, checking);

        boolean actual = commandValidator.validateWithdrawAmount("600", QUICK_ID);
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_greater_then_max_withdraw_amount_from_savings_account() {
        bank.addAccount(QUICK_ID, savings);

        boolean actual = commandValidator.validateWithdrawAmount("1150", QUICK_ID);
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_withdraw_using_special_characters_in_amount_from_checking() {
        bank.addAccount(QUICK_ID, checking);

        boolean actual = commandValidator.validateWithdrawAmount("!235", QUICK_ID);
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_withdraw_using_special_characters_in_amount_from_savings() {
        bank.addAccount(QUICK_ID, savings);

        boolean actual = commandValidator.validateWithdrawAmount("!562", QUICK_ID);
        Assertions.assertFalse(actual);
    }

    @Test
    void valid_account_exists() {
        bank.addAccount(QUICK_ID, checking);

        boolean actual = commandValidator.validateAccountExists(QUICK_ID);
        Assertions.assertTrue(actual);
    }

    @Test
    void invalid_account_doesnt_exist() {
        boolean actual = commandValidator.validateAccountExists(QUICK_ID);
        Assertions.assertFalse(actual);
    }

    @Test
    void valid_withdraw_command_length() {
        boolean actual = commandValidator.validateWithdrawLength(COMMAND);
        Assertions.assertTrue(actual);
    }

    @Test
    void invalid_withdraw_command_length() {
        boolean actual = commandValidator.validateWithdrawLength("withdraw 12345678 500 yo");
        Assertions.assertFalse(actual);
    }

    @Test
    void valid_withdraw_from_checking_account() {
        bank.addAccount(QUICK_ID, checking);

        boolean actual = commandValidator.validate(COMMAND);
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_withdraw_from_savings_account() {
        bank.addAccount(QUICK_ID, savings);

        boolean actual = commandValidator.validate(COMMAND);
        Assertions.assertTrue(actual);
    }

    @Test
    void invalid_withdraw_from_checking_account() {
        bank.addAccount(QUICK_ID, checking);

        boolean actual = commandValidator.validate("withdraw 12345678 500");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_withdraw_from_savings_account() {
        bank.addAccount(QUICK_ID, savings);

        boolean actual = commandValidator.validate("withdraw 12345678 -258");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_withdraw_without_a_valid_ID() {
        boolean actual = commandValidator.validate("withdraw 12345678 400");
        Assertions.assertFalse(actual);
    }
}
