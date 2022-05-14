// Name: Michael Ibrahim | ID: mi374 | Section: 001

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
        boolean deposit = commandValidator.validateDepositCommand("deposit 12345678 500");
        assertTrue(deposit);
    }

    @Test
    void invalid_deposit_command_with_out_stating_deposit() {
        boolean deposit = commandValidator.validateDepositCommand("12345678 500");
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_command_with_two_numbers() {
        boolean deposit = commandValidator.validateDepositCommand("deposit 12345678 500 70");
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_command_with_special_characters() {
        boolean deposit = commandValidator.validateDepositCommand("deposit 12345678 5@#");
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_command_with_letters() {
        boolean deposit = commandValidator.validateDepositCommand("deposit 12345678 x0x");
        assertFalse(deposit);
    }

    @Test
    void invalid_deposit_command_with_a_negative_number() {
        boolean deposit = commandValidator.validateDepositCommand("deposit 12345678 -200");
        assertFalse(deposit);
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
}
