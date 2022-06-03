package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

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

    @Override
    public boolean validateWithdrawAmount(String amount) {
        Double dbl = Double.valueOf(amount);
        if (dbl >= 0 && dbl <= 1000) {
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

    @Override
    public void withdraw(double amount) {
        if (amount > money) {
            money = 0;
            this.withdrawRestriction = false;
        } else {
            money -= amount;
        }
    }

    @Override
    public void passTime() {
        this.month += 1;
        aprCalculation();
        this.withdrawRestriction = true;
    }
}
