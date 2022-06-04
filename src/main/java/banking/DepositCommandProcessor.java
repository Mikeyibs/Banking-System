package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.List;

public class DepositCommandProcessor extends CommandProcessor {
    private String id;
    private double amount;

    public DepositCommandProcessor(Bank bank) {
        super(bank);
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
        setVariables(commands);
        deposit(getID(), getAmount());
    }

    public void deposit(String quickID, double amount) {
        bank.getAccounts().get(quickID).deposit(amount);
    }

    public void setVariables(List<String> commands) {
        this.id = commands.get(1);
        this.amount = Double.parseDouble(commands.get(2));
    }

}
