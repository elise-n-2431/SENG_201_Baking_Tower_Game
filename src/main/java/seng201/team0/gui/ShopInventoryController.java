package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import seng201.team0.MainGameManager;
import seng201.team0.models.Tower;

import java.util.List;

/**
 * Controller class for the shop/inventory window.
 * @author Hannah Grace, Elise Newman
 */

public class ShopInventoryController {

    public ShopInventoryController(MainGameManager mainGameManager){
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


}
