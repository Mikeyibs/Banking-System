package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

public abstract class Account {
    protected int month;
    protected double calcAPR;
    private double apr;
    private double money;

    public Account(double apr, double money) {
        this.apr = apr;
        this.money = money;
        this.month = 0;
        this.calcAPR = ((apr / 100) / 12);
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

    public void withdraw(double amount) {
        if (amount > money) {
            money = 0;
        } else {
            money -= amount;
        }
    }

    public abstract boolean validateDepositAmount(String amount);

    public abstract boolean validateWithdrawAmount(String amount);
}

