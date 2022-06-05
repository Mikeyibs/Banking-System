package banking;

import java.util.List;

public class TransferCommandValidator extends CommandValidator {
    private String fromId;
    private String toId;
    private String amount;

    public TransferCommandValidator(Bank bank) {
        super(bank);
    }

    public String getFromId() {
        return this.fromId;
    }

    public String getToId() {
        return this.toId;
    }

    public String getAmount() {
        return this.amount;
    }

    @Override
    public boolean validate(String command) {
        List<String> commands = parseString(command);
        try {
            setVariables(commands);
            return (validateAccountExists(getFromId()) && validateAccountExists(getToId()) &&
                    validateTransferLength(command) &&
                    validateWithdrawAmount(getFromId(), getAmount()) &&
                    validateDepositAmount(getToId(), getAmount()));
        } catch (ArrayIndexOutOfBoundsException exc) {
            return false;
        }
    }

    public void setVariables(List<String> commands) {
        this.toId = commands.get(2);
        this.fromId = commands.get(1);
        this.amount = commands.get(3);
    }

    public boolean validateWithdrawAmount(String quickId, String amount) {
        if (isNum(amount) && accountCanWithdrawThisMonth(quickId)) {
            return bank.getAccounts().get(quickId).validateWithdrawAmount(amount);
        } else {
            return false;
        }
    }

    public boolean accountCanWithdrawThisMonth(String quickId) {
        if (bank.getAccounts().get(quickId).getWithdrawRestriction()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateDepositAmount(String quickId, String amount) {
        if (isNum(amount)) {
            return bank.getAccounts().get(quickId).validateDepositAmount(amount);
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
