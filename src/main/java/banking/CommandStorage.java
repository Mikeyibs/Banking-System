package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {

    List<String> invalidCommands = new ArrayList<String>();
    List<String> validCommands = new ArrayList<String>();

    public CommandStorage() {
        invalidCommands = new ArrayList<>();
        validCommands = new ArrayList<>();
    }

    public void storeInvalidCommands(String command) {
        invalidCommands.add(command);
    }

    public void storeValidCommands(String command) {
        validCommands.add(command);
    }

    public List<String> getValidCommands() {
        return this.validCommands;
    }

    public List<String> getInvalidCommands() {
        return this.invalidCommands;
    }

    public void removeCommands(String quickId) {
        for (int i = 0; i < validCommands.size(); i++) {
            if (validCommands.get(i).contains(quickId) && !validCommands.get(i).contains("transfer") && !validCommands.get(i).contains("Transfer")) {
                validCommands.remove(validCommands.get(i));
            }
        }
    }
}
