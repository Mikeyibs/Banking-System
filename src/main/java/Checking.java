// Name: Michael Ibrahim | ID: mi374 | Section: 001

public class Checking extends Account {
    private double apr;
    private double money;

    public Checking(double apr, double money) {
        super(apr, 0);
    }

    public boolean validateDepositAmount(String amount) {
        if (isAmountInRangeChecking(amount)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAmountInRangeChecking(String amount) {
        Double d = Double.parseDouble(amount);
        if (d >= 0 && d <= 1000) {
            return true;
        } else {
            return false;
        }
    }
}
