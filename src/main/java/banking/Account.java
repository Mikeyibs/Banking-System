package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

public abstract class Account {
    private double apr;
    private double money;

    public Account(double apr, double money) {
        this.apr = apr;
        this.money = money;
    }

    public double getApr() {
        return apr;
    }

    public double getMoney() {
        return money;
    }

    public void deposit(double money) {
        this.money += money;
    }

    public abstract boolean validateDepositAmount(String amount);

    public void withdraw(double amount) {
        if (amount > money) {
            money = 0;
        } else {
            money -= amount;
        }
    }
}

