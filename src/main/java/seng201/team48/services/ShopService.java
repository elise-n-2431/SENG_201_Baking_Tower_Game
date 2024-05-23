package seng201.team48.services;

import seng201.team48.models.Purchasable;
import seng201.team48.models.Tower;
import java.util.List;

/**
 * Service class which validates purchase process in shop. Called from ShopInventoryController.java.
 */

public class ShopService {
    private String nonemptyTowerList;
    private int nonemptyTowerIndex;
    private int nonemptyUpgradeIndex;
    public boolean canPurchase(int moneyAvailable, int moneyRequired) {
        return moneyAvailable - moneyRequired >= 0;
    }

    /**
     * Checks if the player has a free tower slot in their inventory.
     * Called from onBuyTowerButtonClicked method in ShopInventoryController.java
     * @param playerTowers List of active towers in the player's inventory (up to 5 items)
     * @param reserveTowers List of inactive towers in the player's inventory (up to 5 items)
     * @return true if playerTowers or reserveTowers lists are not both full, false if both lists are full.
     */
    public boolean hasTowerInventorySpace(List<Tower> playerTowers, List<Tower> reserveTowers) {
        if (playerTowers.size() < 5) {
            nonemptyTowerList = "playerTower";
            return true;
        }
        else if (reserveTowers.size() < 5) {
            nonemptyTowerList = "reserveTower";
            return true;
        }
        return false;
    }

    /**
     * Checks if the player has a free item slot in their inventory.
     * Called from onBuyItemButtonClicked method in ShopInventoryController.java
     * @param playerUpgrades List of items (upgrades and repair kits) in the player's inventory (up to 5 items)
     * @return true if playerUpgrades list is not full, false otherwise
     */
    public boolean hasItemInventorySpace(List<Purchasable> playerUpgrades) {
        return playerUpgrades.size() < 5;
    }

    public String getNonemptyTowerList() {
        return nonemptyTowerList;
    }

    public void setNonemptyTowerList(String nonemptyTowerList) {
        this.nonemptyTowerList = nonemptyTowerList;
    }

    public int getNonemptyTowerIndex() {
        return nonemptyTowerIndex;
    }

    public void setNonemptyTowerIndex(int nonemptyTowerIndex) {
        this.nonemptyTowerIndex = nonemptyTowerIndex;
    }

    public int getNonemptyUpgradeIndex() {
        return nonemptyUpgradeIndex;
    }

    public void setNonemptyUpgradeIndex(int nonemptyUpgradeIndex) {
        this.nonemptyUpgradeIndex = nonemptyUpgradeIndex;
    }
}
