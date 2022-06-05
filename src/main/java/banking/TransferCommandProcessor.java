package banking;

import java.util.List;

public class TransferCommandProcessor extends CommandProcessor {
    private String fromId;
    private String toId;
    private double amount;

    public TransferCommandProcessor(Bank bank) {
        super(bank);
    }

    public String getFromId() {
        return this.fromId;
    }

    public String getToId() {
        return this.toId;
    }

    public double getAmount() {
        return this.amount;
    }

    @Override
    public void processor(String command) {
        List<String> commands = parseString(command);
        setVariables(commands);
        transfer(getFromId(), getToId(), getAmount());
    }

    public void transfer(String fromId, String toId, Double amount) {
        bank.withdraw(fromId, amount);
        bank.deposit(toId, amount);
    }

    public void setVariables(List<String> commands) {
        this.toId = commands.get(2);
        this.fromId = commands.get(1);
        this.amount = Integer.parseInt(commands.get(3));
    }
}
