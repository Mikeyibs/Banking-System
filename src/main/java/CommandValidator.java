public class CommandValidator {
    public Bank bank;
    public CreateCommandValidator createCommandValidator;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String command) {
        if (command == "create checking 12345678 0.06") {
            return true;
        } else if (command == "craete savings 12345678 0.06") {
            return true;
        } else if (command == "create cd 12345678 0.06 2000") {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateID(String quickId) {
        if (quickId.length() == 8 && isNum(quickId)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNum(String num) { // https://www.baeldung.com/java-check-string-number
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
