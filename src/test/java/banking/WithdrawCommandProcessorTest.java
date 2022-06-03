package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawCommandProcessorTest {
    WithdrawCommandProcessor withdraw;
    CreateCommandProcessor create;
    DepositCommandProcessor deposit;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        create = new CreateCommandProcessor(bank);
        deposit = new DepositCommandProcessor(bank);
        withdraw = new WithdrawCommandProcessor(bank);
    }

    @Test
    void valid_withdraw_from_checking() {
        create.createCheckingAccount("12345678", 0.06);
        deposit.deposit("12345678", 1000);
        withdraw.processor("withdraw 12345678 500");

        Assertions.assertEquals(500, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void valid_withdraw_twice_from_checking() {
        create.createCheckingAccount("12345678", 0.06);
        deposit.deposit("12345678", 1000);
        withdraw.processor("withdraw 12345678 500");
        withdraw.processor("withdraw 12345678 250");

        Assertions.assertEquals(250, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void valid_withdraw_decimal_amount_from_checking() {
        create.createCheckingAccount("12345678", 0.06);
        deposit.deposit("12345678", 1000);
        withdraw.processor("withdraw 12345678 500.50");

        Assertions.assertEquals(499.50, bank.getAccounts().get("12345678").getMoney());
    }

    @Test
    void valid_withdraw_from_savings() {
        create.createSavingsAccount("33445566", 0.06);
        deposit.deposit("33445566", 1000);
        withdraw.processor("withdraw 33445566 500");

        Assertions.assertEquals(500, bank.getAccounts().get("33445566").getMoney());
    }

    @Test
    void valid_withdraw_twice_from_savings() {
        create.createSavingsAccount("33445566", 1.0);
        deposit.deposit("33445566", 1000);
        withdraw.processor("withdraw 33445566 500");
        bank.passTime(1);
        withdraw.processor("withdraw 33445566 250");

        Assertions.assertEquals(250.41666666666669, bank.getAccounts().get("33445566").getMoney());
    }

    @Test
    void valid_withdraw_decimal_amount_from_savings() {
        create.createSavingsAccount("33445566", 0.06);
        deposit.deposit("33445566", 1000);
        withdraw.processor("withdraw 33445566 500.50");

        Assertions.assertEquals(499.50, bank.getAccounts().get("33445566").getMoney());
    }

    @Test
    void valid_withdraw_after_12_months_from_cd() {
        create.createCDAccount("12345678", 1.2, 1100);
        bank.passTime(12);
        withdraw.processor("withdraw 12345678 500");

        Assertions.assertEquals(600, bank.getAccounts().get("12345678").getMoney());
    }
}