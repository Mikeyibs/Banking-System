package banking;

public class PassTimeCommandProcessor extends CommandProcessor {

    public PassTimeCommandProcessor(Bank bank) {
        super(bank);
    }

    public int getMonth(String command) {
        return Integer.valueOf(parseString(command, 1));
    }

    @Override
    public void processor(String command) {
        int month = getMonth(command);
        passTime(month);
    }

    public void passTime(int month) {
        bank.passTime(month);
    }
}
