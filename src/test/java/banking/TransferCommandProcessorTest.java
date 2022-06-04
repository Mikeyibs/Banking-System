package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferCommandProcessorTest {
    Bank bank;
    CommandProcessor processor;
    TransferCommandProcessor transferProcessor;
    CommandStorage commands;

    @BeforeEach
    void setUp() {
        bank = new Bank(commands);
        processor = new CommandProcessor(bank);
        transferProcessor = new TransferCommandProcessor(bank);
    }

    @Test
    void transfer_to_checking_account_from_checking_account() {
        processor.processor("create checking 12345678 1.0");
        processor.processor("deposit 12345678 500");
        processor.processor("create checking 22334455 0.8");
        transferProcessor.processor("transfer 12345678 22334455 500");

        Assertions.assertEquals(0, bank.getAccounts().get("12345678").getMoney());
        Assertions.assertEquals(500, bank.getAccounts().get("22334455").getMoney());
    }

    @Test
    void transfer_to_savings_account_from_savings_account() {
        processor.processor("create savings 12345678 1.0");
        processor.processor("deposit 12345678 500");
        processor.processor("create savings 22334455 0.8");
        transferProcessor.processor("transfer 12345678 22334455 500");

        Assertions.assertEquals(0, bank.getAccounts().get("12345678").getMoney());
        Assertions.assertEquals(500, bank.getAccounts().get("22334455").getMoney());
    }

    @Test
    void transfer_to_checking_account_from_savings_account() {
        processor.processor("create savings 12345678 1.0");
        processor.processor("deposit 12345678 500");
        processor.processor("create checking 22334455 0.8");
        transferProcessor.processor("transfer 12345678 22334455 500");

        Assertions.assertEquals(0, bank.getAccounts().get("12345678").getMoney());
        Assertions.assertEquals(500, bank.getAccounts().get("22334455").getMoney());
    }

    @Test
    void transfer_to_savings_account_from_checking_account() {
        processor.processor("create checking 12345678 1.0");
        processor.processor("deposit 12345678 500");
        processor.processor("create savings 22334455 0.8");
        transferProcessor.processor("transfer 12345678 22334455 500");

        Assertions.assertEquals(0, bank.getAccounts().get("12345678").getMoney());
        Assertions.assertEquals(500, bank.getAccounts().get("22334455").getMoney());
    }
}
