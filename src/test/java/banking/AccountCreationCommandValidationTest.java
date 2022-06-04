package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
    CommandStorage commands;


    @BeforeEach
    void set_up() {
        bank = new Bank(commands);
        checking = new Checking(APR, 0);
        savings = new Savings(APR, 0);
        cd = new CD(APR, MONEY);

        commandValidator = new CreateCommandValidator(bank);
    }

    @Test
    void valid_create_checking_command() {
        boolean create = commandValidator.validate("create checking 12345678 0.06");
        assertTrue(create);
    }

    @Test
    void invalid_create_checking_command_with_no_create() {
        boolean create = commandValidator.validate("checking 12345678 0.06");
        assertFalse(create);
    }

    @Test
    void invalid_create_checking_command_with_money_at_end() {
        boolean create = commandValidator.validate("create checking 12345678 0.06 1500");
        assertFalse(create);
    }

    @Test
    void valid_create_savings_command() {
        boolean create = commandValidator.validate("create savings 12345678 0.06");
        assertTrue(create);
    }

    @Test
    void invalid_create_savings_command_with_no_create() {
        boolean create = commandValidator.validate("savings 12345678 0.06");
        assertFalse(create);
    }

    @Test
    void invalid_create_savings_command_with_money_at_end() {
        boolean create = commandValidator.validate("create savings 12345678 0.06 1500");
        assertFalse(create);
    }

    @Test
    void valid_create_cd_command() {
        boolean create = commandValidator.validate("create cd 12345678 0.06 2000");
        assertTrue(create);
    }

    @Test
    void invalid_create_cd_command_with_no_create() {
        boolean create = commandValidator.validate("cd 12345678 0.06");
        assertFalse(create);
    }

    @Test
    void empty_id_input() {
        boolean create = commandValidator.validateID("");
        assertFalse(create);
    }

    @Test
    void valid_eight_number_id_input() {
        boolean create = commandValidator.validateID(QUICK_ID);
        assertTrue(create);
    }

    @Test
    void invalid_nine_number_id_input() {
        boolean create = commandValidator.validateID("123456789");
        assertFalse(create);
    }

    @Test
    void invalid_seven_number_id_input() {
        boolean create = commandValidator.validateID("1234567");
        assertFalse(create);
    }

    @Test
    void invalid_with_single_single_letter_id_input() {
        boolean create = commandValidator.validateID("X1234567");
        assertFalse(create);
    }

    @Test
    void invalid_with_single_special_character_id_input() {
        boolean create = commandValidator.validateID("123457#");
        assertFalse(create);
    }

    @Test
    void empty_apr_input() {
        boolean create = commandValidator.validateAPR("");
        assertFalse(create);
    }

    @Test
    void valid_apr_input() {
        boolean create = commandValidator.validateAPR("0.06");
        assertTrue(create);
    }

    @Test
    void valid_apr_at_zero_input() {
        boolean create = commandValidator.validateAPR("0");
        assertTrue(create);
    }

    @Test
    void valid_apr_at_ten_input() {
        boolean create = commandValidator.validateAPR("10");
        assertTrue(create);
    }

    @Test
    void invalid_negative_apr_input() {
        boolean create = commandValidator.validateAPR("-1");
        assertFalse(create);
    }

    @Test
    void invalid_apr_above_ten_input() {
        boolean create = commandValidator.validateAPR("10.2");
        assertFalse(create);
    }

    @Test
    void invalid_apr_with_letters_input() {
        boolean create = commandValidator.validateAPR("a8");
        assertFalse(create);
    }

    @Test
    void invalid_apr_with_special_char_as_input() {
        boolean create = commandValidator.validateAPR("%1.3");
        assertFalse(create);
    }

    @Test
    void method_successfully_validates_id_is_in_bank() {
        bank.addAccount("12345678", checking);
        boolean create = commandValidator.validateIDExistsInBank("12345678");
        assertFalse(create);
    }

    @Test
    void method_successfully_validates_id_not_in_bank() {
        boolean create = commandValidator.validateIDExistsInBank("12345678");
        assertTrue(create);
    }

    @Test
    void set_variables_method_accurately_sets_vars() {
        List<String> commands = Arrays.asList("create", "checking", "12345678", "1.0");
        commandValidator.setVariables(commands);
        String id = commandValidator.getID();
        String type = commandValidator.getType();
        String apr = commandValidator.getAPR();
        Assertions.assertEquals("12345678", id);
        Assertions.assertEquals("checking", type);
        Assertions.assertEquals("1.0", apr);
    }

    @Test
    void count_commands_successfully_counts_all_commands() {
        List<String> commands = Arrays.asList("create", "checking", "12345678", "1.0");
        commandValidator.setType(commands);
        boolean create = commandValidator.countCommands(commands);
        Assertions.assertTrue(create);
    }

    @Test
    void large_create_checking_command_passed_to_counts_all_commands() {
        List<String> commands = Arrays.asList("create", "checking", "12345678", "1.0", "2000");
        commandValidator.setType(commands);
        boolean create = commandValidator.countCommands(commands);
        assertFalse(create);
    }
}
