package seng201.team48;

import seng201.team48.models.Purchasable;
import seng201.team48.models.RepairKit;
import seng201.team48.models.Upgrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UpgradeManager {
    private Random random = new Random();
    private List<Purchasable> defaultUpgradesList = new ArrayList<>();
    private List<String> defaultUpgradesImages = new ArrayList<>();
    private List<Purchasable> upgradesForSale;
    private List<Purchasable> playerUpgrades;
    private List<String> upgradesForSaleImages = new ArrayList<>();

    /**
     * Constructor which initialises list of default upgrades used by the shop.
     * Upgrades to level 1 towers are more common than other upgrade types.
     */
    public UpgradeManager() {
        Upgrade level1Upgrade = new Upgrade("1");
        Upgrade level1Upgrade2 = new Upgrade("1");
        Upgrade level2Upgrade = new Upgrade("2");
        Upgrade level3Upgrade = new Upgrade("3");
        RepairKit repairKit = new RepairKit();
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

    public List<Purchasable> getDefaultUpgradesList() {
        return defaultUpgradesList;
    }

    public void setDefaultUpgradesList(List<Purchasable> defaultShopList) {
        this.defaultUpgradesList = defaultShopList;
    }

    public List<Purchasable> getUpgradesForSale() {
        return upgradesForSale;
    }

    public void setUpgradesForSale(List<Purchasable> upgradesForSale) {
        this.upgradesForSale = upgradesForSale;
    }

    public List<Purchasable> getPlayerUpgrades() {
        return playerUpgrades;
    }

    public void setPlayerUpgrades(List<Purchasable> playerUpgrades) {
        this.playerUpgrades = playerUpgrades;
    }

    public void addPlayerUpgrade(Purchasable boughtUpgrade) {
        this.playerUpgrades.add(boughtUpgrade);
    }

    public List<String> getUpgradesForSaleImages() {
        return upgradesForSaleImages;
    }

    public void removePlayerUpgrade(Purchasable soldItem) { this.playerUpgrades.remove(soldItem);
    }
}
