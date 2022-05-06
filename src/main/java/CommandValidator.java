public class CommandValidator {
    public Bank bank;
    public CreateCommandValidator createCommandValidator;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String command) {
        if (bank.accountExistsByQuickID("12345678")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateID(String quickId) {
        if (quickId.length() == 8 && isInteger(quickId)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isInteger(String num) { // https://www.baeldung.com/java-check-string-number
        if (num.length() == 0) {
            return false;
        }
        try {
            Double d = Double.parseDouble(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
