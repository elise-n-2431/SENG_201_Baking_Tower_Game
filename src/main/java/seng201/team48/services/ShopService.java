package seng201.team48.services;

import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;
import seng201.team48.UpgradeManager;
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
     * Sells an inventory item based on the given parameters.
     * Called from onSellClicked method in ShopInventoryController.
     *
     * @param mainGameManager        The MainGameManager instance.
     * @param lastSelectedInvList    The last selected inventory list ("active", "reserve", or "item").
     * @param selectedUpgradeIndex   The index of the selected upgrade item.
     * @param selectedActiveItemIndex   The index of the selected active tower item.
     * @param selectedReserveItemIndex  The index of the selected reserve tower item.
     * @return                       A string indicating the action to perform ("doRefresh", "showError", or "doNothing").
     */
    public String sellInvItem(MainGameManager mainGameManager, String lastSelectedInvList,
                            int selectedUpgradeIndex, int selectedActiveItemIndex, int selectedReserveItemIndex) {
        TowerManager towerManager = mainGameManager.getTowerManager();
        UpgradeManager upgradeManager = mainGameManager.getUpgradeManager();

        if (hasEnoughTowers(towerManager.getPlayerTowers()) || lastSelectedInvList == "item") {
            int sellPrice = -500;
            Purchasable soldItem;
            if (lastSelectedInvList == "active") {
                soldItem = towerManager.getPlayerTowers().get(selectedActiveItemIndex);
                sellPrice = soldItem.getSellPrice();
                towerManager.removePlayerTower((Tower) soldItem);
                towerManager.removePlayerTowersImage(selectedActiveItemIndex);
            } else if (lastSelectedInvList == "reserve") {
                soldItem = towerManager.getReserveTowers().get(selectedReserveItemIndex);
                sellPrice = soldItem.getSellPrice();
                towerManager.removeReserveTower((Tower) soldItem);
                towerManager.removePlayerTowersImage(selectedReserveItemIndex);
            } else if (lastSelectedInvList == "item") {
                soldItem = upgradeManager.getPlayerUpgrades().get(selectedUpgradeIndex);
                sellPrice = soldItem.getSellPrice();
                upgradeManager.removePlayerUpgrade(soldItem);
                upgradeManager.removePlayerItemsImage(selectedUpgradeIndex);
            } else {
                return "doNothing";
            }
            mainGameManager.addTotalMoney(sellPrice);
            return "doRefresh";
        } else {
            return "showError";
        }
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

    /**
     * Checks if the player has enough towers to sell a tower in their inventory.
     * @param playerTowers List of active player towers
     * @return true if player has more than 3 active towers, false otherwise.
     */
    public boolean hasEnoughTowers(List<Tower> playerTowers) {
        return playerTowers.size() > 3;
    }

    public String getNonemptyTowerList() {
        return nonemptyTowerList;
    }

}
