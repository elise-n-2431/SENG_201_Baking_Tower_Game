package seng201.team48;

import seng201.team48.models.Purchasable;
import seng201.team48.models.RepairKit;
import seng201.team48.models.Upgrade;

import java.util.List;

public class UpgradeManager {
    private List<Purchasable> defaultShopList;

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
        defaultShopList.addAll(List.of(level1Upgrade, level1Upgrade2, level2Upgrade, level3Upgrade, repairKit));
    }

    public List<Purchasable> getDefaultShopList() {
        return defaultShopList;
    }

    public void setDefaultShopList(List<Purchasable> defaultShopList) {
        this.defaultShopList = defaultShopList;
    }
}
