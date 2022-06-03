package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

public class CommandValidator {
    public Bank bank;
    public CreateCommandValidator createCommandValidator;
    public DepositCommandValidator depositCommandValidator;
    public WithdrawCommandValidator withdrawCommandValidator;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String command) {
        switch (parseString(command, 0)) {
            case "create":
                return createValidator(command);
            case "deposit":
                return depositValidator(command);
            case "withdraw":
                return withdrawValidator(command);
            case "pass":
                return passValidator(command);
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

    public boolean withdrawValidator(String command) {
        withdrawCommandValidator = new WithdrawCommandValidator(bank);
        return withdrawCommandValidator.validate(command);
    }

    public boolean passValidator(String command) {
        return false;
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
