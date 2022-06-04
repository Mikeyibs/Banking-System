package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassTimeCommandValidatorTest {
    Bank bank;
    PassTimeCommandValidator commandValidator;
    CommandStorage commands;

    @BeforeEach
    void setUp() {
        bank = new Bank(commands);
        commandValidator = new PassTimeCommandValidator(bank);
    }

    @Test
    void validate_month_input_properly_validates_the_month_as_int() {
        String test = "2";
        boolean pass = commandValidator.validateMonthInput(test);
        Assertions.assertTrue(pass);
    }

    @Test
    void validate_month_input_properly_validates_null_month_input() {
        String test = "";
        boolean pass = commandValidator.validateMonthInput(test);
        Assertions.assertFalse(pass);
    }

    @Test
    void validate_month_input_properly_validates_month_with_decimals() {
        String test = "2.0";
        boolean pass = commandValidator.validateMonthInput(test);
        Assertions.assertFalse(pass);
    }

    @Test
    void valid_pass_time_command_length() {
        String command = ("pass 5");
        boolean pass = commandValidator.validatePassTimeLength(command);
        Assertions.assertTrue(pass);
    }

    @Test
    void invalid_pass_time_command_length_too_little_args() {
        String command = ("pass");
        boolean pass = commandValidator.validatePassTimeLength(command);
        Assertions.assertFalse(pass);
    }

    @Test
    void invalid_pass_time_command_length_too_long() {
        String command = ("pass 12 12");
        boolean pass = commandValidator.validatePassTimeLength(command);
        Assertions.assertFalse(pass);
    }

    @Test
    void valid_validation_function_successfully_validates_months_as_int_value() {
        boolean pass = commandValidator.validateMonthInput("5");
        Assertions.assertTrue(pass);
    }

    @Test
    void valid_validation_function_successfully_validates_max_months_as_int_value() {
        boolean pass = commandValidator.validateMonthInput("60");
        Assertions.assertTrue(pass);
    }

    @Test
    void invalid_validation_function_zero_months_input() {
        boolean pass = commandValidator.validateMonthInput("0");
        Assertions.assertFalse(pass);
    }

    @Test
    void valid_pass_time_command() {
        String command = ("pass 5");
        boolean pass = commandValidator.validate(command);
        Assertions.assertTrue(pass);
    }

    @Test
    void valid_pass_time_command_maximum_months() {
        String command = ("pass 60");
        boolean pass = commandValidator.validate(command);
        Assertions.assertTrue(pass);
    }

    @Test
    void valid_pass_time_command_minimum_months() {
        String command = ("pass 1");
        boolean pass = commandValidator.validate(command);
        Assertions.assertTrue(pass);
    }

    @Test
    void invalid_pass_time_command_zero_months() {
        String command = ("pass 0");
        boolean pass = commandValidator.validate(command);
        Assertions.assertFalse(pass);
    }

    @Test
    void invalid_pass_time_command_more_than_60_months() {
        String command = ("pass 62");
        boolean pass = commandValidator.validate(command);
        Assertions.assertFalse(pass);
    }

    @Test
    void invalid_pass_time_command_too_few_args() {
        String command = ("pass");
        boolean pass = commandValidator.validate(command);
        Assertions.assertFalse(pass);
    }

    @Test
    void invalid_pass_time_command_too_many_args() {
        String command = ("pass 12 12");
        boolean pass = commandValidator.validate(command);
        Assertions.assertFalse(pass);
    }
}
