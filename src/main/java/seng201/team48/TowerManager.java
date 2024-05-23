package seng201.team48;

import seng201.team48.models.Tower;

import java.util.ArrayList;
import java.util.List;

public class TowerManager {
    //    private final Consumer<TowerManager> setupScreenLauncher;
//    private final Consumer<TowerManager> mainScreenLauncher;
//    private final Runnable clearScreen;
    private final List<Tower> defaultTowers = new ArrayList<>();
    private List<String> defaultTowersImages = new ArrayList<>();
    private List<String> playerTowersImages = new ArrayList<>();
    private List<Tower> playerTowers;
    private List<Tower> reserveTowers;


    /**
     * Constructor
     */
    public TowerManager() {
        Tower flourTower = new Tower("Flour", "Used in pasta, pasta sauce, pancakes, carbonara, cake, and banana bread", 100, 3, 10, 0.05);
        Tower eggTower = new Tower("Eggs", "Used in fried eggs, scrambled eggs, pasta, pancakes, carbonara, cake, banana bread", 100, 3, 20, 0.05);
        Tower sugarTower = new Tower("Sugar", "Used in cake and banana bread", 100, 3, 5, 0.02);
        Tower milkTower = new Tower("Milk", "Used in pasta sauce, scrambled eggs, pancakes, carbonara, cake, and banana bread", 100, 3, 8, 0.035);
        Tower bananaTower = new Tower("Banana", "Used in smoothie and banana bread", 150, 7, 8, 0.04);
        defaultTowers.addAll(List.of(flourTower, eggTower, sugarTower, milkTower, bananaTower));

        String flourImagePath = getClass().getResource("/images/FlourButton.png").toExternalForm();
        String eggImagePath = getClass().getResource("/images/EggsButton.png").toExternalForm();
        String sugarImagePath = getClass().getResource("/images/SugarButton.png").toExternalForm();
        String milkImagePath = getClass().getResource("/images/MilkButton.png").toExternalForm();
        String bananaImagePath = getClass().getResource("/images/BananaButton.png").toExternalForm();
        defaultTowersImages.addAll(List.of(flourImagePath, eggImagePath, sugarImagePath, milkImagePath, bananaImagePath));
        playerTowers = new ArrayList<>();
        reserveTowers = new ArrayList<>();
    }

    public List<String> getDefaultTowersImages() {
        return defaultTowersImages;
    }

    public List<String> getPlayerTowersImages() {
        return playerTowersImages;
    }

    public void setPlayerTowersImages(List<String> playerTowersImages) {
        this.playerTowersImages = playerTowersImages;
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

    public List<Tower> getReserveTowers() {
        return reserveTowers;
    }

    public void setReserveTowers(List<Tower> reserveTowers) {
        this.reserveTowers = reserveTowers;
    }

    public void addPlayerTower(Tower tower) {
        this.playerTowers.add(tower);
    }

    public void removePlayerTower(Tower tower) {
        this.playerTowers.remove(tower);
    }

    public void addReserveTower(Tower tower) {
        this.reserveTowers.add(tower);
    }

    public void removeReserveTower(Tower tower) {
        this.reserveTowers.remove(tower);
    }
    public void swapActiveReserveTowers(int index, Tower activeTower, Tower reserveTower) {
        this.reserveTowers.set(index, activeTower);
        this.playerTowers.set(index, reserveTower);
    }
}
