package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandStorageTest {
    CommandStorage commandStorage;
    Bank bank;
    Account checking;

    @BeforeEach
    void setUp() {
        commandStorage = new CommandStorage();
        bank = new Bank(commandStorage);
        checking = new Checking(0.06, 0);
    }

    @Test
    void invalid_command_stored() {
        String command = "create checks 12345678 9.5";
        commandStorage.storeInvalidCommands(command);
        List<String> test = Arrays.asList(command);

        Assertions.assertEquals(test, commandStorage.getInvalidCommands());
    }

    @Test
    void invalid_no_command_stored() {
        List<String> test = Collections.emptyList();

        Assertions.assertEquals(test, commandStorage.getInvalidCommands());
    }

    @Test
    void invalid_empty_command_stored() {
        String command = "";
        commandStorage.storeInvalidCommands(command);
        List<String> test = Arrays.asList(command);

        Assertions.assertEquals(test, commandStorage.getInvalidCommands());
    }

    @Test
    void invalid_multiple_commands_stored() {
        String command = "create checks 12345678 9.5";
        String command2 = "create checkses 12345678 42.5";
        commandStorage.storeInvalidCommands(command);
        commandStorage.storeInvalidCommands(command2);
        List<String> test = Arrays.asList(command, command2);

        Assertions.assertEquals(test, commandStorage.getInvalidCommands());
    }

    @Test
    void valid_command_stored() {
        String command = "create checking 12345678 0.06";
        commandStorage.storeValidCommands(command);
        List<String> test = Arrays.asList(command);

        Assertions.assertEquals(test, commandStorage.getValidCommands());
    }

    @Test
    void valid_multiple_commands_stored() {
        String command = "create checking 12345678 0.06";
        String command2 = "withdraw 12345678 500";
        commandStorage.storeValidCommands(command);
        commandStorage.storeValidCommands(command2);
        List<String> test = Arrays.asList(command, command2);

        Assertions.assertEquals(test, commandStorage.getValidCommands());
    }

    @Test
    void bank_successfully_returns_commands() {
        commandStorage.storeValidCommands("create checking 12345678 1.0");
        List<String> cmds = bank.getCommands().getValidCommands();
        List<String> test = Arrays.asList("create checking 12345678 1.0");
        Assertions.assertEquals(test, cmds);

    }
}
