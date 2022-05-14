// Name: Michael Ibrahim | ID: mi374 | Section: 001

public class Savings extends Account {
    private double apr;
    private double money;

    public Savings(double apr, double money) {
        super(apr, 0);
    }

    @Override
    public boolean validateDepositAmount(String amount) {
        if (isAmountInRangeChecking(amount)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAmountInRangeChecking(String amount) {
        Double dbl = Double.valueOf(amount);
        if (dbl >= 0 && dbl <= 2500) {
            return true;
        } else {
            return false;
        }
    }
}
