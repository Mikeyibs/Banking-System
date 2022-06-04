package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
        bank = new Bank();
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
    void output_all_valid_commands_succesfully() {
        storage.storeValidCommands("create checking 12345678 1.0");
        storage.storeValidCommands("create savings 22334455 1.0");
        storage.storeValidCommands("create checking 55667788 1.0");

        List<String> finalOutput = output.Output();
    }
}
