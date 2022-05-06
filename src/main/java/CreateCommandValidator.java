public class CreateCommandValidator extends CommandValidator {
    public static final double MIN_AMOUNT = (1000);
    public static final double MAX_AMOUNT = (10000);


    public CreateCommandValidator(Bank bank) {
        super(bank);
    }

    public boolean validateAPR(String apr) {
        if (isNum(apr) && isAPRInRange(apr)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAPRInRange(String apr) {
        Double d = Double.parseDouble(apr);
        if (d >= 0 && d <= 10) {
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
        Double d = Double.parseDouble(amount);
        if (d >= MIN_AMOUNT && d <= MAX_AMOUNT) {
            return true;
        } else {
            return false;
        }
    }

}
