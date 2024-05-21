import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team48.models.RepairKit;
import seng201.team48.models.Tower;
import static org.junit.jupiter.api.Assertions.*;

public class TowerTest {
    static Tower tower;
    static RepairKit repairKit;

    @BeforeEach
    void setUp() {
        tower = new Tower("Water", "Used in nothing", 6, 4, 1, 0.4);
        repairKit = new RepairKit();
    }

    @Test
    void toStringTest() {
        String expectedPrint = "Tower{description: Used in nothing, purchasePrice: 6, sellPrice: 4, resourceType: Water, resourceAmount: 1, reloadSpeed: 0.4}";
        assertEquals(expectedPrint, tower.toString());
    }

    @Test
    void setLevelTest() {
        tower.setLevel(tower.getLevel() + 1);
        assertEquals(2, tower.getLevel());
    }

    @Test
    void repairTowerTest() {
        tower.setBroken(true);
        tower.addItem(repairKit);
        assertFalse(tower.isBroken());
    }

}
