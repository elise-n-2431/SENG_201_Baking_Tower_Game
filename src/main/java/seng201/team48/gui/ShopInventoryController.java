package seng201.team48.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;
import seng201.team48.models.Tower;

/**
 * Controller class for the shop/inventory window.
 * @author Hannah Grace, Elise Newman
 */

public class ShopInventoryController {
    TowerManager towerManager;
    MainGameManager mainGameManager;
    public ShopInventoryController(MainGameManager mainGameManager){
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
    }
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
