package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CommandStorageTest {
    CommandStorage commandStorage;

    @BeforeEach
    void setUp() {
        commandStorage = new CommandStorage();
    }

    @Test
    void invalid_command_stored() {
        String command = "create checks 12345678 9.5";
        commandStorage.storeInvalidCommands(command);
        List<String> test = Arrays.asList(command);

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
}
