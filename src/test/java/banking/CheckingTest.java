package banking;// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingTest {
    public static final double APR = (0.06);
    public static final double ZEROMONEY = (0.0);
    Account checking;

    @BeforeEach
    void set_up() {
        checking = new Checking(APR, ZEROMONEY);
    }

    @Test
    void account_has_apr() {
        assertEquals(APR, checking.getApr());
    }

    @Test
    void account_has_zero_money() {
        assertEquals(ZEROMONEY, checking.getMoney());
    }
}
