package seng201.team48.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team48.models.Tower;
import static org.junit.jupiter.api.Assertions.*;

public class TowerTest {
    @Test
    void passCase() {
        Tower tower = new Tower("Water", "Used in nothing", 6, 4, 1, 0.4);
        String expectedPrint = "Tower{description: Used in nothing, purchasePrice: 6, sellPrice: 4, resourceType: 0.4, resourceAmount: 1, reloadSpeed: 0.4}";
        assertEquals(tower.toString(), expectedPrint);
    }

}
