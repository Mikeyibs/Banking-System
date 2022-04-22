// Name: Michael Ibrahim | ID: mi374 | Section: 001

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Checking> checkingAccounts;
    private Map<String, Savings> savingsAccounts;
    private Map<String, CD> cdAccounts;

    Bank() {
        checkingAccounts = new HashMap<>();
        savingsAccounts = new HashMap<>();
        cdAccounts = new HashMap<>();
    }

    public Map<String, Checking> getCheckingAccounts() {
        return checkingAccounts;
    }

    public Map<String, Savings> getSavingsAccounts() {
        return savingsAccounts;
    }

    public Map<String, CD> getCdAccounts() {
        return cdAccounts;
    }

    public void addCheckingAccounts(int id, double apr, String QUICK_ID, int money) {
        checkingAccounts.put(QUICK_ID, new Checking(id, apr, money));
    }

    public void addSavingsAccounts(int id, double apr, String quickId, int money) {
        savingsAccounts.put(quickId, new Savings(id, apr, money));
    }

    public void addCdAccounts(int id, double apr, String quickId, int money) {
        cdAccounts.put(quickId, new CD(id, apr, money));
    }

    public void depositMoneyIntoChecking(String quickId, int money) {
        checkingAccounts.get(quickId).deposit(money);
    }

    public void depositMoneyIntoSavings(String quickId, int money) {
        savingsAccounts.get(quickId).deposit(money);
    }

    public void withdrawMoneyFromChecking(String quickId, int money) {
        checkingAccounts.get(quickId).withdraw(money);
    }

    public void withdrawMoneyFromSavings(String quickId, int money) {
        savingsAccounts.get(quickId).withdraw(money);
    }

    public void withdrawMoneyFromCd(String quickId, int money) {
        cdAccounts.get(quickId).withdraw(money);
    }
}
