// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void valid_account_exists() {
        bank.addAccount(QUICK_ID, checking);

        boolean deposit = commandValidator.validateAccountExists(QUICK_ID);
        Assertions.assertTrue(deposit);
    }

    @Test
    void invalid_account_doesnt_exist() {
        bank.addAccount(QUICK_ID, checking);

        boolean deposit = commandValidator.validateAccountExists(QUICK_ID);
        Assertions.assertTrue(deposit);
    }

    @Test
    void valid_deposit_amount_checking() {
        bank.addAccount(QUICK_ID, checking);

        boolean deposit = commandValidator.validateDepositAmount("650", QUICK_ID);
        assertTrue(deposit);
    }

    @Test
    void valid_deposit_amount_checking_one_dollar() {
        bank.addAccount(QUICK_ID, checking);

        boolean deposit = commandValidator.validateDepositAmount("1", QUICK_ID);
        assertTrue(deposit);
    }

    @Test
    void valid_deposit_amount_checking_maximum() {
        bank.addAccount(QUICK_ID, checking);

        boolean deposit = commandValidator.validateDepositAmount("1000", QUICK_ID);
        assertTrue(deposit);
    }

    @Test
    void valid_deposit_amount_checking_minimum() {
        bank.addAccount(QUICK_ID, checking);

        boolean deposit = commandValidator.validateDepositAmount("0", QUICK_ID);
        assertTrue(deposit);
    }

    @Test
    void valid_deposit_amount_checking_decimal_amount() {
        bank.addAccount(QUICK_ID, checking);

        boolean deposit = commandValidator.validateDepositAmount("100.50", QUICK_ID);
        assertTrue(deposit);
    }

    @Test
    void invalid_deposit_amount_checking_negative_amount() {
        bank.addAccount(QUICK_ID, checking);

        boolean deposit = commandValidator.validateDepositAmount("-1", QUICK_ID);
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_amount_checking_above_maximum() {
        bank.addAccount(QUICK_ID, checking);

        boolean deposit = commandValidator.validateDepositAmount("1020", QUICK_ID);
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_amount_checking_letters() {
        bank.addAccount(QUICK_ID, checking);

        boolean deposit = commandValidator.validateDepositAmount("1x0x", QUICK_ID);
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_amount_checking_special_characters() {
        bank.addAccount(QUICK_ID, checking);

        boolean deposit = commandValidator.validateDepositAmount("1#!2", QUICK_ID);
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_amount_checking_double_decimal() {
        bank.addAccount(QUICK_ID, checking);

        boolean deposit = commandValidator.validateDepositAmount("100.50.50", QUICK_ID);
        assertFalse(deposit);
    }

    @Test
    void valid_deposit_amount_savings() {
        bank.addAccount(QUICK_ID, savings);

        boolean deposit = commandValidator.validateDepositAmount("650", QUICK_ID);
        assertTrue(deposit);
    }

    @Test
    void valid_deposit_amount_savings_one_dollar() {
        bank.addAccount(QUICK_ID, savings);

        boolean deposit = commandValidator.validateDepositAmount("1", QUICK_ID);
        assertTrue(deposit);
    }

    @Test
    void valid_deposit_amount_savings_maximum() {
        bank.addAccount(QUICK_ID, savings);

        boolean deposit = commandValidator.validateDepositAmount("2500", QUICK_ID);
        assertTrue(deposit);
    }

    @Test
    void valid_deposit_amount_savings_minimum() {
        bank.addAccount(QUICK_ID, savings);

        boolean deposit = commandValidator.validateDepositAmount("0", QUICK_ID);
        assertTrue(deposit);
    }

    @Test
    void valid_deposit_amount_savings_decimal_amount() {
        bank.addAccount(QUICK_ID, savings);

        boolean deposit = commandValidator.validateDepositAmount("100.50", QUICK_ID);
        assertTrue(deposit);
    }

    @Test
    void invalid_deposit_amount_savings_negative_amount() {
        bank.addAccount(QUICK_ID, savings);

        boolean deposit = commandValidator.validateDepositAmount("-1", QUICK_ID);
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_amount_savings_above_maximum() {
        bank.addAccount(QUICK_ID, savings);

        boolean deposit = commandValidator.validateDepositAmount("3600", QUICK_ID);
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_amount_savings_letters() {
        bank.addAccount(QUICK_ID, savings);

        boolean deposit = commandValidator.validateDepositAmount("1x0x", QUICK_ID);
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_amount_savings_special_characters() {
        bank.addAccount(QUICK_ID, savings);

        boolean deposit = commandValidator.validateDepositAmount("1#!2", QUICK_ID);
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_amount_savings_double_decimal() {
        bank.addAccount(QUICK_ID, savings);

        boolean deposit = commandValidator.validateDepositAmount("100.50.50", QUICK_ID);
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_length_more_then_required() {
        String command = ("deposit 12345678 1000 230");
        boolean deposit = commandValidator.validateDepositLength(command);
        assertFalse(deposit);
    }

    @Test
    void valid_deposit_length() {
        String command = ("deposit 12345678 100");
        boolean deposit = commandValidator.validateDepositLength(command);
        assertTrue(deposit);
    }

    @Test
    void invalid_deposit_length_less_then_required() {
        String command = ("deposit 12345678");
        boolean deposit = commandValidator.validateDepositLength(command);
        assertFalse(deposit);
    }

    @Test
    void valid_deposit_into_checking_account() {
        String command = ("deposit 12345678 750");
        bank.addAccount(QUICK_ID, checking);

        boolean deposit = commandValidator.validate(command);
        assertTrue(deposit);
    }

    @Test
    void valid_deposit_into_savings_account() {
        String command = ("deposit 12345678 2200");
        bank.addAccount(QUICK_ID, savings);

        boolean deposit = commandValidator.validate(command);
        assertTrue(deposit);
    }

    @Test
    void invalid_deposit_into_account_that_does_not_exist() {
        String command = ("deposit 12345678 2200");

        boolean deposit = commandValidator.validate(command);
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_into_CD_account() {
        String command = ("deposit 12345678 2200");
        bank.addAccount(QUICK_ID, cd);

        boolean deposit = commandValidator.validate(command);
        assertFalse(deposit);
    }

    @Test
    void valid_get_ID_command() {
        String command = ("deposit 12345678 750");

        String deposit = commandValidator.getID(command);
        assertEquals(QUICK_ID, deposit);
    }

    @Test
    void invalid_get_ID_command() {
        String command = ("12345678 750");

        String deposit = commandValidator.getID(command);
        assertEquals("750", deposit);
    }

    @Test
    void valid_get_amount_command() {
        String command = ("deposit 12345678 750");

        String deposit = commandValidator.getAmount(command);
        assertEquals("750", deposit);
    }
}
