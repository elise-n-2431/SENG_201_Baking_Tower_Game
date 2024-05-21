
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team48.exceptions.NegativeBankException;
import seng201.team48.models.Inventory;

public class InventoryTest {
    static Inventory testInventory;

    /*
    * Initialise a testInventory object before all tests
    * */
    @BeforeEach
    void setUp() {
        testInventory = new Inventory(5);
    }

    @Test
    void constructionTest() {
        assertEquals(5, testInventory.getBankBalance());
    }

    /* Expected input */
    @Test
    void increaseBalanceTest() {
        testInventory.increaseBank(1);
        assertEquals(6, testInventory.getBankBalance());
    }

    /* Test decreaseBank throws a negativeBankException if amount to be subtracted is greater than current balance. */
    @Test
    void decreaseBalanceExceptionTest() {
        boolean throwsError = false;
        try {
            testInventory.decreaseBank(6);
        } catch (NegativeBankException e) {
            throwsError = true;
        }
        assertTrue(throwsError);
    }

    @Test
    void decreaseBalanceTest() throws NegativeBankException {
        testInventory.decreaseBank(3);
        assertEquals(2, testInventory.getBankBalance());
    }

}
