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
}
