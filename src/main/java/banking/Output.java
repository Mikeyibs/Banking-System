package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Output {
    private final Bank bank;
    private final CommandStorage commands;
    public List<String> outputList;
    private String id;
    private double money;
    private double apr;
    private String action;
    private String type;

    public Output(Bank bank, CommandStorage commands) {
        this.bank = bank;
        this.commands = commands;
    }

    public String getId() {
        return this.id;
    }

    public String getMoney() {
        return formatNumber(this.money);
    }

    public String getApr() {
        return formatNumber(this.apr);
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(List<String> parsedStrings) {
        this.action = parsedStrings.get(0);
    }

    public String getType() {
        return this.type;
    }

    public void setType(List<String> parsedStrings) {
        this.type = parsedStrings.get(1);
    }

    public void setID(List<String> parsedStrings) {
        this.id = parsedStrings.get(2);
    }

    public void setMoney() {
        this.money = bank.getAccounts().get(id).getMoney();
    }

    public void setApr() {
        this.apr = bank.getAccounts().get(id).getApr();
    }

    public List<String> Output() {
        outputList = new ArrayList<>();
        for (int i = 0; i < commands.getValidCommands().size(); i++) {
            String command = commands.getValidCommands().get(i);
            List<String> parsedStrings = parseString(command);
            setAction(parsedStrings);
            addCommandsToOutputList(parsedStrings, outputList, command);
        }
        if (!commands.getInvalidCommands().isEmpty()) {
            for (int j = 0; j < commands.invalidCommands.size(); j++) {
                outputList.add(commands.invalidCommands.get(j));
            }
        }
        return outputList;
    }

    private void addCommandsToOutputList(List<String> parsedStrings, List<String> outputList, String command) {
        if (getAction() == "create") {
            addAccountInformation(command, parsedStrings, outputList);
        } else if (getAction() == "deposit") {
            outputList.add(command);
        } else if (getAction() == "withdraw") {
            outputList.add(command);
        } else if (getAction() == "transfer") {
            outputList.add(command);
        }
    }

    private void addAccountInformation(String command, List<String> parsedStrings, List<String> outputList) {
        setID(parsedStrings);
        setType(parsedStrings);
        setApr();
        setMoney();

        String accountInfomation = String.format("%s %s %s %s", getType(), getId(), getMoney(), getApr());
        outputList.add(accountInfomation);
    }

    private String formatNumber(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        return decimalFormat.format(value);
    }

    public String caseConversion(String command) {
        return command.toLowerCase();
    }


    public List<String> parseString(String i) {
        return Arrays.asList(i.toLowerCase().trim().split(" "));
    }
}
