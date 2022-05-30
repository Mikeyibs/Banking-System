package banking;

public class WithdrawCommandValidator extends CommandValidator {

    public WithdrawCommandValidator(Bank bank) {
        super(bank);
    }

    public String getID(String command) {
        return parseString(command, 1);
    }

    public String getAmount(String command) {
        return parseString(command, 2);
    }

    @Override
    public boolean validate(String command) {
        if (validateAccountExists(getID(command))) {
            if (validateWithdrawAmount(getAmount(command), getID(command)) &&
                    validateID(getID(command)) && validateWithdrawLength(command)) {
                return true;
            } else {
                return false;
            }
        } else {
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
