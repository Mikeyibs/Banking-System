package banking;

public class PassTimeCommandValidator extends CommandValidator {

    public PassTimeCommandValidator(Bank bank) {
        super(bank);
    }

    public String getMonth(String command) {
        return parseString(command, 1);
    }

    @Override
    public boolean validate(String command) {
        if (validatePassTimeLength(command) && validateMonthInput(getMonth(command))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateMonthLength(int month) {
        if (month > 0 && month <= 60) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateMonthInput(String month) {
        if (month == null) {
            return false;
        }
        try {
            int validMonth = Integer.parseInt(month);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return validateMonthLength(Integer.valueOf(month));
    }

    public boolean validatePassTimeLength(String command) {
        String[] arrStr = command.split(" ", 0);
        if (arrStr.length == 2) {
            return true;
        } else {
            return false;
        }
    }
}
