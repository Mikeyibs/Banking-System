package banking;

import java.util.List;

public class PassTimeCommandValidator extends CommandValidator {
    private String month;

    public PassTimeCommandValidator(Bank bank) {
        super(bank);
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(List<String> commands) {
        this.month = commands.get(1);
    }

    @Override
    public boolean validate(String command) {
        List<String> commands = parseString(command);
        try {
            setMonth(commands);
            return (validatePassTimeLength(command) && validateMonthInput(getMonth()));
        } catch (ArrayIndexOutOfBoundsException exc) {
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
        return validateMonthLength(Integer.parseInt(month));
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
