package seng201.team48.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    UpgradeManager upgradeManager;
    private List<Tower> towersForSale;

    private int selectedItemIndex = -1;
    @FXML
    private Button back_button;
    @FXML
    private Button buyTower;
    @FXML
    private Button sell_tower;
    @FXML
    private Button place;
    @FXML
    private Button active1Button;
    @FXML
    private Button active2Button;
    @FXML
    private Button active3Button;
    @FXML
    private Button active4Button;
    @FXML
    private Button active5Button;
    @FXML
    private Button reserve1Button;
    @FXML
    private Button reserve2Button;
    @FXML
    private Button reserve3Button;
    @FXML
    private Button reserve4Button;
    @FXML
    private Button reserve5Button;
    @FXML
    private Button tower1Button;
    @FXML
    private Button tower2Button;
    @FXML
    private Button tower3Button;
    @FXML
    private Button tower4Button;
    @FXML
    private Button tower5Button;
    @FXML
    private Button upgrade1Button;
    @FXML
    private Button upgrade2Button;
    @FXML
    private Button upgrade3Button;
    @FXML
    private Button upgrade4Button;
    @FXML
    private Button upgrade5Button;
    @FXML
    private Label upgrade1PriceLabel;
    @FXML
    private Label upgrade2PriceLabel;
    @FXML
    private Label upgrade3PriceLabel;
    @FXML
    private Label upgrade4PriceLabel;
    @FXML
    private Label upgrade5PriceLabel;
    @FXML
    private Label viewItemNameLabel;
    @FXML
    private Label viewDescriptionLabel;
    @FXML
    private Label playerCoinsLabel;

    public ShopInventoryController(MainGameManager mainGameManager){
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
        upgradeManager = new UpgradeManager();
        System.out.println(upgradeManager.getDefaultUpgradesList());
    }

    public void initialize() {
        refreshShop();

        List<Button> buyTowerButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
        List<Button> buyUpgradeButtons = List.of(upgrade1Button, upgrade2Button, upgrade3Button, upgrade4Button, upgrade5Button);
        List<Label> upgradePriceLabels = List.of(upgrade1PriceLabel, upgrade2PriceLabel, upgrade3PriceLabel, upgrade4PriceLabel, upgrade5PriceLabel);

        List<Button> activeTowerButtons = List.of(active1Button, active2Button, active3Button, active4Button, active5Button);
        List<Button> reserveTowerButtons = List.of(reserve1Button, reserve2Button, reserve3Button, reserve4Button, reserve5Button);

        /*
        * Set up click functionality for purchasable tower buttons in shop
        *
        * */
        for (int i = 0; i < buyTowerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            buyTowerButtons.get(i).setOnAction(event -> {
                updateShopDisplay(towerManager.getDefaultTowers().get(finalI));
                selectedItemIndex = finalI;
                buyTowerButtons.forEach(button -> {
                    if (button == buyTowerButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

        // Assign upgrade items & repair kits to shop buttons
        List<Purchasable> upgradesForSale = upgradeManager.getUpgradesForSale();
        for (int i = 0; i < buyUpgradeButtons.size(); i++) {
            int finalI = i;
            buyUpgradeButtons.get(i).setText(upgradesForSale.get(i).getName());
            upgradePriceLabels.get(i).setText(String.valueOf(upgradesForSale.get(i).getPurchasePrice()));
            // Set on click functionality
            buyUpgradeButtons.get(i).setOnAction(event -> {
                updateShopDisplay(upgradesForSale.get(finalI));
                selectedItemIndex = finalI;
                buyUpgradeButtons.forEach(button -> {
                    if (button == buyUpgradeButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
    }

    /* Displays relevant tower info in the window -SHOULD IT BE @FXML? */
    public void updateShopDisplay(Purchasable purchasable){
        viewItemNameLabel.setText("Available in shop: " + purchasable.getName());
        viewDescriptionLabel.setText(purchasable.getDescription());
    }


    /**
     * Resets the lists of towers and upgrades available for purchase in the shop.
     * All tower types are available for sale.
     * List of available upgrades is randomly generated by instance of UpgradeManager.
     */
    public void refreshShop() {
        setTowersForSale(towerManager.getDefaultTowers());
        upgradeManager.setUpgradesForSale(upgradeManager.generateUpgradesForSale());
    }

    public List<Tower> getTowersForSale() {
        return towersForSale;
    }

    public void setTowersForSale(List<Tower> towersForSale) {
        this.towersForSale = towersForSale;
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
