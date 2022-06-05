package banking;

import java.util.List;

public class WithdrawCommandProcessor extends CommandProcessor {
    private String id;
    private Double amount;

    public WithdrawCommandProcessor(Bank bank) {
        super(bank);
    }

    public String getId() {
        return this.id;
    }

    public void setId(List<String> commands) {
        this.id = commands.get(1);
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(List<String> commands) {
        this.amount = Double.parseDouble(commands.get(2));
    }

    @Override
    public void processor(String command) {
        List<String> commands = parseString(command);
        setId(commands);
        setAmount(commands);
        withdraw(getId(), getAmount());
    }

    public void withdraw(String quickID, double amount) {
        bank.getAccounts().get(quickID).withdraw(amount);
    }
}
