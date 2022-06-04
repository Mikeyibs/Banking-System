package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidationTest {
    public static final String QUICK_ID = ("12345678");
    public static final double APR = (0.06);
    public static final double MONEY = (2000);

    Bank bank;
    Account checking;
    Account savings;
    Account cd;
    CommandValidator commandValidator;
    CommandStorage commands;

    @BeforeEach
    void set_up() {
        bank = new Bank(commands);
        checking = new Checking(APR, 0);
        savings = new Savings(APR, 0);
        cd = new CD(APR, MONEY);
        commandValidator = new CommandValidator(bank);
    }

    @Test
    void valid_create_checking_command() {
        boolean actual = commandValidator.validate("create checking 12345678 0.06");
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_create_savings_command() {
        boolean actual = commandValidator.validate("create savings 12345678 1.0");
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_create_cd_command() {
        boolean actual = commandValidator.validate("create cd 12345678 1.0 1200");
        Assertions.assertTrue(actual);
    }

    @Test
    void invalid_create_checking_command() {
        boolean actual = commandValidator.validate("create checks 12345678 1000");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_create_checking_command_with_special_characters() {
        boolean actual = commandValidator.validate("create check!ng 123456!! !000");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_create_checking_command_with_extra_commands() {
        boolean actual = commandValidator.validate("create checking 12345678 0.06 1000");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_create_checking_command_without_apr() {
        boolean actual = commandValidator.validate("create checking 12345678");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_create_savings_command() {
        boolean actual = commandValidator.validate("create savs 12345678 1000");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_create_savings_command_with_special_characters() {
        boolean actual = commandValidator.validate("create sav!ngs 123456!! !000");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_create_savings_command_with_extra_commands() {
        boolean actual = commandValidator.validate("create savings 12345678 0.06 1000");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_create_savings_command_without_apr() {
        boolean actual = commandValidator.validate("create savings 12345678");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_create_CD_command_without_money() {
        boolean actual = commandValidator.validate("create cd 12345678 1.0");
        Assertions.assertFalse(actual);
    }

    @Test
    void valid_deposit_into_checking_command() {
        bank.addAccount(QUICK_ID, checking);

        boolean actual = commandValidator.validate("deposit 12345678 500");
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_deposit_into_savings_command() {
        bank.addAccount(QUICK_ID, savings);

        boolean actual = commandValidator.validate("deposit 12345678 500");
        Assertions.assertTrue(actual);
    }

    @Test
    void invalid_deposit_into_cd_command() {
        bank.addAccount(QUICK_ID, cd);

        boolean actual = commandValidator.validate("deposit 12345678 500");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_deposit_with_special_characters_command() {
        bank.addAccount(QUICK_ID, checking);

        boolean actual = commandValidator.validate("depos!t !2345678 500");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_deposit_with_negative_amount_command() {
        bank.addAccount(QUICK_ID, checking);

        boolean actual = commandValidator.validate("deposit 12345678 -500");
        Assertions.assertFalse(actual);
    }

    @Test
    void valid_withdraw_from_checking_account_command() {
        bank.addAccount(QUICK_ID, checking);
        bank.deposit(QUICK_ID, 200);

        boolean actual = commandValidator.validate("withdraw 12345678 100");
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_withdraw_from_savings_account_command() {
        bank.addAccount(QUICK_ID, savings);
        bank.deposit(QUICK_ID, 200);

        boolean actual = commandValidator.validate("withdraw 12345678 100");
        Assertions.assertTrue(actual);
    }

    @Test
    void invalid_two_withdraws_from_savings_account_command() {
        bank.addAccount(QUICK_ID, savings);
        bank.deposit(QUICK_ID, 200);
        bank.withdraw(QUICK_ID, 100);

        boolean actual = commandValidator.validate("withdraw 12345678 100");
        Assertions.assertFalse(actual);
    }

    @Test
    void valid_withdraw_from_cd_account_command() {
        bank.addAccount(QUICK_ID, cd);
        bank.passTime(13);

        boolean actual = commandValidator.validate("withdraw 12345678 2000");
        Assertions.assertTrue(actual);
    }

    @Test
    void invalid_withdraw_from_cd_account_command() {
        bank.addAccount(QUICK_ID, cd);

        boolean actual = commandValidator.validate("withdraw 12345678 100");
        Assertions.assertFalse(actual);
    }

    @Test
    void valid_transfer_between_checking_account_and_savings_account() {
        bank.addAccount(QUICK_ID, checking);
        bank.addAccount("22334455", savings);
        bank.deposit(QUICK_ID, 400);

        boolean actual = commandValidator.validate("transfer 12345678 22334455 200");
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_transfer_between_savings_account_and_checking_account() {
        bank.addAccount(QUICK_ID, checking);
        bank.addAccount("22334455", savings);
        bank.deposit("22334455", 400);

        boolean actual = commandValidator.validate("transfer 22334455 12345678 200");
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_transfer_twice_between_savings_account_and_checking_account() {
        bank.addAccount(QUICK_ID, checking);
        bank.addAccount("22334455", savings);
        bank.deposit("22334455", 400);
        bank.deposit(QUICK_ID, 400);

        boolean actual = commandValidator.validate("transfer 22334455 12345678 200");
        Assertions.assertTrue(actual);
        bank.passTime(1);
        boolean test = commandValidator.validate("transfer 22334455 12345678 100");
        Assertions.assertTrue(test);
    }

    @Test
    void invalid_transfer_twice_between_savings_account_and_checking_account() {
        bank.addAccount(QUICK_ID, checking);
        bank.addAccount("22334455", savings);
        bank.deposit("22334455", 400);
        bank.deposit(QUICK_ID, 400);

        boolean actual = commandValidator.validate("transfer 22334455 12345678 200");
        Assertions.assertTrue(actual);
        boolean test = commandValidator.validate("transfer 22334455 12345678 100");
        Assertions.assertFalse(test);
    }
}
