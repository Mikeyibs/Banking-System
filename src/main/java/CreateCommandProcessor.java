// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.Objects;

public class CreateCommandProcessor extends CommandProcessor {

    public CreateCommandProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void processor(String command) {
        if (Objects.equals(getType(command), "checking")) {
            createCheckingAccount(getID(command), getAPR(command));
        } else if (Objects.equals(getType(command), "savings")) {
            createSavingsAccount(getID(command), getAPR(command));
        } else if (Objects.equals(getType(command), "cd")) {
            createCDAccount(getID(command), getAPR(command), getAmount(command));
        }
    }

    public void createCheckingAccount(String quickID, double APR) {
        Account checking = new Checking(APR, 0);
        bank.addAccount(quickID, checking);
    }

    public void createCDAccount(String quickID, double APR, double amount) {
        Account cd = new CD(APR, amount);
        bank.addAccount(quickID, cd);
    }

    public void createSavingsAccount(String quickID, double APR) {
        Account savings = new Savings(APR, 0);
        bank.addAccount(quickID, savings);
    }
}
