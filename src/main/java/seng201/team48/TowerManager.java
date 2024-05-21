package seng201.team48;

import seng201.team48.models.Tower;

import java.util.ArrayList;
import java.util.List;

public class TowerManager {
    //    private final Consumer<TowerManager> setupScreenLauncher;
//    private final Consumer<TowerManager> mainScreenLauncher;
//    private final Runnable clearScreen;
    private final List<Tower> defaultTowers = new ArrayList<>();
    private List<Tower> playerTowers;

    /**
     * Constructor
     */
    public TowerManager() {
        Tower eggTower = new Tower("Eggs", "Eggs", 5, 3, "Eggs", 20, 0.2);
        Tower milkTower = new Tower("Milk", "Creates butter, milk and cream", 6, 3, "Milk", 8, 0.15);
        Tower flourTower = new Tower("Flour", "Creates flour", 5, 3, "Flour", 10, 0.1);
        Tower bananaTower = new Tower("Banana", "is a tree", 7, 8, "Banana", 7, 0.04);
        Tower sugarTower = new Tower("Sugar", "Processes sugar", 5, 3, "Sugar", 5, 0.02);
        defaultTowers.addAll(List.of(flourTower, eggTower, sugarTower, milkTower, bananaTower));
    }

    public List<Tower> getDefaultTowers() {
        return defaultTowers;
    }

    public List<Tower> getPlayerTowers() {
        return playerTowers;
    }

    public void setPlayerTowers(List<Tower> playerTowers) {
        this.playerTowers = playerTowers;
    }

}
