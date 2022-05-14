public class CreateCommandValidator extends CommandValidator {
    public static final double MIN_AMOUNT = (1000);
    public static final double MAX_AMOUNT = (10000);


    public CreateCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        if (bank.accountExistsByQuickID(parseString(command, 2)) &&
                validateAPR(parseString(command, 3)) &&
                countCmds(command)) {
            return true;
        } else {
            return false;
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
