import org.junit.jupiter.api.Test;
import seng201.team48.models.Upgrade;

import static org.junit.jupiter.api.Assertions.*;

public class UpgradeTest {
    static Upgrade upgrade;

    @Test
    void level1PurchasePriceTest() {
        upgrade = new Upgrade("1");
        System.out.println(upgrade.getPurchasePrice());
        String expectedPrint = "Upgrade for level 1 tower. Price: 20";
        assertEquals(expectedPrint, upgrade.toString());
    }

    @Test
    void level2PurchasePriceTest() {
        upgrade = new Upgrade("2");
        System.out.println(upgrade.getPurchasePrice());
        String expectedPrint = "Upgrade for level 2 tower. Price: 30";
        assertEquals(expectedPrint, upgrade.toString());
    }

    @Test
    void level3PurchasePriceTest() {
        upgrade = new Upgrade("3");
        System.out.println(upgrade.getPurchasePrice());
        String expectedPrint = "Upgrade for level 3 tower. Price: 40";
        assertEquals(expectedPrint, upgrade.toString());
    }

}
