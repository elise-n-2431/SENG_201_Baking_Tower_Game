package seng201.team48.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;
import seng201.team48.UpgradeManager;
import seng201.team48.models.Purchasable;
import seng201.team48.models.Tower;
import seng201.team48.services.ShopService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controller class for the shop/inventory window.
 * @author Hannah Grace, Elise Newman
 */

public class ShopInventoryController {
    TowerManager towerManager;
    MainGameManager mainGameManager;
    UpgradeManager upgradeManager;
    ShopService shopService = new ShopService();
    private List<Tower> towersForSale;
    private List<Purchasable> totalShopItems;
    private int selectedItemIndex = -1;
    private int selectedActiveItemIndex = -1;
    private int selectedReserveItemIndex = -1;
    @FXML
    private Button back_button;
    @FXML
    private Button buyTowerButton;
    @FXML
    private Button buyItemButton;
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
    private Button boughtItem1Button;
    @FXML
    private Button boughtItem2Button;
    @FXML
    private Button boughtItem3Button;
    @FXML
    private Button boughtItem4Button;
    @FXML
    private Button boughtItem5Button;
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
    List<Button> buyTowerButtons;
    List<Button> buyUpgradeButtons;
    List<Label> upgradePriceLabels;
    List<Button> activeTowerButtons;
    List<Button> reserveTowerButtons;
    List<Button> boughtItemButtons;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);

    public ShopInventoryController(MainGameManager mainGameManager){
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
        upgradeManager = new UpgradeManager();
        totalShopItems = new ArrayList<Purchasable>();
        System.out.println(upgradeManager.getDefaultUpgradesList());
    }

    public void initialize() {
        refreshShop();

        buyItemButton.setVisible(false);
        buyTowerButton.setVisible(false);
        playerCoinsLabel.setText(String.valueOf(mainGameManager.getTotalMoney()));

        buyTowerButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
        buyUpgradeButtons = List.of(upgrade1Button, upgrade2Button, upgrade3Button, upgrade4Button, upgrade5Button);
        upgradePriceLabels = List.of(upgrade1PriceLabel, upgrade2PriceLabel, upgrade3PriceLabel, upgrade4PriceLabel, upgrade5PriceLabel);

        activeTowerButtons = List.of(active1Button, active2Button, active3Button, active4Button, active5Button);
        reserveTowerButtons = List.of(reserve1Button, reserve2Button, reserve3Button, reserve4Button, reserve5Button);
        boughtItemButtons = List.of(boughtItem1Button, boughtItem2Button, boughtItem3Button, boughtItem4Button, boughtItem5Button);

        // List containing everything from List<Tower> defaultTowers and List<Purchasable> upgradesForSale
        totalShopItems.addAll(towerManager.getDefaultTowers());
        totalShopItems.addAll(upgradeManager.getUpgradesForSale());

        /*
         * Set up click functionality for purchasable tower buttons in shop
         *
         * */
        for (int i = 0; i < buyTowerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            buyTowerButtons.get(i).setOnAction(event -> {
                updateShopDisplay(towerManager.getDefaultTowers().get(finalI));
                buyItemButton.setVisible(false);
                buyTowerButton.setVisible(true);
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
            // Set item shop buttons
            String imagePath = upgradeManager.getUpgradesForSaleImages().get(i);
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            buyUpgradeButtons.get(i).setGraphic(imageView);

            upgradePriceLabels.get(i).setText(String.valueOf(upgradesForSale.get(i).getPurchasePrice()));

            // Set on click functionality
            buyUpgradeButtons.get(i).setOnAction(event -> {
                updateShopDisplay(upgradesForSale.get(finalI));
                buyTowerButton.setVisible(false);
                buyItemButton.setVisible(true);
                selectedItemIndex = finalI + 5;
                buyUpgradeButtons.forEach(button -> {
                    if (button == buyUpgradeButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

        // PLAYER INVENTORY: ACTIVE TOWERS, RESERVE TOWERS, BOUGHT ITEMS
        for (int i = 0; i < activeTowerButtons.size(); i++) {
            int finalI = i;
            // Set names of the player's active towers
            if (i < towerManager.getPlayerTowers().size()) {
                // Set relevant button's image in inventory
                String imagePath = towerManager.getPlayerTowersImages().get(i);
                Image image = new Image(imagePath);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                activeTowerButtons.get(i).setGraphic(imageView);
                //activeTowerButtons.get(i).setText(towerManager.getPlayerTowers().get(i).getName());
            }
            else {
                // Hide buttons
                activeTowerButtons.get(i).setVisible(false);
            }
            // Set on click functionality
            activeTowerButtons.get(i).setOnAction(event -> {
                selectedActiveItemIndex = finalI;
                if (selectedReserveItemIndex != -1) {
                    // Swap active and reserve tower
                    Tower reserveTower = towerManager.getReserveTowers().get(selectedReserveItemIndex);
                    Tower activeTower = towerManager.getPlayerTowers().get(finalI);
                    towerManager.swapActiveReserveTowers(finalI, activeTower, reserveTower);
                    activeTowerButtons.get(finalI).setText(reserveTower.getName());
                    reserveTowerButtons.get(selectedReserveItemIndex).setText(activeTower.getName());
                }
                activeTowerButtons.forEach(button -> {
                    if (button == activeTowerButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

        // Set reserve towers
        for (int i = 0; i < reserveTowerButtons.size(); i++) {
            int finalI = i;
            // Set names of the player's active towers
            if (i < towerManager.getReserveTowers().size()) {
                reserveTowerButtons.get(i).setText(towerManager.getReserveTowers().get(i).getName());
            }
            else {
                // Hide buttons
                reserveTowerButtons.get(i).setVisible(false);
            }
        }

    }

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
    private void onBackClicked(){
        mainGameManager.closeShopScreen();
    }


    public void onBuyTowerButtonClicked(ActionEvent actionEvent) {
        int moneyRequired = totalShopItems.get(selectedItemIndex).getPurchasePrice();
        boolean hasEnoughMoney = shopService.canPurchase(mainGameManager.getTotalMoney(), moneyRequired);
        boolean hasInventorySpace = shopService.hasTowerInventorySpace(towerManager.getPlayerTowers(), towerManager.getReserveTowers());
        if (selectedItemIndex != -1 && hasEnoughMoney && hasInventorySpace) {
            // Remove item from shop and place in first available spot in player inventory
            buyTowerButtons.get(selectedItemIndex).setVisible(false);
            int price = totalShopItems.get(selectedItemIndex).getPurchasePrice();
            String name = totalShopItems.get(selectedItemIndex).getName();
            mainGameManager.deductTotalMoney(price);
            playerCoinsLabel.setText(String.valueOf(mainGameManager.getTotalMoney()));

            // Add last clicked tower to player inventory in the correct list
            if (Objects.equals(shopService.getNonemptyTowerList(), "playerTower")) {
                towerManager.addPlayerTower((Tower) totalShopItems.get(selectedItemIndex));

                // Set relevant button's image in inventory
                String imagePath = towerManager.getDefaultTowersImages().get(selectedItemIndex);
                Image image = new Image(imagePath);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(20);
                imageView.setFitHeight(40);
                Button newButton = activeTowerButtons.get(towerManager.getPlayerTowers().size() - 1);
                newButton.setVisible(true);
                newButton.setGraphic(imageView);

            }
            else {
                towerManager.addReserveTower((Tower) totalShopItems.get(selectedItemIndex));
                // Make button of new index visible
                Button newButton = reserveTowerButtons.get(towerManager.getReserveTowers().size() - 1);
                newButton.setVisible(true);
                newButton.setText(totalShopItems.get(selectedItemIndex).getName());
            }
            infoAlert.setTitle("Tower Purchase Successful");
            infoAlert.setHeaderText("You have bought " + name);
            infoAlert.showAndWait();


        } else if (!hasEnoughMoney) {
            alert.setTitle("Error");
            alert.setHeaderText("Not enough money");
            alert.setContentText("Earn more money by creating recipes during the next round!");
            alert.showAndWait();
        } else if (!hasInventorySpace) {
            alert.setTitle("Error");
            alert.setHeaderText("Not enough inventory space");
            alert.setContentText("Your active and reserve tower slots are full. Try upgrading your towers instead!");
            alert.showAndWait();
        }
    }

    public void onBuyItemButtonClicked(ActionEvent actionEvent) {
        int moneyRequired = totalShopItems.get(selectedItemIndex).getPurchasePrice();
        boolean hasEnoughMoney = shopService.canPurchase(mainGameManager.getTotalMoney(), moneyRequired);
        boolean hasInventorySpace = shopService.hasItemInventorySpace(upgradeManager.getPlayerUpgrades());
        if (selectedItemIndex != -1 && hasEnoughMoney && hasInventorySpace) {

            int price = totalShopItems.get(selectedItemIndex).getPurchasePrice();
            String name = totalShopItems.get(selectedItemIndex).getName();
            mainGameManager.deductTotalMoney(price);
            playerCoinsLabel.setText(String.valueOf(mainGameManager.getTotalMoney()));

            // Remove item from shop and place in first available spot in player inventory
            buyUpgradeButtons.get(selectedItemIndex - 5).setVisible(false);
            upgradeManager.addPlayerUpgrade(totalShopItems.get(selectedItemIndex));

            // Set relevant button's image in inventory
            String imagePath = upgradeManager.getUpgradesForSaleImages().get(selectedItemIndex - 5);
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            Button newButton = boughtItemButtons.get(upgradeManager.getPlayerUpgrades().size() - 1);
            newButton.setVisible(true);
            newButton.setGraphic(imageView);

            infoAlert.setTitle("Item Purchase Successful");
            infoAlert.setContentText("You have bought " + name);
            infoAlert.showAndWait();

        } else if (!hasEnoughMoney) {
            alert.setTitle("Error");
            alert.setHeaderText("Not enough money");
            alert.setContentText("Earn more money by creating recipes during the next round!");
            alert.showAndWait();
        } else if (!hasInventorySpace) {
            alert.setTitle("Error");
            alert.setHeaderText("Not enough inventory space");
            alert.setContentText("You can only store up to 5 items at a time. Try upgrading your towers or selling items!");
            alert.showAndWait();
        }

    }

    public void onSellTowerClicked(ActionEvent actionEvent) {
    }

    public void onPlaceButtonClicked(ActionEvent actionEvent) {
    }
}
