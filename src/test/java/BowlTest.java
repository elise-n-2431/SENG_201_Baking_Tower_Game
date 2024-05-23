import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team48.models.Bowl;
import seng201.team48.models.Tower;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BowlTest {
    private Bowl testBowl;
    private Tower testTower;
    @BeforeEach
    public void init(){
        List<Tower> testNewFilled = new ArrayList<Tower>(3);
        testNewFilled.add(null);
        testNewFilled.add(null);
        testNewFilled.add(null);
        testBowl = new Bowl(3, "Small", testNewFilled);
        testTower = new Tower("Water", "Used in nothing", 6, 4, 1, 0.4);
    }
    @Test
    void addTowerToBowl(){
        testBowl.addToBowl(testTower);
        assertEquals(testBowl.getFilled().get(0), testTower);
    }

    @Test
    void emptyBowl(){
        assertFalse(testBowl.getFullBowl());
    }
    @Test
    void fullbowl(){
        testBowl.addToBowl(testTower);
        testBowl.addToBowl(testTower);
        testBowl.addToBowl(testTower);
        assertTrue(testBowl.getFullBowl());

    }
    @Test
    void setEmptyFromEmpty(){
        testBowl.addToBowl(testTower);
        testBowl.addToBowl(testTower);
        testBowl.addToBowl(testTower);
        testBowl.setEmpty();
        assertNull(testBowl.getFilled().get(0));
    }
    @Test
    void setEmptyFromFull(){
        testBowl.setEmpty();
        assertNull(testBowl.getFilled().get(0));
        assertNull(testBowl.getFilled().get(1));
        assertNull(testBowl.getFilled().get(2));
    }
    @Test
    void getCapacity(){
        assertEquals(3, testBowl.getCapacity());
    }
    @Test
    void getSize(){
        assertEquals("Small", testBowl.getSize());
    }

}
