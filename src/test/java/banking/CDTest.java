package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CDTest {
    public static final double APR = (0.06);
    public static final double MONEY = (2000);
    Account cd;

    @BeforeEach
    void set_up() {
        cd = new CD(APR, MONEY);
    }

    @Test
    void account_has_apr() {
        assertEquals(APR, cd.getApr());
    }

    @Test
    void account_has_money() {
        assertEquals(MONEY, cd.getMoney());
    }

    @Test
    void validate_withdraw_returns_correct_value_if_given_correct_amount() {
        String money = "2000";
        Boolean test = cd.validateWithdrawAmount(money);
        assertEquals(true, test);
    }

    @Test
    void validate_withdraw_returns_correct_value_if_given_incorrect_amount() {
        String money = "900";
        Boolean test = cd.validateWithdrawAmount(money);
        assertEquals(false, test);
    }
}
