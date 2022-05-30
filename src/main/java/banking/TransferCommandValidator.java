package banking;

public class TransferCommandValidator extends CommandValidator {

    public TransferCommandValidator(Bank bank) {
        super(bank);
    }

    public String getFromId(String command) {
        return parseString(command, 1);
    }

    public String getToId(String command) {
        return parseString(command, 2);
    }

    @Override
    public boolean validate(String command) {
        if (validateAccountExists(getFromId(command)) && validateAccountExists(getToId(command))) {
            if (validateTransferLength(command)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean validateTransferLength(String command) {
        String[] arrStr = command.split(" ", 0);
        if (arrStr.length == 4) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateAccountExists(String quickId) {
        return bank.accountExistsByQuickID(quickId);
    }
}
