package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

public class Checking extends Account {

    public Checking(double apr, double money) {
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

    @Override
    public void withdraw(double amount) {
        withdrawCalculation(amount);
    }

    @Override
    public boolean validateWithdrawAmount(String amount) {
        Double dbl = Double.valueOf(amount);
        if (dbl >= 0 && dbl <= 400) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAmountInRangeChecking(String amount) {
        Double dbl = Double.valueOf(amount);
        if (dbl >= 0 && dbl <= 1000) {
            return true;
        } else {
            return false;
        }
    }
}
