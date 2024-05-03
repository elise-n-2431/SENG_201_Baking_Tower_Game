
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seng201.team0.exceptions.NegativeBankException;
import seng201.team0.models.Inventory;

public class InventoryTest {
    static Inventory testInventory;

    /*
    * Initialise a testInventory object before all tests
    * */
    @BeforeAll
    static void setUp() {
        testInventory = new Inventory(5);
    }

    /* Expected input */
    @Test
    void increaseBalanceTest() {
        testInventory.increaseBank(3);
        assertEquals(testInventory.getBankBalance(), 8);

    }

    /* Test decreaseBank throws a negativeBankException if amount to be subtracted is greater than current balance. */
    @Test
    void decreaseBalanceTest() {
        boolean throwsError = false;
        try {
            testInventory.decreaseBank(6);
        } catch (NegativeBankException e) {
            throwsError = true;
        }
        assertTrue(throwsError);
    }

}
