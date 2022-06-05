package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.List;

public class MasterControl {
    Bank bank;
    CommandValidator commandValidator;
    CommandProcessor commandProcessor;
    CommandStorage commandStorage;
    Output output;

    public MasterControl(Bank bank, CommandValidator commandValidator,
                         CommandProcessor commandProcessor, CommandStorage commandStorage) {
        this.bank = bank;
        this.commandValidator = commandValidator;
        this.commandProcessor = commandProcessor;
        this.commandStorage = commandStorage;
        this.output = new Output(bank, commandStorage);
    }

    public List<String> start(List<String> input) {
        for (String cmd : input) {
            if (commandValidator.validate(cmd)) {
                commandStorage.storeValidCommands(cmd);
                commandProcessor.processor(cmd);
            } else {
                commandStorage.storeInvalidCommands(cmd);
            }
        }
        return output.Output();
    }
}
