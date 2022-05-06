public class CreateCommandValidator extends CommandValidator {

    public CreateCommandValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        return false;
    }

    public boolean validateIDExists(String quickID) {
        if (bank.accountExistsByQuickID(quickID)) {
            return false;
        } else {
            return true;
        }
    }
}
