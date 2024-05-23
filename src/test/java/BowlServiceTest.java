import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team48.TowerManager;
import seng201.team48.models.Bowl;
import seng201.team48.services.BowlService;

import static org.junit.jupiter.api.Assertions.*;

public class BowlServiceTest {
    private BowlService testBowlService;
    @BeforeEach
    public void init(){
        testBowlService = new BowlService();
    }
    @Test
    public void numBowlsFromEmpty(){
        assertEquals(0, testBowlService.getNumBowlsSelected());
    }
    @Test
    public void testSetNumBowlsSelected(){
        testBowlService.setNumBowlsSelected(4, 2);
        assertEquals(6, testBowlService.getNumBowlsSelected());
    }
    @Test
    public void getNewBowlSmall(){
        testBowlService.setNumBowlsSelected(2, 2);
        Bowl testBowl = testBowlService.getNewBowl();
        assertNull(testBowl.getFilled().get(0));
        assertNull(testBowl.getFilled().get(1));
        assertNull(testBowl.getFilled().get(2));
    }
    @Test
    public void getNewBowlLarge(){
        testBowlService.setNumBowlsSelected(0, 2);
        Bowl testBowl = testBowlService.getNewBowl();
        assertNull(testBowl.getFilled().get(0));
        assertNull(testBowl.getFilled().get(1));
        assertNull(testBowl.getFilled().get(2));
        assertNull(testBowl.getFilled().get(3));
        assertNull(testBowl.getFilled().get(4));
    }
    @Test
    public void getBowlsUsedSmallFalse(){
        testBowlService.setNumBowlsSelected(6, 0);
        assertFalse(testBowlService.getBowlsUsed());
    }
    @Test
    public void getBowlsUsedLargeFalse(){
        testBowlService.setNumBowlsSelected(0, 2);
        assertFalse(testBowlService.getBowlsUsed());
    }
    @Test
    public void getBowlsUsedTrue(){
        testBowlService.setNumBowlsSelected(0, 0);
        assertTrue(testBowlService.getBowlsUsed());
    }
    @Test
    public void getNumBowlsSent(){
        assertEquals(0, testBowlService.getNumBowlsSent());
    }




}
