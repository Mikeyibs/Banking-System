package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.Arrays;
import java.util.List;

public class CommandValidator {
    public Bank bank;
    public CreateCommandValidator createCommandValidator;
    public DepositCommandValidator depositCommandValidator;
    public WithdrawCommandValidator withdrawCommandValidator;
    public PassTimeCommandValidator passTimeCommandValidator;
    public TransferCommandValidator transferCommandValidator;
    public String action;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(List<String> commands) {
        this.action = commands.get(0);
    }

    public boolean validate(String command) {
        List<String> commands = parseString(command);
        setAction(commands);
        switch (getAction()) {
            case "create":
                return createValidator(command);
            case "deposit":
                return depositValidator(command);
            case "withdraw":
                return withdrawValidator(command);
            case "pass":
                return passValidator(command);
            case "transfer":
                return transferValidator(command);
            default:
                return false;
        }
    }

    private boolean transferValidator(String command) {
        transferCommandValidator = new TransferCommandValidator(bank);
        return transferCommandValidator.validate(command);
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
        passTimeCommandValidator = new PassTimeCommandValidator(bank);
        return passTimeCommandValidator.validate(command);
    }

    public List<String> parseString(String command) {
        return Arrays.asList(command.toLowerCase().trim().split(" "));
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
