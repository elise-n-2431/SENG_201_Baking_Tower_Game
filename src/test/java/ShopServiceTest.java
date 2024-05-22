import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team48.models.Tower;
import seng201.team48.services.ShopService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
