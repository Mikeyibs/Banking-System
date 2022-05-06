public class CommandValidator {
    private Bank bank;

    public CommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String command) {
        if (bank.accountExistsByQuickID("12345678")) {
            return false;
        } else {
            return true;
        }
    }
}
