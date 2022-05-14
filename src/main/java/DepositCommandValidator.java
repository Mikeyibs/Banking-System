public class DepositCommandValidator extends CommandValidator {

    public DepositCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        if (validateDepositAmount(parseString(command, 2), parseString(command, 1))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateDepositCommand(String command) {
        if (command == "deposit 12345678 500") {
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
}

