package seng201.team48;

import seng201.team48.models.Tower;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TowerManager} class manages different types of towers and their corresponding images
 * used in the game. It maintains lists of default towers, player-owned towers, and reserve towers,
 * along with their associated image paths.
 *
 * @author Hannah Grace, Elise Newman
 */

public class TowerManager {
    private final List<Tower> defaultTowers = new ArrayList<>();
    private List<String> defaultTowersImages = new ArrayList<>();
    private List<String> playerTowersImages = new ArrayList<>();
    private List<String> reserveTowersImages = new ArrayList<>();
    private List<Tower> playerTowers;
    private List<Tower> reserveTowers;

    /**
     * Constructs a new {@code TowerManager} and initializes the default towers and their images.
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

    /**
     * Gets the image paths for the default towers.
     *
     * @return a list of image paths for the default towers.
     */
    public List<String> getDefaultTowersImages() {
        return defaultTowersImages;
    }

    /**
     * Gets the image paths for the player's towers.
     *
     * @return a list of image paths for the player's towers.
     */
    public List<String> getPlayerTowersImages() {
        return playerTowersImages;
    }

    /**
     * Sets the image paths for the player's towers.
     *
     * @param playerTowersImages a list of image paths to set for the player's towers.
     */
    public void setPlayerTowersImages(List<String> playerTowersImages) {
        this.playerTowersImages = playerTowersImages;
    }

    /**
     * Adds an image path to the player's towers images.
     *
     * @param imagePath the image path to add.
     */
    public void addPlayerTowersImage(String imagePath) {
        this.playerTowersImages.add(imagePath);
    }

    /**
     * Removes an image path from the player's towers images at the specified index.
     *
     * @param index the index of the image path to remove.
     */
    public void removePlayerTowersImage(int index) {
        this.playerTowersImages.remove(index);
    }

    /**
     * Adds an image path to the reserve towers images.
     *
     * @param imagePath the image path to add.
     */
    public void addReserveTowersImage(String imagePath) {
        this.reserveTowersImages.add(imagePath);
    }

    /**
     * Gets the list of tower types: Ingredient stations for eggs, flour, sugar, milk, and banana.
     *
     * @return a list of default towers.
     */
    public List<Tower> getDefaultTowers() {
        return defaultTowers;
    }

    /**
     * Gets the list of player's towers.
     *
     * @return a list of player's towers.
     */
    public List<Tower> getPlayerTowers() {
        return playerTowers;
    }

    /**
     * Sets the list of player's active towers, which will be used in gameplay.
     *
     * @param playerTowers a list of the player's active towers
     */
    public void setPlayerTowers(List<Tower> playerTowers) {
        this.playerTowers = playerTowers;
    }

    /**
     * Gets the list of the player's reserve towers.
     *
     * @return a list of reserve towers.
     */
    public List<Tower> getReserveTowers() {
        return reserveTowers;
    }

    /**
     * Adds a tower to the player's active towers.
     *
     * @param tower the tower to add.
     */
    public void addPlayerTower(Tower tower) {
        this.playerTowers.add(tower);
    }

    /**
     * Removes a tower from the player's active towers.
     *
     * @param tower the tower to remove.
     */
    public void removePlayerTower(Tower tower) {
        this.playerTowers.remove(tower);
    }

    /**
     * Adds a tower to the player's reserve towers.
     *
     * @param tower the tower to add.
     */
    public void addReserveTower(Tower tower) {
        this.reserveTowers.add(tower);
    }

    /**
     * Removes a tower from the reserve towers.
     *
     * @param tower the tower to remove.
     */
    public void removeReserveTower(Tower tower) {
        this.reserveTowers.remove(tower);
    }
    public void swapActiveReserveTowers(int index, Tower activeTower, Tower reserveTower) {
        this.reserveTowers.set(index, activeTower);
        this.playerTowers.set(index, reserveTower);
    }
}
