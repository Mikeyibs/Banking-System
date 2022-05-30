package banking;

public class TransferCommandProcessor extends CommandProcessor {

    public TransferCommandProcessor(Bank bank) {
        super(bank);
    }

    public String getFromId(String command) {
        return parseString(command, 1);
    }

    public String getToId(String command) {
        return parseString(command, 2);
    }

    @Override
    public Double getAmount(String command) {
        return Double.valueOf(parseString(command, 3));
    }

    @Override
    public void processor(String command) {
        Double amount = getAmount(command);
        String fromId = getFromId(command);
        String toId = getToId(command);
        transfer(fromId, toId, amount);
    }

    public void transfer(String fromId, String toId, Double amount) {
        bank.withdraw(fromId, amount);
        bank.deposit(toId, amount);
    }
}
