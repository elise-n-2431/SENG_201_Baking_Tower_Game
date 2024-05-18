package seng201.team48.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team48.models.Tower;
import seng201.team48.services.TowerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TowerServiceTest {
    private TowerService testTowerService;
    private Tower[] towersList;
    Tower flourTower;
    Tower waterTower;
    Tower sugarTower;

    @BeforeEach
    public void setupTest() {
        testTowerService = new TowerService();
        towersList = new Tower[3];

        flourTower = new Tower("Flour Mill", "Creates flour", 5, 3, "Flour", 10, 1);
        waterTower = new Tower("Water Tower", "Stores water", 5, 3, "Water", 20, 2);
        sugarTower = new Tower("Sugarcane Mill", "Processes sugar", 5, 3, "Sugar", 5, 2);
    }

    @Test
    void passCase() {
        towersList.equals(List.of(flourTower, waterTower, sugarTower));
        assertTrue(testTowerService.areAllTowersSelected(towersList));
    }

    @Test
    void failCase() {
        towersList.equals(List.of(flourTower, waterTower));
        assertFalse(testTowerService.areAllTowersSelected(towersList));
    }
}
