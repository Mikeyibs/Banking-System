import java.util.Objects;

public class CommandValidator {
    public Bank bank;
    public CreateCommandValidator createCommandValidator;
    public DepositCommandValidator depositCommandValidator;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String command) {
        if (countCmds(command)) {
            switch (parseString(command, 0)) {
                case "create":
                    return createValidator(command);
                case "deposit":
                    return depositValidator(command);
                default:
                    return false;
            }
        } else {
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

    public boolean countCmds(String command) {

        String[] arrStr = command.split(" ", 0);

        if (Objects.equals(getType(command), "cd") && arrStr.length > 5) {
            return false;
        } else if (!Objects.equals(getType(command), "cd") && arrStr.length > 4) {
            return false;
        } else {
            return true;
        }
    }

    public String getType(String command) {
        String type = parseString(command, 1);

        return type;
    }

    public String parseString(String command, int limit) {

        String[] arrStr = command.split(" ", 0);

        return arrStr[limit];
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
