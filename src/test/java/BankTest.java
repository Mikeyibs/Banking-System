// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {
    public static final int ID = (12345678);
    public static final double APR = (0.06);
    public static final double MONEY = (2000);
    public static final double MONEY1 = (4000);
    public static final double ZEROMONEY = (0);
    public static final String QUICK_ID = ("12345678");
    public static final String SECOND_QUICK_ID = ("23456789");

    Bank bank;
    Checking checking;
    Savings savings;
    CD cd;

    @BeforeEach
    void set_up() {
        bank = new Bank();
        checking = new Checking(APR, 0);
        savings = new Savings(APR, 0);
        cd = new CD(APR, MONEY);
    }

    @Test
    void bank_has_no_accounts_initially() {
        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    void add_checking_accounts_to_bank() {
        bank.addAccount(QUICK_ID, checking);

        assertEquals(checking, bank.getAccounts().get(QUICK_ID));
    }

    @Test
    void add_two_checking_account_to_bank() {
        bank.addAccount(QUICK_ID, checking);
        bank.addAccount(SECOND_QUICK_ID, checking);

        assertEquals(checking, bank.getAccounts().get(QUICK_ID));
        assertEquals(checking, bank.getAccounts().get(SECOND_QUICK_ID));
    }

    @Test
    void add_savings_accounts_to_bank() {
        bank.addAccount(QUICK_ID, savings);

        assertEquals(savings, bank.getAccounts().get(QUICK_ID));
    }

    @Test
    void add_two_savings_accounts_to_bank() {
        bank.addAccount(QUICK_ID, savings);
        bank.addAccount(SECOND_QUICK_ID, savings);

        assertEquals(savings, bank.getAccounts().get(QUICK_ID));
        assertEquals(savings, bank.getAccounts().get(SECOND_QUICK_ID));
    }

    @Test
    void add_cd_accounts_to_bank() {
        bank.addAccount(QUICK_ID, cd);

        assertEquals(cd, bank.getAccounts().get(QUICK_ID));
    }

    @Test
    void add_two_cd_accounts_to_bank() {
        bank.addAccount(QUICK_ID, cd);
        bank.addAccount(SECOND_QUICK_ID, cd);

        assertEquals(cd, bank.getAccounts().get(QUICK_ID));
        assertEquals(cd, bank.getAccounts().get(SECOND_QUICK_ID));
    }

    @Test
    void deposit_money_to_checking_account() {
        bank.addAccount(QUICK_ID, checking);

        bank.deposit(QUICK_ID, MONEY);

        Account actual = bank.getAccounts().get(QUICK_ID);
        assertEquals(MONEY, actual.getMoney());
    }

    @Test
    void deposit_money_twice_into_checking_account() {
        bank.addAccount(QUICK_ID, checking);

        bank.deposit(QUICK_ID, MONEY);
        bank.deposit(QUICK_ID, MONEY);

        Account actual = bank.getAccounts().get(QUICK_ID);

        assertEquals(4000, actual.getMoney());
    }

    @Test
    void deposit_money_to_savings_account() {
        bank.addAccount(QUICK_ID, savings);

        bank.deposit(QUICK_ID, MONEY);

        Account actual = bank.getAccounts().get(QUICK_ID);
        assertEquals(MONEY, actual.getMoney());
    }

    @Test
    void deposit_money_twice_into_savings_account() {
        bank.addAccount(QUICK_ID, savings);

        bank.deposit(QUICK_ID, MONEY);
        bank.deposit(QUICK_ID, MONEY);

        Account actual = bank.getAccounts().get(QUICK_ID);

        assertEquals(4000, actual.getMoney());
    }

    @Test
    void withdraw_all_money_from_checking_account() {
        bank.addAccount(QUICK_ID, checking);

        bank.deposit(QUICK_ID, MONEY);
        bank.withdraw(QUICK_ID, MONEY);

        Account actual = bank.getAccounts().get(QUICK_ID);
        assertTrue(actual.getMoney() == 0);
    }

    @Test
    void withdraw_money_twice_from_checking_account() {
        bank.addAccount(QUICK_ID, checking);

        bank.deposit(QUICK_ID, 4000);
        bank.withdraw(QUICK_ID, MONEY);
        bank.withdraw(QUICK_ID, MONEY);

        Account actual = bank.getAccounts().get(QUICK_ID);
        assertTrue(actual.getMoney() == 0);
    }

    @Test
    void withdraw_over_balance_checking() {
        bank.addAccount(QUICK_ID, checking);

        bank.deposit(QUICK_ID, 4000);
        bank.withdraw(QUICK_ID, 6100);

        Account actual = bank.getAccounts().get(QUICK_ID);
        assertTrue(actual.getMoney() == 0);
    }

    @Test
    void withdraw_all_money_from_savings_account() {
        bank.addAccount(QUICK_ID, savings);

        bank.deposit(QUICK_ID, MONEY);
        bank.withdraw(QUICK_ID, MONEY);

        Account actual = bank.getAccounts().get(QUICK_ID);
        assertTrue(actual.getMoney() == 0);
    }

    @Test
    void withdraw_money_twice_from_savings_account() {
        bank.addAccount(QUICK_ID, savings);

        bank.deposit(QUICK_ID, 4000);
        bank.withdraw(QUICK_ID, MONEY);
        bank.withdraw(QUICK_ID, MONEY);

        Account actual = bank.getAccounts().get(QUICK_ID);
        assertTrue(actual.getMoney() == 0);
    }

    @Test
    void withdraw_over_balance_savings() {
        bank.addAccount(QUICK_ID, savings);

        bank.deposit(QUICK_ID, 4000);
        bank.withdraw(QUICK_ID, 6100);

        Account actual = bank.getAccounts().get(QUICK_ID);
        assertTrue(actual.getMoney() == 0);
    }

    @Test
    void withdraw_all_money_from_cd_account() {
        bank.addAccount(QUICK_ID, cd);

        bank.deposit(QUICK_ID, MONEY);
        bank.withdraw(QUICK_ID, 4000);

        Account actual = bank.getAccounts().get(QUICK_ID);
        assertTrue(actual.getMoney() == 0);
    }

    @Test
    void withdraw_money_twice_from_cd_account() {
        bank.addAccount(QUICK_ID, cd);

        bank.deposit(QUICK_ID, 4000);
        bank.withdraw(QUICK_ID, MONEY);
        bank.withdraw(QUICK_ID, 4000);

        Account actual = bank.getAccounts().get(QUICK_ID);
        assertTrue(actual.getMoney() == 0);
    }

    @Test
    void withdraw_over_balance_cd() {
        bank.addAccount(QUICK_ID, cd);

        bank.deposit(QUICK_ID, 4000);
        bank.withdraw(QUICK_ID, 6100);

        Account actual = bank.getAccounts().get(QUICK_ID);
        assertTrue(actual.getMoney() == 0);
    }
}
