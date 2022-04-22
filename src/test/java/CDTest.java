// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CDTest {
    public static final int ID = (12345678);
    public static final double APR = (0.06);
    public static final int MONEY = (2000);
    CD cd;

    @BeforeEach
    void set_up() {
        cd = new CD(ID, APR, MONEY);
    }

    @Test
    void account_has_id() {
        assertEquals(ID, cd.getId());
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
