package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

public abstract class Account {
    protected int month;
    protected double calcAPR;
    protected double apr;
    protected double money;
    protected boolean withdrawRestriction;

    public Account(double apr, double money) {
        this.apr = apr;
        this.money = money;
        this.month = 0;
        this.calcAPR = ((apr / 100) / 12);
        this.withdrawRestriction = true;
    }

    public double getApr() {
        return apr;
    }

    public double getMoney() {
        return money;
    }

    public double getCalcAPR() {
        return calcAPR;
    }

    public int getMonth() {
        return month;
    }

    public boolean getWithdrawRestriction() {
        return this.withdrawRestriction;
    }

    public void deposit(double money) {
        this.money += money;
    }

    protected void passTime() {
        this.month += 1;
        aprCalculation();
    }

    protected void aprCalculation() {
        double calc = getCalcAPR() * getMoney();
        this.money = this.money + calc;
    }

    protected void withdrawCalculation(double amount) {
        if (amount > money) {
            money = 0;
        } else {
            money -= amount;
        }
    }

    public void withdraw(double amount) {
        withdrawCalculation(amount);
        this.withdrawRestriction = false;
    }

    public abstract boolean validateDepositAmount(String amount);

    public abstract boolean validateWithdrawAmount(String amount);
}

