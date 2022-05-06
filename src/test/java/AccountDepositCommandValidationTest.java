import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountDepositCommandValidationTest {
    public static final String QUICK_ID = ("12345678");
    public static final double APR = (0.06);
    public static final double MONEY = (2000);

    Bank bank;
    Account checking;
    Account savings;
    Account cd;
    DepositCommandValidator commandValidator;


    @BeforeEach
    void set_up() {
        bank = new Bank();
        checking = new Checking(APR, 0);
        savings = new Savings(APR, 0);
        cd = new CD(APR, MONEY);

        commandValidator = new DepositCommandValidator(bank);
    }

    @Test
    void valid_deposit_command() {
        boolean actual = commandValidator.validateDepositCommand("deposit 12345678 500");
        assertTrue(actual);
    }

    @Test
    void invalid_deposit_command_with_out_stating_deposit() {
        boolean actual = commandValidator.validateDepositCommand("12345678 500");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_with_two_numbers() {
        boolean actual = commandValidator.validateDepositCommand("deposit 12345678 500 70");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_with_special_characters() {
        boolean actual = commandValidator.validateDepositCommand("deposit 12345678 5@#");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_with_letters() {
        boolean actual = commandValidator.validateDepositCommand("deposit 12345678 x0x");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_with_a_negative_number() {
        boolean actual = commandValidator.validateDepositCommand("deposit 12345678 -200");
        assertFalse(actual);
    }

    @Test
    void valid_deposit_amount_checking() {
        boolean actual = commandValidator.validateDepositCheckingAmount("650");
        assertTrue(actual);
    }

    @Test
    void valid_deposit_amount_checking_one_dollar() {
        boolean actual = commandValidator.validateDepositCheckingAmount("1");
        assertTrue(actual);
    }

    @Test
    void valid_deposit_amount_checking_maximum() {
        boolean actual = commandValidator.validateDepositCheckingAmount("1000");
        assertTrue(actual);
    }

    @Test
    void valid_deposit_amount_checking_minimum() {
        boolean actual = commandValidator.validateDepositCheckingAmount("0");
        assertTrue(actual);
    }

    @Test
    void valid_deposit_amount_checking_decimal_amount() {
        boolean actual = commandValidator.validateDepositCheckingAmount("100.50");
        assertTrue(actual);
    }

    @Test
    void invalid_deposit_amount_checking_negative_amount() {
        boolean actual = commandValidator.validateDepositCheckingAmount("-1");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_amount_checking_above_maximum() {
        boolean actual = commandValidator.validateDepositCheckingAmount("1020");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_amount_checking_letters() {
        boolean actual = commandValidator.validateDepositCheckingAmount("1x0x");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_amount_checking_special_characters() {
        boolean actual = commandValidator.validateDepositCheckingAmount("1#!2");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_amount_checking_double_decimal() {
        boolean actual = commandValidator.validateDepositCheckingAmount("100.50.50");
        assertFalse(actual);
    }
}
