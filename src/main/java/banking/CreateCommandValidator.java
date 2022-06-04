package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.List;
import java.util.Objects;

public class CreateCommandValidator extends CommandValidator {
    public static final double MIN_AMOUNT = (1000);
    public static final double MAX_AMOUNT = (10000);
    private String type;
    private String id;
    private String apr;
    private String amount;


    public CreateCommandValidator(Bank bank) {
        super(bank);
    }

    public String getType() {
        return this.type;
    }

    public void setType(List<String> commands) {
        this.type = commands.get(1);
    }

    public String getID() {
        return this.id;
    }

    public String getAPR() {
        return this.apr;
    }

    public String getAmount() {
        return this.amount;
    }

    public boolean countCommands(List<String> commands) {
        return validateLength(commands);
    }

    @Override
    public boolean validate(String command) {
        List<String> commands = parseString(command);
        try {
            setType(commands);
            setVariables(commands);
            return (validateType(getType()) && validateIDExistsInBank(getID()) && validateAPR(getAPR()) && validateLength(commands)
                    && validateAmount(getAmount()) && countCommands(commands));
        } catch (ArrayIndexOutOfBoundsException exc) {
            return false;
        }
    }

    private boolean validateType(String type) {
        if (Objects.equals(type, "checking") || Objects.equals(type, "savings") || Objects.equals(type, "cd")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateAmount(String amount) {
        if (getType().equals("checking") || getType().equals("savings")) {
            return true;
        } else {
            if (Double.parseDouble(getAmount()) >= 1000 && Double.parseDouble(getAmount()) <= 10000) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean validateLength(List<String> commands) {
        if (getType().equals("checking")) {
            if (commands.size() == 4) {
                return true;
            } else {
                return false;
            }
        } else if (getType().equals("savings")) {
            if (commands.size() == 4) {
                return true;
            } else {
                return false;
            }
        } else if (getType().equals("cd")) {
            if (commands.size() == 5) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void setVariables(List<String> commands) {
        this.id = commands.get(2);
        this.type = commands.get(1);
        this.apr = commands.get(3);

        if (getType().equals("cd")) {
            this.amount = commands.get(4);
        } else {
            this.amount = "0";
        }
    }

    public boolean validateIDExistsInBank(String quickID) {
        if (bank.accountExistsByQuickID(quickID)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateAPR(String apr) {
        if (isNum(apr) && isAPRInRange(apr)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAPRInRange(String apr) {
        double dbl = Double.parseDouble(apr);
        if (dbl >= 0 && dbl <= 10) {
            return true;
        } else {
            return false;
        }
    }
}
