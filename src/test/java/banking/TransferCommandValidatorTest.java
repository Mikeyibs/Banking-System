package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferCommandValidatorTest {
    public static final String QUICK_ID = ("12345678");
    public static final double APR = (0.06);
    public static final double MONEY = (2000);
    public static final String COMMAND = ("transfer 12345678 22334455 200");

    Bank bank;
    Account checking;
    Account savings;
    Account cd;
    TransferCommandValidator commandValidator;
    CommandValidator validator;
    CommandProcessor processor;
    CommandStorage commands;

    @BeforeEach
    void setUp() {
        bank = new Bank(commands);
        checking = new Checking(APR, 0);
        savings = new Savings(APR, 0);
        cd = new CD(APR, MONEY);

        commandValidator = new TransferCommandValidator(bank);
        validator = new CommandValidator(bank);
        processor = new CommandProcessor(bank);
    }

    @Test
    void valid_account_exists_in_bank() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateAccountExists(QUICK_ID);
        Assertions.assertTrue(transfer);
    }

    @Test
    void invalid_account_does_not_exist_in_bank() {
        boolean transfer = commandValidator.validateAccountExists(QUICK_ID);
        Assertions.assertFalse(transfer);
    }

    @Test
    void valid_transfer_length() {
        boolean transfer = commandValidator.validateTransferLength(COMMAND);
        Assertions.assertTrue(transfer);
    }

    @Test
    void invalid_transfer_length() {
        boolean transfer = commandValidator.validateTransferLength("transfer 12345678 150");
        Assertions.assertFalse(transfer);
    }

    @Test
    void invalid_transfer_length_three_IDs() {
        boolean transfer = commandValidator.validateTransferLength("transfer 12345678 22334455 11223344 150");
        Assertions.assertFalse(transfer);
    }

    @Test
    void valid_withdraw_amount_from_checking_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateWithdrawAmount("12345678", "200");
        Assertions.assertTrue(transfer);
    }

    @Test
    void valid_withdraw_zero_amount_from_checking_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateWithdrawAmount("12345678", "0");
        Assertions.assertTrue(transfer);
    }

    @Test
    void valid_withdraw_max_amount_from_checking_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateWithdrawAmount("12345678", "400");
        Assertions.assertTrue(transfer);
    }

    @Test
    void invalid_withdraw_more_then_max_amount_from_checking_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateWithdrawAmount("12345678", "1200");
        Assertions.assertFalse(transfer);
    }

    @Test
    void invalid_withdraw_special_characters_from_checking_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateWithdrawAmount("12345678", "4@@");
        Assertions.assertFalse(transfer);
    }

    @Test
    void invalid_withdraw_negative_amount_from_checking_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateWithdrawAmount("12345678", "-200");
        Assertions.assertFalse(transfer);
    }

    @Test
    void valid_withdraw_amount_from_savings_account() {
        processor.processor("create savings 12345678 1.0");

        boolean transfer = commandValidator.validateWithdrawAmount("12345678", "500");
    }

    @Test
    void valid_withdraw_zero_amount_from_savings_account() {
        processor.processor("create savings 12345678 1.0");

        boolean transfer = commandValidator.validateWithdrawAmount("12345678", "0");
        Assertions.assertTrue(transfer);
    }

    @Test
    void valid_withdraw_max_amount_from_savings_account() {
        processor.processor("create savings 12345678 1.0");

        boolean transfer = commandValidator.validateWithdrawAmount("12345678", "1000");
        Assertions.assertTrue(transfer);
    }

    @Test
    void invalid_withdraw_more_then_max_amount_from_savings_account() {
        processor.processor("create savings 12345678 1.0");

        boolean transfer = commandValidator.validateWithdrawAmount("12345678", "1200");
        Assertions.assertFalse(transfer);
    }

    @Test
    void invalid_withdraw_special_characters_from_savings_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateWithdrawAmount("12345678", "4@@");
        Assertions.assertFalse(transfer);
    }

    @Test
    void invalid_withdraw_negative_amount_from_savings_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateWithdrawAmount("12345678", "-200");
        Assertions.assertFalse(transfer);
    }

    @Test
    void valid_deposit_amount_checking_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateDepositAmount("12345678", "700");
        Assertions.assertTrue(transfer);
    }

    @Test
    void valid_deposit_zero_amount_checking_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateDepositAmount("12345678", "0");
        Assertions.assertTrue(transfer);
    }

    @Test
    void valid_deposit_max_amount_checking_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateDepositAmount("12345678", "1000");
        Assertions.assertTrue(transfer);
    }

    @Test
    void invalid_deposit_negative_amount_checking_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateDepositAmount("12345678", "-100");
        Assertions.assertFalse(transfer);
    }

    @Test
    void invalid_deposit_greater_then_max_amount_checking_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateDepositAmount("12345678", "1400");
        Assertions.assertFalse(transfer);
    }

    @Test
    void invalid_attempt_to_deposit_special_characters_checking_account() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validateDepositAmount("12345678", "4@@");
        Assertions.assertFalse(transfer);
    }

    @Test
    void valid_deposit_amount_savings_account() {
        processor.processor("create savings 12345678 1.0");

        boolean transfer = commandValidator.validateDepositAmount("12345678", "700");
        Assertions.assertTrue(transfer);
    }

    @Test
    void valid_deposit_zero_amount_savings_account() {
        processor.processor("create savings 12345678 1.0");

        boolean transfer = commandValidator.validateDepositAmount("12345678", "0");
        Assertions.assertTrue(transfer);
    }

    @Test
    void valid_deposit_max_amount_savings_account() {
        processor.processor("create savings 12345678 1.0");

        boolean transfer = commandValidator.validateDepositAmount("12345678", "2400");
        Assertions.assertTrue(transfer);
    }

    @Test
    void invalid_deposit_greater_then_max_amount_savings_account() {
        processor.processor("create savings 12345678 1.0");

        boolean transfer = commandValidator.validateDepositAmount("12345678", "4400");
        Assertions.assertFalse(transfer);
    }

    @Test
    void invalid_deposit_negative_amount_savings_account() {
        processor.processor("create savings 12345678 1.0");

        boolean transfer = commandValidator.validateDepositAmount("12345678", "-100");
        Assertions.assertFalse(transfer);
    }

    @Test
    void invalid_attempt_to_deposit_special_characters_savings_account() {
        processor.processor("create savings 12345678 1.0");

        boolean transfer = commandValidator.validateDepositAmount("12345678", "4@@");
        Assertions.assertFalse(transfer);
    }

    @Test
    void valid_transfer_command_from_checking_account_to_checking_account() {
        processor.processor("create checking 12345678 1.0");
        processor.processor("create checking 22334455 1.0");
        processor.processor("deposit 12345678 700");
        processor.processor("deposit 22334455 500");
        boolean transfer = commandValidator.validate(COMMAND);

        Assertions.assertTrue(transfer);
    }

    @Test
    void valid_transfer_command_from_checking_account_to_savings_account() {
        processor.processor("create checking 12345678 1.0");
        processor.processor("create savings 22334455 1.0");
        processor.processor("deposit 12345678 700");
        processor.processor("deposit 22334455 500");
        boolean transfer = commandValidator.validate(COMMAND);

        Assertions.assertTrue(transfer);
    }

    @Test
    void valid_transfer_command_from_savings_account_to_savings_account() {
        processor.processor("create savings 12345678 1.0");
        processor.processor("create savings 22334455 1.0");
        processor.processor("deposit 12345678 700");
        processor.processor("deposit 22334455 500");
        boolean transfer = commandValidator.validate(COMMAND);

        Assertions.assertTrue(transfer);
    }

    @Test
    void valid_transfer_command_from_savings_account_to_checking_account() {
        processor.processor("create savings 12345678 1.0");
        processor.processor("create checking 22334455 1.0");
        processor.processor("deposit 12345678 700");
        processor.processor("deposit 22334455 500");
        boolean transfer = commandValidator.validate(COMMAND);

        Assertions.assertTrue(transfer);
    }

    @Test
    void invalid_transfer_from_account_that_does_not_exist() {
        processor.processor("create checking 12345678 1.0");

        boolean transfer = commandValidator.validate("transfer 22334455 12345678 300");
        Assertions.assertFalse(transfer);
    }

    @Test
    void invalid_transfer_from_checking_account_to_account_that_does_not_exist() {
        processor.processor("create checking 12345678 1.0");
        processor.processor("deposit 12345678 500");

        boolean transfer = commandValidator.validate(COMMAND);
        Assertions.assertFalse(transfer);
    }

    @Test
    void invalid_transfer_from_cd_to_checking_account() {
        processor.processor("create cd 12345678 1.0 1500");
        processor.processor("create checking 22334455 1.0");

        boolean transfer = commandValidator.validate("transfer 12345678 22334455 400");
        Assertions.assertFalse(transfer);
    }

    @Test
    void invalid_transfer_command_with_too_many_commands() {
        processor.processor("create checking 12345678 1.0");
        processor.processor("deposit 12345678 200");
        processor.processor("create checking 22445566 1.0");

        boolean transfer = commandValidator.validate("transfer 12345678 22445566 200 300");
        Assertions.assertFalse(transfer);
    }

    @Test
    void account_can_withdraw_this_month_method_successfully_returns_false() {
        processor.processor("create savings 12345678 1.0");
        processor.processor("deposit 12345678 100");
        processor.processor("withdraw 12345678 50");

        boolean transfer = commandValidator.accountCanWithdrawThisMonth("12345678");
        Assertions.assertFalse(transfer);
    }
}
