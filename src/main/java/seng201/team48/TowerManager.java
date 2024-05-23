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
        Tower flourTower = new Tower("Flour",
                "A flour station to fill your bowls.\n\nUsed in pasta, pasta sauce, pancakes, carbonara, cake, and banana bread recipes.\n\nReload speed: Slow.",
                100, 50, 10, 0.05);
        Tower eggTower = new Tower("Eggs",
                "An egg station to fill your bowls.\n\nUsed in fried eggs, scrambled eggs, pasta, pancakes, carbonara, cake, banana bread recipes.\n\nReload speed: Slow.",
                100, 50, 20, 0.05);
        Tower sugarTower = new Tower("Sugar",
                "A sugar station to fill your bowls.\n\nUsed only in cake and banana bread recipes.\n\nReload speed: Medium.",
                100, 50, 5, 0.02);
        Tower milkTower = new Tower("Milk",
                "A milk station to fill your bowls.\n\nUsed in pasta sauce, scrambled eggs, pancakes, carbonara, cake, and banana bread recipes.\n\nReload speed: Fast.",
                100, 50, 8, 0.035);
        Tower bananaTower = new Tower("Banana",
                "A fancy banana station to fill your bowls.\n\nUsed in smoothie and banana bread recipes.\n\nRefreshes quite slowly, but these recipes reward you well.",
                150, 75, 8, 0.04);
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

    public void addPlayerTowersImage(String imagePath) {
        this.playerTowersImages.add(imagePath);
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
