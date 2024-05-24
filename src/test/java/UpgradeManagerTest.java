import seng201.team48.UpgradeManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team48.models.Purchasable;
import seng201.team48.models.RepairKit;
import seng201.team48.models.Upgrade;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class UpgradeManagerTest {

    private UpgradeManager upgradeManager;

    @BeforeEach
    public void setUp() {
        upgradeManager = new UpgradeManager(new Random());
    }


    @Test
    public void testConstructorInitializesDefaultUpgradesList() {
        List<Purchasable> defaultUpgrades = upgradeManager.getDefaultUpgradesList();
        assertEquals(5, defaultUpgrades.size());
        assertTrue(defaultUpgrades.get(0) instanceof Upgrade);
        assertTrue(defaultUpgrades.get(4) instanceof RepairKit);
    }

    @Test
    public void testConstructorInitializesDefaultUpgradesImages() {
        List<String> defaultUpgradesImages = upgradeManager.getUpgradesForSaleImages();
        assertEquals(5, defaultUpgradesImages.size());
    }

    @Test
    public void testGenerateUpgradesForSale() {
        List<Purchasable> defaultUpgrades = upgradeManager.getDefaultUpgradesList();

        List<Purchasable> upgradesForSale = upgradeManager.generateUpgradesForSale();

        assertEquals(5, upgradesForSale.size());

        // Check if all elements in upgradesForSale are from defaultUpgrades
        Set<Purchasable> defaultUpgradesSet = defaultUpgrades.stream().collect(Collectors.toSet());
        for (Purchasable upgrade : upgradesForSale) {
            assertTrue(defaultUpgradesSet.contains(upgrade));
        }
    }

    @Test
    public void testAddPlayerUpgrade() {
        Purchasable upgrade = new Upgrade("1");
        upgradeManager.addPlayerUpgrade(upgrade);

        List<Purchasable> playerUpgrades = upgradeManager.getPlayerUpgrades();
        assertEquals(1, playerUpgrades.size());
        assertEquals(upgrade, playerUpgrades.get(0));
    }

    @Test
    public void testRemovePlayerUpgrade() {
        Purchasable upgrade = new Upgrade("1");
        upgradeManager.addPlayerUpgrade(upgrade);
        upgradeManager.removePlayerUpgrade(upgrade);

        List<Purchasable> playerUpgrades = upgradeManager.getPlayerUpgrades();
        assertEquals(0, playerUpgrades.size());
    }

    @Test
    public void testAddPlayerItemsImage() {
        String imagePath = "/images/Level1.png";
        upgradeManager.addPlayerItemsImage(imagePath);

        List<String> playerItemsImages = upgradeManager.getPlayerItemsImages();
        assertEquals(1, playerItemsImages.size());
        assertEquals(imagePath, playerItemsImages.get(0));
    }

    @Test
    public void testRemovePlayerItemsImage() {
        String imagePath = "/images/Level1.png";
        upgradeManager.addPlayerItemsImage(imagePath);
        upgradeManager.removePlayerItemsImage(0);

        List<String> playerItemsImages = upgradeManager.getPlayerItemsImages();
        assertEquals(0, playerItemsImages.size());
    }
}
