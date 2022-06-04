package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassTimeCommandProcessorTest {
    public static final String QUICK_ID = "12345678";
    Bank bank;
    CommandProcessor processor;
    PassTimeCommandProcessor passTime;
    CommandStorage commands;

    @BeforeEach
    void setUp() {
        bank = new Bank(commands);
        processor = new CommandProcessor(bank);
        passTime = new PassTimeCommandProcessor(bank);
    }

    @Test
    void valid_no_months_have_passed_checking() {
        processor.processor("create checking 12345678 1.0");
        processor.processor("deposit 12345678 500");
        passTime.processor("pass 0");

        Assertions.assertEquals(500, bank.getAccounts().get(QUICK_ID).getMoney());
        Assertions.assertEquals(0, bank.getAccounts().get(QUICK_ID).getMonth());
    }

    @Test
    void valid_one_month_of_time_passes_checking() {
        processor.processor("create checking 12345678 1.0");
        processor.processor("deposit 12345678 500");
        passTime.processor("pass 1");

        Assertions.assertEquals(500.4166666666667, bank.getAccounts().get(QUICK_ID).getMoney());
        Assertions.assertEquals(1, bank.getAccounts().get(QUICK_ID).getMonth());
    }

    @Test
    void valid_two_months_of_time_passes_checking() {
        processor.processor("create checking 12345678 1.0");
        processor.processor("deposit 12345678 500");
        passTime.processor("pass 2");

        Assertions.assertEquals(500.8336805555556, bank.getAccounts().get(QUICK_ID).getMoney());
        Assertions.assertEquals(2, bank.getAccounts().get(QUICK_ID).getMonth());
    }

    @Test
    void valid_twelve_months_of_time_passes_checking() {
        processor.processor("create checking 12345678 1.0");
        processor.processor("deposit 12345678 500");
        passTime.processor("pass 12");

        Assertions.assertEquals(505.022980443591, bank.getAccounts().get(QUICK_ID).getMoney());
        Assertions.assertEquals(12, bank.getAccounts().get(QUICK_ID).getMonth());
    }
}
