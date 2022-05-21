package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.Objects;

public class CommandValidator {
    public Bank bank;
    public CreateCommandValidator createCommandValidator;
    public DepositCommandValidator depositCommandValidator;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String command) {
        switch (parseString(command, 0)) {
            case "create":
                return createValidator(command);
            case "deposit":
                return depositValidator(command);
            default:
                return false;
        }
    }

    public boolean createValidator(String command) {
        createCommandValidator = new CreateCommandValidator(bank);
        return createCommandValidator.validate(command);
    }

    public boolean depositValidator(String command) {
        depositCommandValidator = new DepositCommandValidator(bank);
        return depositCommandValidator.validate(command);
    }

    public boolean countCommands(String command) {
        String[] arrStr = command.split(" ", 0);
        return validateLength(arrStr, getType(command));
    }

    public String getType(String command) {
        return parseString(command, 1);
    }

    public String getID(String command) {
        return parseString(command, 2);
    }

    public String getAPR(String command) {
        return parseString(command, 3);
    }

    public String parseString(String command, int limit) {

        String[] arrStr = command.split(" ", 0);

        return arrStr[limit];
    }

    public boolean validateLength(String[] arrStr, String type) {
        if (Objects.equals(type, "cd")) {
            if (arrStr.length == 5) {
                return true;
            } else {
                return false;
            }
        } else if (Objects.equals(type, "checking")) {
            if (arrStr.length == 4) {
                return true;
            } else {
                return false;
            }
        } else if (Objects.equals(type, "savings")) {
            if (arrStr.length == 4) {
                return true;
            } else {
                return false;
            }
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

    public boolean isNum(String num) {
        if (num.length() == 0) {
            return false;
        }
        try {
            Double dbl = Double.valueOf(num);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

}
