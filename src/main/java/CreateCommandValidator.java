// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.Objects;

public class CreateCommandValidator extends CommandValidator {
    public static final double MIN_AMOUNT = (1000);
    public static final double MAX_AMOUNT = (10000);


    public CreateCommandValidator(Bank bank) {
        super(bank);
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

    public boolean countCommands(String command) {
        String[] arrStr = command.split(" ", 0);
        return validateLength(arrStr, getType(command));
    }

    @Override
    public boolean validate(String command) {
        if (countCommands(command)) {
            if (validateIDExistsInBank(getID(command)) && validateAPR(getAPR(command))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
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

    public boolean validateIDExistsInBank(String quickID) {
        if (bank.accountExistsByQuickID(quickID)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateAPR(String apr) {
        if (isNum(apr) && isAPRInRange(apr)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAPRInRange(String apr) {
        Double dbl = Double.valueOf(apr);
        if (dbl >= 0 && dbl <= 10) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateCDAmount(String amount) {
        if (isNum(amount) && isCDInRange(amount)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isCDInRange(String amount) {
        Double dbl = Double.valueOf(amount);
        if (dbl >= MIN_AMOUNT && dbl <= MAX_AMOUNT) {
            return true;
        } else {
            return false;
        }
    }

}
