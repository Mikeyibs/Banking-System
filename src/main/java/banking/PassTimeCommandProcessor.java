package banking;

import java.util.List;

public class PassTimeCommandProcessor extends CommandProcessor {
    int month;

    public PassTimeCommandProcessor(Bank bank) {
        super(bank);
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(List<String> commands) {
        this.month = Integer.parseInt(commands.get(1));
    }

    @Override
    public void processor(String command) {
        List<String> commands = parseString(command);
        setMonth(commands);
        bank.passTime(month);
    }
}
