import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;
import seng201.team48.UpgradeManager;
import seng201.team48.models.Purchasable;
import seng201.team48.models.Tower;
import seng201.team48.models.Upgrade;
import seng201.team48.services.ShopService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShopServiceTest {
    private ShopService testShopService;
    List<Tower> playerTowers;
    List<Tower> reserveTowers;
    @BeforeEach
    public void setupTest() {
        // Use CounterService directly
        testShopService = new ShopService();
        playerTowers = new ArrayList<>();
        reserveTowers = new ArrayList<>();
    }

    @Test
    public void towerSlotFreeTest() {
        Tower eggTower = new Tower("Eggs", "Used in fried eggs, scrambled eggs, pasta, pancakes, carbonara, cake, banana bread", 5, 3, 20, 0.05);
        Tower milkTower = new Tower("Milk", "Used in pasta sauce, scrambled eggs, pancakes, carbonara, cake, and banana bread", 6, 3, 8, 0.035);
        Tower flourTower = new Tower("Flour", "Used in pasta, pasta sauce, pancakes, carbonara, cake, and banana bread", 5, 3, 10, 0.05);
        Tower bananaTower = new Tower("Banana", "Used in smoothie and banana bread", 8, 7, 8, 0.04);
        Tower sugarTower = new Tower("Sugar", "Used in cake and banana bread", 5, 3, 5, 0.02);
        playerTowers.addAll(List.of(flourTower, eggTower, sugarTower, milkTower, bananaTower));
        reserveTowers.addAll(List.of(flourTower, flourTower));
        assertTrue(testShopService.hasTowerInventorySpace(playerTowers, reserveTowers));
    }

    @Test
    public void towerListsFullTest() {
        Tower eggTower = new Tower("Eggs", "Used in fried eggs, scrambled eggs, pasta, pancakes, carbonara, cake, banana bread", 5, 3, 20, 0.05);
        Tower milkTower = new Tower("Milk", "Used in pasta sauce, scrambled eggs, pancakes, carbonara, cake, and banana bread", 6, 3, 8, 0.035);
        Tower flourTower = new Tower("Flour", "Used in pasta, pasta sauce, pancakes, carbonara, cake, and banana bread", 5, 3, 10, 0.05);
        Tower bananaTower = new Tower("Banana", "Used in smoothie and banana bread", 8, 7, 8, 0.04);
        Tower sugarTower = new Tower("Sugar", "Used in cake and banana bread", 5, 3, 5, 0.02);
        playerTowers.addAll(List.of(flourTower, eggTower, sugarTower, milkTower, bananaTower));
        reserveTowers.addAll(List.of(flourTower, flourTower, sugarTower, milkTower, bananaTower));
        assertFalse(testShopService.hasTowerInventorySpace(playerTowers, reserveTowers));
    }

    @Test
    public void testHasItemInventorySpace() {
        List<Purchasable> playerUpgrades = new ArrayList<>();

        Upgrade testUpgrade = new Upgrade("1");

        for (int i = 0; i < 4; i++) {
            playerUpgrades.add(testUpgrade.cloneSelf());
        }

        assertTrue(testShopService.hasItemInventorySpace(playerUpgrades));

        playerUpgrades.add(testUpgrade.cloneSelf());
        assertFalse(testShopService.hasItemInventorySpace(playerUpgrades));
    }

    @Test
    public void testCanPurchase() {
        assertTrue(testShopService.canPurchase(100, 50));
        assertFalse(testShopService.canPurchase(50, 100));
        assertTrue(testShopService.canPurchase(50, 50));
    }

    @Test
    public void testHasEnoughTowers() {
        List<Tower> playerTowers = new ArrayList<>();
        Tower eggTower = new Tower("Eggs", "Used in fried eggs, scrambled eggs, pasta, pancakes, carbonara, cake, banana bread", 5, 3, 20, 0.05);
        for (int i = 0; i < 4; i++) {
            playerTowers.add((Tower) eggTower.cloneSelf());
        }
        assertTrue(testShopService.hasEnoughTowers(playerTowers));
        playerTowers.remove(0);
        assertFalse(testShopService.hasEnoughTowers(playerTowers));
    }

    @Test
    public void testSellItemEnoughTowers() {
        // Arrange
        MainGameManager mainGameManager = mock(MainGameManager.class);
        TowerManager towerManager = mock(TowerManager.class);
        UpgradeManager upgradeManager = mock(UpgradeManager.class);

        when(mainGameManager.getTowerManager()).thenReturn(towerManager);
        when(mainGameManager.getUpgradeManager()).thenReturn(upgradeManager);

        when(towerManager.getPlayerTowers()).thenReturn(List.of(mock(Tower.class), mock(Tower.class), mock(Tower.class), mock(Tower.class), mock(Tower.class)));
        when(towerManager.getReserveTowers()).thenReturn(List.of());
        when(upgradeManager.getPlayerUpgrades()).thenReturn(List.of());

        ShopService shopService = new ShopService();

        String result = shopService.sellInvItem(mainGameManager, "active", 0, 0, 0);
        assertEquals("doRefresh", result);
    }

    @Test
    public void testSellItemWhenNotEnoughTowers() {
        MainGameManager mainGameManager = mock(MainGameManager.class);
        TowerManager towerManager = mock(TowerManager.class);
        UpgradeManager upgradeManager = mock(UpgradeManager.class);

        when(mainGameManager.getTowerManager()).thenReturn(towerManager);
        when(mainGameManager.getUpgradeManager()).thenReturn(upgradeManager);

        when(towerManager.getPlayerTowers()).thenReturn(List.of(mock(Tower.class), mock(Tower.class), mock(Tower.class)));
        when(towerManager.getReserveTowers()).thenReturn(List.of());
        when(upgradeManager.getPlayerUpgrades()).thenReturn(List.of());

        ShopService shopService = new ShopService();

        String result = shopService.sellInvItem(mainGameManager, "active", 0, 0, 0);

        assertEquals("showError", result);
    }

}
