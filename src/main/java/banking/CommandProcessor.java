package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.Arrays;
import java.util.List;

public class CommandProcessor {
    public Bank bank;
    public String action;
    CreateCommandProcessor createProcessor;
    DepositCommandProcessor depositProcessor;
    WithdrawCommandProcessor withdrawProcessor;
    PassTimeCommandProcessor passTimeProcessor;
    TransferCommandProcessor transferProcessor;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(List<String> parseString) {
        this.action = parseString.get(0);
    }

    public void processor(String command) {
        List<String> commands = parseString(command);
        setAction(commands);
        switch (getAction()) {
            case "create":
                processCreate(command);
                break;
            case "deposit":
                processDeposit(command);
                break;
            case "withdraw":
                processWithdraw(command);
                break;
            case "pass":
                processPassTime(command);
                break;
            case "transfer":
                processTransfer(command);
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

    public List<String> parseString(String command) {
        return Arrays.asList(command.toLowerCase().trim().split(" "));
    }
}

