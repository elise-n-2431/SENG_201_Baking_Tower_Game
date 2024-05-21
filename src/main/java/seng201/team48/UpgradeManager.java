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
    private List<Purchasable> upgradesForSale;

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
        upgradesForSale = defaultUpgradesList; // Initialise upgradesForSale
    }

    /**
     * Generates random list of upgrades for sale
     */
    public List<Purchasable> generateUpgradesForSale() {
        for (int i = 0; i < upgradesForSale.size(); i++) {
            int randomIndex = random.nextInt(defaultUpgradesList.size());
            upgradesForSale.set(i, defaultUpgradesList.get(randomIndex));
        }
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
}
