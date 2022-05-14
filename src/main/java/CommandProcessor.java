public class CommandProcessor {
    public Bank bank;
    CreateCommandProcessor createProcessor;
    DepositCommandProcessor depositProcessor;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processor(String command) {
        switch (parseString(command, 0)) {
            case "create":
                processCreate(command);
                break;
            case "deposit":
                processDeposit(command);
                break;
            default:
                break;
        }
    }

    public void processCreate(String command) {
        createProcessor = new CreateCommandProcessor(bank);

        createProcessor.processor(command);
    }

    public void processDeposit(String command) {
        depositProcessor = new DepositCommandProcessor(bank);

        depositProcessor.processor(command);
    }

    public String parseString(String command, int limit) {

        String[] arrStr = command.split(" ", 0);

        return arrStr[limit];
    }

    public String getType(String command) {
        return parseString(command, 1);
    }

    public String getID(String command) {
        return parseString(command, 2);
    }

    public Double getAPR(String command) {
        return Double.valueOf(parseString(command, 3));
    }

    public Double getAmount(String command) {
        return Double.valueOf(parseString(command, 4));
    }
}

