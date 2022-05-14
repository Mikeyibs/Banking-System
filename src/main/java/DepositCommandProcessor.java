// Name: Michael Ibrahim | ID: mi374 | Section: 001

public class DepositCommandProcessor extends CommandProcessor {

    public DepositCommandProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void processor(String command) {
        Double amount = getDeposit(command);
        String quickID = getDepositID(command);
        if (amount >= 0) {
            deposit(quickID, amount);
        }
    }

    public void deposit(String quickID, double amount) {
        bank.getAccounts().get(quickID).deposit(amount);
    }

    public Double getDeposit(String command) {
        return Double.valueOf(parseString(command, 2));
    }

    public String getDepositID(String command) {
        return parseString(command, 1);
    }
}
