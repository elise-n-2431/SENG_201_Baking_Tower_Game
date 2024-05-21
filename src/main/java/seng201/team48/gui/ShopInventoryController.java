package seng201.team48.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;
import seng201.team48.UpgradeManager;
import seng201.team48.models.Purchasable;
import seng201.team48.models.Tower;

import java.util.List;

/**
 * Controller class for the shop/inventory window.
 * @author Hannah Grace, Elise Newman
 */

public class ShopInventoryController {
    TowerManager towerManager;
    MainGameManager mainGameManager;
    UpgradeManager upgradeManager = new UpgradeManager();
    private List<Tower> towersForSale;
    private List<Purchasable> upgradesForSale;
    private int selectedTowerIndex = -1;
    private final Tower[] selectedTowers = new Tower[3];
    @FXML
    private Button back_button;
    @FXML
    private Button buyTower;
    @FXML
    private Button sell_tower;
    @FXML
    private Button place;

    public ShopInventoryController(MainGameManager mainGameManager){
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
        refreshShop();

    }

    /**
     * Resets the lists of towers and upgrades available for purchase in the shop.
     * TO-DO: randomise.
     */
    public void refreshShop() {
        setTowersForSale(towerManager.getDefaultTowers());
        setUpgradesForSale(upgradeManager.getDefaultShopList());
    }

    public List<Tower> getTowersForSale() {
        return towersForSale;
    }

    public void setTowersForSale(List<Tower> towersForSale) {
        this.towersForSale = towersForSale;
    }

    public List<Purchasable> getUpgradesForSale() {
        return upgradesForSale;
    }

    public void setUpgradesForSale(List<Purchasable> upgradesForSale) {
        this.upgradesForSale = upgradesForSale;
    }

    @FXML
    public void onBuyTowerClicked(){
        //change tower from shop to inventory
    }
    @FXML
    private void onBackClicked(){
        mainGameManager.closeShopScreen();
    }


    public void onBuyButtonClicked(ActionEvent actionEvent) {
    }

    public void onSellTowerClicked(ActionEvent actionEvent) {
    }

    public void onPlaceButtonClicked(ActionEvent actionEvent) {
    }
}
