import java.util.ArrayList;
import java.util.List;

public class CommandStorage {

    List<String> invalidCommands = new ArrayList<String>();

    public CommandStorage() {
        invalidCommands = new ArrayList<>();
    }

    public void storeInvalidCommands(String command) {
        invalidCommands.add(command);
    }

    public List<String> getInvalidCommands() {
        return this.invalidCommands;
    }

}
