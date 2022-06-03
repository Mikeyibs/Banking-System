package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private final Map<String, Account> accounts;
    CommandStorage commands;

    Bank() {
        accounts = new HashMap<>();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
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

        for (Map.Entry i : accounts.entrySet()) {
            String quickId = (String) i.getKey();
            Account acct = (Account) i.getValue();

            for (int j = 0; j < months; j++) {
                double money = acct.getMoney();
                if (money == 0) {
                    removedAccounts.add(quickId);
                } else {
                    if (money < 100) {
                        withdraw(quickId, 25);
                    }
                    acct.passTime();
                }
            }
        }

        for (int y = 0; y < removedAccounts.size(); y++) {
            removeAccount(removedAccounts.get(y));
            commands.removeCommands(removedAccounts.get(y));
        }
    }
}
