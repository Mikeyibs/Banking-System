// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {
    public static final int ID = (12345678);
    public static final double APR = (0.06);
    public static final int MONEY = (2000);
    public static final int MONEY1 = (4000);
    public static final int ZEROMONEY = (0);
    public static final String QUICK_ID = String.valueOf(ID);
    Bank bank;

    @BeforeEach
    void set_up() {
        bank = new Bank();
    }

    @Test
    void bank_has_no_accounts_initially() {
        assertTrue(bank.getCheckingAccounts().isEmpty());
        assertTrue(bank.getSavingsAccounts().isEmpty());
        assertTrue(bank.getCdAccounts().isEmpty());
    }

    @Test
    void add_checking_accounts_to_bank() {
        bank.addCheckingAccounts(ID, APR, QUICK_ID, ZEROMONEY);
        assertEquals(ID, bank.getCheckingAccounts().get(QUICK_ID).getId());
    }

    @Test
    void add_savings_accounts_to_bank() {
        bank.addSavingsAccounts(ID, APR, QUICK_ID, ZEROMONEY);
        assertEquals(ID, bank.getSavingsAccounts().get(QUICK_ID).getId());
    }

    @Test
    void add_cd_accounts_to_bank() {
        bank.addCdAccounts(ID, APR, QUICK_ID, ZEROMONEY);
        assertEquals(ID, bank.getCdAccounts().get(QUICK_ID).getId());
    }

    @Test
    void deposit_money_to_checking_account() {
        bank.addCheckingAccounts(ID, APR, QUICK_ID, ZEROMONEY);
        bank.depositMoneyIntoChecking(QUICK_ID, MONEY);
        Checking actual = bank.getCheckingAccounts().get(QUICK_ID);
        assertEquals(MONEY, actual.getMoney());
    }

    @Test
    void deposit_money_to_savings_account() {
        bank.addSavingsAccounts(ID, APR, QUICK_ID, ZEROMONEY);
        bank.depositMoneyIntoSavings(QUICK_ID, MONEY);
        Savings actual = bank.getSavingsAccounts().get(QUICK_ID);
        assertEquals(MONEY, actual.getMoney());
    }

    @Test
    void withdraw_all_money_from_checking_account() {
        bank.addCheckingAccounts(ID, APR, QUICK_ID, ZEROMONEY);
        bank.depositMoneyIntoChecking(QUICK_ID, MONEY);
        bank.withdrawMoneyFromChecking(QUICK_ID, MONEY);
        Checking actual = bank.getCheckingAccounts().get(QUICK_ID);
        assertTrue(actual.getMoney() == 0);
    }

    @Test
    void withdraw_all_money_from_savings_account() {
        bank.addSavingsAccounts(ID, APR, QUICK_ID, ZEROMONEY);
        bank.depositMoneyIntoSavings(QUICK_ID, MONEY);
        bank.withdrawMoneyFromSavings(QUICK_ID, MONEY);
        Savings actual = bank.getSavingsAccounts().get(QUICK_ID);
        assertTrue(actual.getMoney() == 0);
    }

    @Test
    void withdraw_all_money_from_cd_account() {
        bank.addCdAccounts(ID, APR, QUICK_ID, MONEY);
        bank.withdrawMoneyFromCd(QUICK_ID, MONEY);
        CD actual = bank.getCdAccounts().get(QUICK_ID);
        assertTrue(actual.getMoney() == 0);
    }
}
