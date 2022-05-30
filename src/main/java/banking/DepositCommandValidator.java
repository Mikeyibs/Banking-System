package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

public class DepositCommandValidator extends CommandValidator {

    public DepositCommandValidator(Bank bank) {
        super(bank);
    }

    public String getAmount(String command) {
        return parseString(command, 2);
    }

    public String getID(String command) {
        return parseString(command, 1);
    }

    @Override
    public boolean validate(String command) {
        if (validateAccountExists(getID(command))) {
            if (validateDepositAmount(getAmount(command), getID(command)) &&
                    validateDepositLength(command) && validateID(getID(command))) {
                return true;
            } else {
                return false;
            }
        } else {
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

