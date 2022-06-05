package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterControlTest {
    MasterControl masterControl;
    CommandStorage commands;
    private List<String> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>();
        Bank bank = new Bank(commands);
        masterControl = new MasterControl(bank, new CommandValidator(bank),
                new CommandProcessor(bank), new CommandStorage());
    }

    private void assertSingleCommand(String command, List<String> actual) {
        assertEquals(1, actual.size());
        assertEquals(command, actual.get(0));
    }

    @Test
    void create_command_contains_typo() {
        input.add("creat checking 12345678 1.0");

        List<String> actual = masterControl.start(input);
        assertSingleCommand("creat checking 12345678 1.0", actual);
    }

    @Test
    void deposit_command_contains_typo() {
        input.add("depositt 12345678 500");

        List<String> actual = masterControl.start(input);
        assertSingleCommand("depositt 12345678 500", actual);
    }

    @Test
    void two_invalid_commands_with_typos() {
        input.add("creat checking 12345678 1.0");
        input.add("depositt 12345678 500");

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("creat checking 12345678 1.0", actual.get(0));
        assertEquals("depositt 12345678 500", actual.get(1));
    }

    @Test
    void invalid_creation_of_two_accounts_with_same_ID() {
        input.add("create checking 12345678 0.06");
        input.add("create checking 12345678 0.06");

        List<String> actual = masterControl.start(input);
        assertEquals("Checking 12345678 0.00 0.06", actual.get(0));
        assertEquals("create checking 12345678 0.06", actual.get(1));
    }

    @Test
    void sample_make_sure_this_passes_unchanged_or_you_will_fail() {
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 700");
        input.add("Deposit 12345678 5000");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("Deposit 98765432 300");
        input.add("Transfer 98765432 12345678 300");
        input.add("Pass 1");
        input.add("Create cd 23456789 1.2 2000");
        List<String> actual = masterControl.start(input);

        assertEquals(5, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
        assertEquals("Deposit 12345678 5000", actual.get(4));
    }
}
