import org.junit.jupiter.api.Test;
import seng201.team48.models.Upgrade;

import static org.junit.jupiter.api.Assertions.*;

public class UpgradeTest {
    static Upgrade upgrade;

    @Test
    void level1PurchasePriceTest() {
        upgrade = new Upgrade("1");
        System.out.println(upgrade.getPurchasePrice());
        String expectedPrint = "Upgrades a level 1 station to level 2 station. Price: 20";
        assertEquals(expectedPrint, upgrade.toString());
    }

    @Test
    void level2PurchasePriceTest() {
        upgrade = new Upgrade("2");
        System.out.println(upgrade.getPurchasePrice());
        String expectedPrint = "Upgrades a level 2 station to level 3 station. Price: 30";
        assertEquals(expectedPrint, upgrade.toString());
    }

    @Test
    void level3PurchasePriceTest() {
        upgrade = new Upgrade("3");
        System.out.println(upgrade.getPurchasePrice());
        String expectedPrint = "Upgrades a level 3 station to level 4 station. Price: 40";
        assertEquals(expectedPrint, upgrade.toString());
    }

}
