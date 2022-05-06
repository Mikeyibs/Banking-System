// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    Bank() {
        accounts = new HashMap<>();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void addAccount(String quickId, Account account) {
        accounts.put(quickId, account);
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
}
