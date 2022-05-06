public class DepositCommandValidator extends CommandValidator {

    public DepositCommandValidator(Bank bank) {
        super(bank);
    }

    public boolean validateDepositCommand(String command) {
        if (command == "deposit 12345678 500") {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateDepositCheckingAmount(String amount) {
        if (isNum(amount) && isAmountInRangeChecking(amount)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAmountInRangeChecking(String amount) {
        Double d = Double.parseDouble(amount);
        if (d >= 0 && d <= 1000) {
            return true;
        } else {
            return false;
        }
    }
}

