package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OutputTest {
    public static final double APR = 1.0;
    Bank bank;
    Output output;
    Account checking;
    Account cd;
    Account savings;
    CommandProcessor processor;
    CommandStorage storage;

    @BeforeEach
    void setUp() {
        bank = new Bank(storage);
        storage = new CommandStorage();
        output = new Output(bank, storage);
        checking = new Checking(APR, 0);
        savings = new Savings(APR, 0);
        cd = new CD(APR, 1500);
        processor = new CommandProcessor(bank);
    }

    @Test
    void parse_valid_command() {
        String command = "create savings 12345678 1.0";
        storage.storeValidCommands(command);

        List<String> finalOutput = output.parseString(command);
        List<String> test = Arrays.asList("create", "savings", "12345678", "1.0");
        Assertions.assertEquals(test, finalOutput);
    }

    @Test
    void value_formatted_to_the_correct_decimal_place() {
        double value = 20;
        String test = output.formatNumber(value);
        Assertions.assertEquals("20.00", test);
    }

    @Test
    void capitalize_first_letter_of_create_accurately() {
        String action = "create";
        String test = output.capitalize(action);
        Assertions.assertEquals("Create", test);
    }

    @Test
    void capitalize_first_letter_of_cd_accurately() {
        String action = "cd";
        String test = output.capitalize(action);
        Assertions.assertEquals("Cd", test);
    }

    @Test
    void capitalize_first_letter_of_savings_accurately() {
        String action = "savings";
        String test = output.capitalize(action);
        Assertions.assertEquals("Savings", test);
    }


    @Test
    void output_all_valid_commands_succesfully() {
        processor.processor("create checking 12345678 1.0");
        processor.processor("deposit 12345678 150");
        processor.processor("create savings 22334455 1.0");
        processor.processor("deposit 22334455 100");
        storage.storeValidCommands("create checking 12345678 1.0");
        storage.storeValidCommands("deposit 12345678 150");
        storage.storeValidCommands("create savings 22334455 1.0");
        storage.storeValidCommands("deposit 22334455 150");

        List<String> finalOutput = output.Output();
        List<String> test = Collections.emptyList();
        Assertions.assertEquals("Checking 12345678 150.00 1.00", finalOutput.get(0));
        Assertions.assertEquals("deposit 12345678 150", finalOutput.get(1));
        Assertions.assertEquals("Savings 22334455 100.00 1.00", finalOutput.get(2));
        Assertions.assertEquals("deposit 22334455 150", finalOutput.get(3));
    }

    @Test
    void output_valid_create_commands_deposit_commands_and_withdraw_commands() {
        processor.processor("create checking 12345678 1.0");
        processor.processor("deposit 12345678 150");
        processor.processor("create savings 22334455 1.0");
        processor.processor("deposit 22334455 150");
        processor.processor("withdraw 22334455 50");
        processor.processor("deposit 12345678 300");
        processor.processor("withdraw 12345678 100");
        storage.storeValidCommands("create checking 12345678 1.0");
        storage.storeValidCommands("deposit 12345678 150");
        storage.storeValidCommands("create savings 22334455 1.0");
        storage.storeValidCommands("deposit 22334455 150");
        storage.storeValidCommands("withdraw 22334455 50");
        storage.storeValidCommands("deposit 12345678 300");
        storage.storeValidCommands("withdraw 12345678 100");

        List<String> finalOutput = output.Output();
        Assertions.assertEquals("Checking 12345678 350.00 1.00", finalOutput.get(0));
        Assertions.assertEquals("deposit 12345678 150", finalOutput.get(1));
        Assertions.assertEquals("Savings 22334455 100.00 1.00", finalOutput.get(2));
        Assertions.assertEquals("deposit 22334455 150", finalOutput.get(3));
        Assertions.assertEquals("withdraw 22334455 50", finalOutput.get(4));
        Assertions.assertEquals("deposit 12345678 300", finalOutput.get(5));
        Assertions.assertEquals("withdraw 12345678 100", finalOutput.get(6));
    }

    @Test
    void output_valid_create_checking_command_with_deposit_command_for_specific_master_control_test_case() {
        processor.processor("Create savings 12345678 0.6");
        processor.processor("Deposit 12345678 700");
        processor.processor("creAte cHecKing 98765432 0.01");
        processor.processor("Deposit 98765432 300");
        processor.processor("Transfer 98765432 12345678 300");
        processor.processor("Pass 1");
        processor.processor("Create cd 23456789 1.2 2000");
        storage.storeValidCommands("Create savings 12345678 0.6");
        storage.storeValidCommands("Deposit 12345678 700");
        storage.storeInvalidCommands("Deposit 12345678 5000");
        storage.storeValidCommands("creAte cHecKing 98765432 0.01");
        storage.storeValidCommands("Deposit 98765432 300");
        storage.storeValidCommands("Transfer 98765432 12345678 300");
        storage.storeValidCommands("Pass 1");
        storage.storeValidCommands("Create cd 23456789 1.2 2000");

        List<String> finalOutput = output.Output();
        List<String> outputs = Collections.emptyList();
        Assertions.assertEquals("Savings 12345678 1000.50 0.60", finalOutput.get(0));
        Assertions.assertEquals("Deposit 12345678 700", finalOutput.get(1));
        Assertions.assertEquals("Transfer 98765432 12345678 300", finalOutput.get(2));
        Assertions.assertEquals("Cd 23456789 2000.00 1.20", finalOutput.get(3));
        Assertions.assertEquals("Deposit 12345678 5000", finalOutput.get(4));
    }

    @Test
    void output_valid_series_of_commands_that_tests_the_whole_system() {
        processor.processor("create checking 12345678 1.0");
        processor.processor("deposit 12345678 200");
        processor.processor("withdraw 12345678 50");
        processor.processor("create cd 22445566 1.0 1800");
        processor.processor("create savings 66554433 1.0");
        processor.processor("transfer 12345678 66554433 50");
        processor.processor("deposit 66554433 500");
        processor.processor("pass 2");
        processor.processor("withdraw 66554433 50");

        storage.storeValidCommands("create checking 12345678 1.0");
        storage.storeValidCommands("deposit 12345678 200");
        storage.storeValidCommands("withdraw 12345678 50");
        storage.storeValidCommands("create cd 22445566 1.0 1800");
        storage.storeValidCommands("create savings 66554433 1.0");
        storage.storeValidCommands("transfer 12345678 66554433 50");
        storage.storeValidCommands("deposit 66554433 500");
        storage.storeValidCommands("pass 2");
        storage.storeValidCommands("withdraw 66554433 50");

        List<String> outputs = output.Output();
        Assertions.assertEquals("Checking 12345678 100.16 1.00", outputs.get(0));
        Assertions.assertEquals("deposit 12345678 200", outputs.get(1));
        Assertions.assertEquals("withdraw 12345678 50", outputs.get(2));
        Assertions.assertEquals("Cd 22445566 1812.02 1.00", outputs.get(3));
        Assertions.assertEquals("Savings 66554433 500.91 1.00", outputs.get(4));
        Assertions.assertEquals("transfer 12345678 66554433 50", outputs.get(5));
        Assertions.assertEquals("deposit 66554433 500", outputs.get(6));
        Assertions.assertEquals("withdraw 66554433 50", outputs.get(7));
    }
}
