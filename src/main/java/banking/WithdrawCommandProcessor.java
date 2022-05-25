package banking;

public class WithdrawCommandProcessor extends CommandProcessor {

    public WithdrawCommandProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void processor(String command) {
        Double amount = getWithdrawAmt(command);
        String quickID = getWithdrawID(command);
        if (amount >= 0) {
            withdraw(quickID, amount);
        }
    }

    public void withdraw(String quickID, double amount) {
        bank.getAccounts().get(quickID).withdraw(amount);
    }

    public Double getWithdrawAmt(String command) {
        return Double.valueOf(parseString(command, 2));
    }

    public String getWithdrawID(String command) {
        return parseString(command, 1);
    }
}
