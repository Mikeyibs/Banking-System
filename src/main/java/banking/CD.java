package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

public class CD extends Account {
    private double apr;
    private double money;

    public CD(double apr, double money) {
        super(apr, money);
    }

    @Override
    public boolean validateDepositAmount(String amount) {
        return false;
    }

    @Override
    public boolean validateWithdrawAmount(String amount) {
        double dbl = Double.parseDouble(amount);
        return dbl >= getMoney();
    }

    @Override
    public void aprCalculation() {
        double calc = (getCalcAPR() * getMoney()) * 4;
        this.money = this.money + calc;
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
        this.withdrawRestriction = getMonth() > 11;
    }
}
