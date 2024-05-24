package seng201.team48;

import seng201.team48.models.Purchasable;
import seng201.team48.models.RepairKit;
import seng201.team48.models.Upgrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class stores information about the level upgrades and repair kits used in gameplay.
 * It manages holding state, GUI windows, and passing objects into controller classes.
 *
 * @author Hannah Grace, Elise Newman
 */

public class UpgradeManager {
    public Random random;
    private List<Purchasable> defaultUpgradesList;
    private List<String> defaultUpgradesImages;
    private List<String> playerItemsImages;
    private List<Purchasable> upgradesForSale;
    private List<Purchasable> playerUpgrades;
    private List<String> upgradesForSaleImages;

    /**
     * Constructor which initialises list of default upgrades used by the shop.
     * Upgrades to level 1 towers are more common than other upgrade types.
     */
    public UpgradeManager(Random random) {
        this.random = random;
        Upgrade level1Upgrade = new Upgrade("1");
        Upgrade level1Upgrade2 = new Upgrade("1");
        Upgrade level2Upgrade = new Upgrade("2");
        Upgrade level3Upgrade = new Upgrade("3");
        RepairKit repairKit = new RepairKit();

        defaultUpgradesList = new ArrayList<>();
        defaultUpgradesImages = new ArrayList<>();
        upgradesForSaleImages = new ArrayList<>();
        playerItemsImages = new ArrayList<>();
        defaultUpgradesList.addAll(List.of(level1Upgrade, level1Upgrade2, level2Upgrade, level3Upgrade, repairKit));

        String l1ImagePath = getClass().getResource("/images/Level1.png").toExternalForm();
        String l2ImagePath = getClass().getResource("/images/Level2.png").toExternalForm();
        String l3ImagePath = getClass().getResource("/images/Level3.png").toExternalForm();
        String repairKitImagePath = getClass().getResource("/images/Repair.png").toExternalForm();
        defaultUpgradesImages.addAll(List.of(l1ImagePath, l1ImagePath, l2ImagePath, l3ImagePath, repairKitImagePath));
        upgradesForSaleImages = defaultUpgradesImages;
        upgradesForSale = defaultUpgradesList; // Initialise upgradesForSale
        playerUpgrades = new ArrayList<>(5);
    }

    /**
     * Generates random list of upgrades for sale
     */
    public List<Purchasable> generateUpgradesForSale() {
        for (int i = 0; i < upgradesForSale.size(); i++) {
            int randomIndex = random.nextInt(defaultUpgradesList.size());
            upgradesForSale.set(i, defaultUpgradesList.get(randomIndex));
            upgradesForSaleImages.set(i, defaultUpgradesImages.get(randomIndex));
        }
        //upgradesForSale.replaceAll(ignored -> defaultUpgradesList.get(random.nextInt(defaultUpgradesList.size())));
        return upgradesForSale;

    }

    /**
     * Gets list of default upgrade items.
     * @return default upgrades list
     */
    public List<Purchasable> getDefaultUpgradesList() {
        return defaultUpgradesList;
    }

    /**
     * Gets list of upgrades currently available for purchase in the shop.
     * @return list of upgrades for sale
     */
    public List<Purchasable> getUpgradesForSale() {
        return upgradesForSale;
    }

    /**
     * Sets list of Purchasable items for sale in upgrades section of shop
     * @param upgradesForSale list of Purchasable upgrades for sale
     */
    public void setUpgradesForSale(List<Purchasable> upgradesForSale) {
        this.upgradesForSale = upgradesForSale;
    }

    /**
     * Gets list of the player's upgrades available in their inventory
     * @return list of player's upgrades
     */
    public List<Purchasable> getPlayerUpgrades() {
        return playerUpgrades;
    }

    /**
     * Adds a purchased upgrade (level-up upgrade or repair kit) to the player's item inventory
     * @param boughtUpgrade the upgrade purchased from the shop
     */
    public void addPlayerUpgrade(Purchasable boughtUpgrade) {
        this.playerUpgrades.add(boughtUpgrade);
    }

    /**
     * Gets list of image paths of upgrades currently being sold in shop, sorted by their appearance on-screen
     * @return list of upgrade image paths
     */
    public List<String> getUpgradesForSaleImages() {
        return upgradesForSaleImages;
    }

    /**
     * Removes item (upgrade or repair kit) from player's inventory if item is sold or used.
     * @param item item to be removed from player's item inventory
     */
    public void removePlayerUpgrade(Purchasable item) {
        this.playerUpgrades.remove(item);
    }

    /**
     * Gets list of image paths of items (upgrade or repair kit) currently in player's item inventory
     * @return list of item image paths, sorted by their appearance in player's inventory on-screen
     */
    public List<String> getPlayerItemsImages() {
        return playerItemsImages;
    }

    /**
     * Adds image path to list of item images displayed in player's item inventory
     * @param imagePath item's image path to be added to list
     */
    public void addPlayerItemsImage(String imagePath) {
        this.playerItemsImages.add(imagePath);
    }

    /**
     * Removes image path at given index from list of item inventory image paths.
     * @param index index of the image path to be removed
     */
    public void removePlayerItemsImage(int index) {
        this.playerItemsImages.remove(index);
    }
}
