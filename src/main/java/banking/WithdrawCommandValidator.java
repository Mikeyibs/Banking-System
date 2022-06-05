package banking;

import java.util.List;

public class WithdrawCommandValidator extends CommandValidator {
    private String id;
    private String amount;

    public WithdrawCommandValidator(Bank bank) {
        super(bank);
    }

    public String getID() {
        return this.id;
    }

    public void setId(List<String> commands) {
        this.id = commands.get(1);
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(List<String> commands) {
        this.amount = commands.get(2);
    }

    @Override
    public boolean validate(String command) {
        List<String> commands = parseString(command);
        try {
            setId(commands);
            setAmount(commands);
            return (validateAccountExists(getID()) && validateWithdrawLength(command) && validateID(getID())
                    && validateWithdrawAmount(getAmount(), getID())
                    && accountCanWithdrawThisMonth(getID()));
        } catch (ArrayIndexOutOfBoundsException exc) {
            return false;
        }
    }

    public boolean validateWithdrawLength(String command) {
        String[] arrStr = command.split(" ", 0);
        if (arrStr.length == 3) {
            return true;
        } else {
            return false;
        }
    }

    public boolean accountCanWithdrawThisMonth(String quickId) {
        if (bank.getAccounts().get(quickId).getWithdrawRestriction()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateWithdrawAmount(String amount, String quickId) {
        if (isNum(amount)) {
            if (bank.getAccounts().get(quickId).validateWithdrawAmount(amount)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean validateAccountExists(String quickId) {
        return bank.accountExistsByQuickID(quickId);
    }
}
