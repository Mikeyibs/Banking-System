// Name: Michael Ibrahim | ID: mi374 | Section: 001

public class Savings {
    private int id;
    private double apr;
    private int money;

    public Savings(int id, double apr, int money) {
        this.id = id;
        this.apr = apr;
        this.money = 0;
    }

    public int getId() {
        return id;
    }

    public double getApr() {
        return apr;
    }

    public int getMoney() {
        return money;
    }

    public void deposit(int money) {
        this.money += money;
    }

    public void withdraw(int money) {
        this.money -= money;
    }
}
