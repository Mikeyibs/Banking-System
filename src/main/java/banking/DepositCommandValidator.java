package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.List;

public class DepositCommandValidator extends CommandValidator {
    private String amount;
    private String id;

    public DepositCommandValidator(Bank bank) {
        super(bank);
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(List<String> commands) {
        this.amount = commands.get(2);
    }

    public String getID() {
        return this.id;
    }

    public void setId(List<String> commands) {
        this.id = commands.get(1);
    }

    @Override
    public boolean validate(String command) {
        List<String> commands = parseString(command);
        try {
            setAmount(commands);
            setId(commands);
            return (validateAccountExists(getID()) && validateDepositAmount(getAmount(), getID()) &&
                    validateDepositLength(command) && validateID(getID()));
        } catch (ArrayIndexOutOfBoundsException exc) {
            return false;
        }
    }

    public boolean validateDepositLength(String command) {
        String[] arrStr = command.split(" ", 0);
        if (arrStr.length == 3) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateDepositAmount(String amount, String quickId) {
        if (isNum(amount)) {
            if (bank.getAccounts().get(quickId).validateDepositAmount(amount)) {
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

