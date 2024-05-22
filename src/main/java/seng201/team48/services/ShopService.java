package seng201.team48.services;

import seng201.team48.models.Tower;
import seng201.team48.models.Upgrade;

import java.util.List;

/**
 * Service class which validates purchase process in shop. Called from ShopInventoryController.java.
 */

public class ShopService {
    private List<Tower> nonemptyTowerList;
    private int nonemptyTowerIndex;
    private int nonemptyUpgradeIndex;
    public boolean canPurchase(int moneyAvailable, int moneyRequired) {
        if (moneyAvailable - moneyRequired >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the player has a free tower slot in their inventory.
     * Called from onBuyTowerButtonClicked method in ShopInventoryController.java
     * @param playerTowers List of active towers in the player's inventory (up to 5 items)
     * @param reserveTowers List of inactive towers in the player's inventory (up to 5 items)
     * @return true if playerTowers or reserveTowers lists are not both full, false if both lists are full.
     */
    public boolean hasTowerInventorySpace(List<Tower> playerTowers, List<Tower> reserveTowers) {
        for(int i = 0; i < playerTowers.size(); i++) {
            if(playerTowers.get(i) == null) {
                nonemptyTowerList = playerTowers;
                nonemptyTowerIndex = i;
                return true;
            }
        }
        for(int i = 0; i < reserveTowers.size(); i++) {
            if(reserveTowers.get(i) == null) {
                nonemptyTowerList = reserveTowers;
                nonemptyTowerIndex = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the player has a free item slot in their inventory.
     * Called from onBuyItemButtonClicked method in ShopInventoryController.java
     * @param playerUpgrades List of items (upgrades and repair kits) in the player's inventory (up to 5 items)
     * @return true if playerUpgrades list is not full, false otherwise
     */
    public boolean hasItemInventorySpace(List<Upgrade> playerUpgrades) {
        for(int i = 0; i < playerUpgrades.size(); i++) {
            if(playerUpgrades.get(i) == null) {
                nonemptyUpgradeIndex = i;
                return true;
            }
        }
        return false;
    }

    public List<Tower> getNonemptyTowerList() {
        return nonemptyTowerList;
    }

    public void setNonemptyTowerList(List<Tower> nonemptyTowerList) {
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
