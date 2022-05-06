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

    public boolean validateAPR(String apr) {
        if (isInteger(apr) && isAPRInRange(apr)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAPRInRange(String apr) {
        Double d = Double.parseDouble(apr);
        if (d >= 0 && d <= 10) {
            return true;
        } else {
            return false;
        }
    }
}
