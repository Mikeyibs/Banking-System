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
    public List<String> idList;
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

    public List<String> getValidCommands() {
        return commands.getValidCommands();
    }

    public List<String> getInvalidCommands() {
        return commands.getInvalidCommands();
    }

    public String getType() {
        return this.type;
    }

    public List<String> Output() {
        outputList = new ArrayList<>();
        List<String> tempCommands = getValidCommands();

        for (int i = 0; i < tempCommands.size(); i++) {
            String command = tempCommands.get(i);
            List<String> parsedStrings = parseString(command);
            setAction(parsedStrings);
            parseID(parsedStrings);
        }

        List<String> validCommands = getValidCommands();
        for (int j = 0; j < validCommands.size(); j++) {
            String cmd = validCommands.get(j);
            List<String> parseStrings = parseString(cmd);
            setAction(parseStrings);
            addCommandsToOutputList(parseStrings, outputList, cmd);
        }

        if (!commands.getInvalidCommands().isEmpty()) {
            outputList.addAll(getInvalidCommands());
        }
        return outputList;
    }

    private void parseID(List<String> parsedStrings) {
        String tempID;
        if (getAction().equals("create")) {
            tempID = parsedStrings.get(2);
            if (!bank.accountExistsByQuickID(tempID)) {
                getValidCommands().removeIf(s -> (s.contains(tempID) && !s.toLowerCase().contains("transfer")));
            }
        }
    }

    private void addCommandsToOutputList(List<String> parsedStrings, List<String> outputList, String command) {
        switch (getAction()) {
            case "create":
                addAccountInformation(parsedStrings, outputList);
                break;
            case "deposit":
                outputList.add(command);
                break;
            case "withdraw":
                outputList.add(command);
                break;
            case "transfer":
                outputList.add(command);
                break;
            case "pass":
                break;
            default:
                break;
        }
    }

    public void setParameters(List<String> parsedStrings) {
        this.type = parsedStrings.get(1);
        this.id = parsedStrings.get(2);
        this.apr = bank.getAccounts().get(getId()).getApr();
        this.money = bank.getAccounts().get(getId()).getMoney();
    }

    private void addAccountInformation(List<String> parsedStrings, List<String> outputList) {
        try {
            setParameters(parsedStrings);
            String accountInfomation = String.format("%s %s %s %s", capitalize(getType()), getId(), getMoney(), getApr());
            outputList.add(accountInfomation);
        } catch (NullPointerException ignored) {

        }
    }

    public String formatNumber(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        return decimalFormat.format(value);
    }

    public String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public List<String> parseString(String i) {
        return Arrays.asList(i.toLowerCase().trim().split(" "));
    }
}
