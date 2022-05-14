// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MasterControlTest {
    MasterControl masterControl;
    private List<String> test;

    @BeforeEach
    void setUp() {
        test = new ArrayList<>();
        Bank bank = new Bank();
        masterControl = new MasterControl(bank, new CommandValidator(bank),
                new CommandProcessor(bank), new CommandStorage());
    }

    private void assertSingleCommand(String command, List<String> actual) {
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(command, actual.get(0));
    }

    @Test
    void create_command_contains_typo() {
        test.add("creat checking 12345678 1.0");

        List<String> actual = masterControl.start(test);
        assertSingleCommand("creat checking 12345678 1.0", actual);
    }

    @Test
    void deposit_command_contains_typo() {
        test.add("depositt 12345678 500");

        List<String> actual = masterControl.start(test);
        assertSingleCommand("depositt 12345678 500", actual);
    }

    @Test
    void two_invalid_commands_with_typos() {
        test.add("creat checking 12345678 1.0");
        test.add("depositt 12345678 500");

        List<String> actual = masterControl.start(test);

        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals("creat checking 12345678 1.0", actual.get(0));
        Assertions.assertEquals("depositt 12345678 500", actual.get(1));
    }

    @Test
    void invalid_creation_of_two_accounts_with_same_ID() {
        test.add("create checking 12345678 0.06");
        test.add("create checking 12345678 0.06");

        List<String> actual = masterControl.start(test);
        assertSingleCommand("create checking 12345678 0.06", actual);
    }
}
