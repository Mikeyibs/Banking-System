// Name: Michael Ibrahim | ID: mi374 | Section: 001

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
}
