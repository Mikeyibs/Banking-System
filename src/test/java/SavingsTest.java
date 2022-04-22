// Name: Michael Ibrahim | ID: mi374 | Section: 001

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsTest {
    public static final int ID = (12345678);
    public static final double APR = (0.06);
    public static final int ZEROMONEY = (0);
    Savings savings;

    @BeforeEach
    void set_up() {
        savings = new Savings(ID, APR, ZEROMONEY);
    }

    @Test
    void account_has_id() {
        assertEquals(ID, savings.getId());
    }

    @Test
    void account_has_apr() {
        assertEquals(APR, savings.getApr());
    }

    @Test
    void account_has_zero_money() {
        assertEquals(ZEROMONEY, savings.getMoney());
    }
}
