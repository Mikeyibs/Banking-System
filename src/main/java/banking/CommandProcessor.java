package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

public class CommandProcessor {
    public Bank bank;
    CreateCommandProcessor createProcessor;
    DepositCommandProcessor depositProcessor;
    WithdrawCommandProcessor withdrawProcessor;
    PassTimeCommandProcessor passTimeProcessor;
    TransferCommandProcessor transferProcessor;

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
            case "withdraw":
                processWithdraw(command);
            case "pass":
                processPassTime(command);
            case "transfer":
                processTransfer(command);
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

    public void processWithdraw(String command) {
        withdrawProcessor = new WithdrawCommandProcessor(bank);

        withdrawProcessor.processor(command);
    }

    public void processPassTime(String command) {
        passTimeProcessor = new PassTimeCommandProcessor(bank);

        passTimeProcessor.processor(command);
    }

    public void processTransfer(String command) {
        transferProcessor = new TransferCommandProcessor(bank);

        transferProcessor.processor(command);
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

