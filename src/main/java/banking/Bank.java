package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private final Map<String, Account> accounts;
    private final CommandStorage commands;

    Bank(CommandStorage commands) {
        accounts = new HashMap<>();
        this.commands = commands;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public CommandStorage getCommands() {
        return this.commands;
    }

    public void addAccount(String quickId, Account account) {
        accounts.put(quickId, account);
    }

    public void removeAccount(String quickId) {
        accounts.remove(quickId);
    }

    public void withdraw(String quickId, double amount) {
        accounts.get(quickId).withdraw(amount);
    }

    public void deposit(String quickId, double amount) {
        accounts.get(quickId).deposit(amount);
    }

    public boolean accountExistsByQuickID(String quickId) {
        if (accounts.get(quickId) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void passTime(int months) {
        List<String> removedAccounts = new ArrayList<>();

        getAccounts().forEach((id, account) ->
        {
            for (int i = 0; i < months; i++) {
                double money = account.getMoney();
                if (money == 0.0) {
                    removedAccounts.add(id);
                } else {
                    if (money < 100) {
                        withdraw(id, 25);
                    }
                    account.passTime();
                }
            }
        });

        removedAccounts.forEach(id -> removeAccount(id));
    }
}
