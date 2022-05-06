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
    void valid_create_checking_command() {
        boolean actual = commandValidator.validate("create checking 12345678 0.06");
        assertTrue(actual);
    }

    @Test
    void invalid_create_checking_command_with_no_create() {
        boolean actual = commandValidator.validate("checking 12345678 0.06");
        assertFalse(actual);
    }

    @Test
    void invalid_create_checking_command_with_money_at_end() {
        boolean actual = commandValidator.validate("create checking 12345678 0.06 1500");
        assertFalse(actual);
    }

    @Test
    void invalid_create_checking_command_with_misspelled_word() {
        boolean actual = commandValidator.validate("create cheking 12345678 0.06");
        assertFalse(actual);
    }

    @Test
    void valid_create_savings_command() {
        boolean actual = commandValidator.validate("craete savings 12345678 0.06");
        assertTrue(actual);
    }

    @Test
    void invalid_create_savings_command_with_no_create() {
        boolean actual = commandValidator.validate("savings 12345678 0.06");
        assertFalse(actual);
    }

    @Test
    void invalid_create_savings_command_with_money_at_end() {
        boolean actual = commandValidator.validate("create savings 12345678 0.06 1500");
        assertFalse(actual);
    }

    @Test
    void invalid_create_savings_command_with_misspelled_word() {
        boolean actual = commandValidator.validate("create savings 12345678 0.06");
        assertFalse(actual);
    }

    @Test
    void valid_create_cd_command() {
        boolean actual = commandValidator.validate("create cd 12345678 0.06 2000");
        assertTrue(actual);
    }

    @Test
    void invalid_create_cd_command_with_no_create() {
        boolean actual = commandValidator.validate("cd 12345678 0.06");
        assertFalse(actual);
    }

    @Test
    void invalid_create_cd_command_with_misspelled_word() {
        boolean actual = commandValidator.validate("created cd 12345678 0.06");
        assertFalse(actual);
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

    @Test
    void invalid_with_single_single_letter_id_input() {
        boolean actual = commandValidator.validateID("X1234567");
        assertFalse(actual);
    }

    @Test
    void invalid_with_single_special_character_id_input() {
        boolean actual = commandValidator.validateID("123457#");
        assertFalse(actual);
    }

    @Test
    void empty_apr_input() {
        boolean actual = commandValidator.validateAPR("");
        assertFalse(actual);
    }

    @Test
    void valid_apr_input() {
        boolean actual = commandValidator.validateAPR("0.06");
        assertTrue(actual);
    }

    @Test
    void valid_apr_at_zero_input() {
        boolean actual = commandValidator.validateAPR("0");
        assertTrue(actual);
    }

    @Test
    void valid_apr_at_ten_input() {
        boolean actual = commandValidator.validateAPR("10");
        assertTrue(actual);
    }

    @Test
    void invalid_negative_apr_input() {
        boolean actual = commandValidator.validateAPR("-1");
        assertFalse(actual);
    }

    @Test
    void invalid_apr_above_ten_input() {
        boolean actual = commandValidator.validateAPR("10.2");
        assertFalse(actual);
    }

    @Test
    void invalid_apr_with_letters_input() {
        boolean actual = commandValidator.validateAPR("a8");
        assertFalse(actual);
    }

    @Test
    void invalid_apr_with_special_char_as_input() {
        boolean actual = commandValidator.validateAPR("%1.3");
        assertFalse(actual);
    }

    @Test
    void invalid_create_cd_account_with_no_money_input() {
        boolean actual = commandValidator.validateCDAmount("");
        assertFalse(actual);
    }

    @Test
    void invalid_create_cd_account_with_negative_amount_input() {
        boolean actual = commandValidator.validateCDAmount("-1000");
        assertFalse(actual);
    }

    @Test
    void invalid_create_cd_account_with_letters_amount_input() {
        boolean actual = commandValidator.validateCDAmount("3xx3");
        assertFalse(actual);
    }

    @Test
    void invalid_create_cd_account_with_special_characters_amount_input() {
        boolean actual = commandValidator.validateCDAmount("3#!3");
        assertFalse(actual);
    }

    @Test
    void valid_create_cd_account_with_min_amount_input() {
        boolean actual = commandValidator.validateCDAmount("1000");
        assertTrue(actual);
    }

    @Test
    void valid_create_cd_account_with_max_amount_input() {
        boolean actual = commandValidator.validateCDAmount("10000");
        assertTrue(actual);
    }

    @Test
    void valid_create_cd_account_with_proper_amount_input() {
        boolean actual = commandValidator.validateCDAmount("6000");
        assertTrue(actual);
    }
}
