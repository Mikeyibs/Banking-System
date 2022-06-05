package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.List;

public class CreateCommandProcessor extends CommandProcessor {
    private double apr;
    private String type;
    private String id;
    private double amount;

    public CreateCommandProcessor(Bank bank) {
        super(bank);
    }

    public double getApr() {
        return this.apr;
    }

    public String getType() {
        return this.type;
    }

    public void setType(List<String> commands) {
        this.type = commands.get(1);
    }

    public String getID() {
        return this.id;
    }

    public double getAmount() {
        return this.amount;
    }

    @Override
    public void processor(String command) {
        List<String> commands = parseString(command);
        setType(commands);
        setVariables(commands);
        if (getType().equals("checking")) {
            createCheckingAccount(getID(), getApr());
        } else if (getType().equals("savings")) {
            createSavingsAccount(getID(), getApr());
        } else if (getType().equals("cd")) {
            createCDAccount(getID(), getApr(), getAmount());
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

    public void setVariables(List<String> commands) {
        if (getType().equals("checking") || getType().equals("savings")) {
            this.id = commands.get(2);
            this.apr = Double.parseDouble(commands.get(3));
        } else if (getType().equals("cd")) {
            this.id = commands.get(2);
            this.apr = Double.parseDouble(commands.get(3));
            this.amount = Double.parseDouble(commands.get(4));
        }
    }
}
