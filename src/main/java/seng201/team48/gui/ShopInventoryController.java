package seng201.team48.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;
import seng201.team48.UpgradeManager;
import seng201.team48.models.Purchasable;
import seng201.team48.models.Tower;
import seng201.team48.services.InventoryService;
import seng201.team48.services.ShopService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controller class for the shop/inventory window.
 * Manages the interaction between the user and the shop/inventory UI elements.
 * Handles purchasing, selling, and using items/towers in the game.
 * Initializes the layout and updates the UI based on user actions.
 * Provides functionality to refresh the shop, display item details, and manage player inventory.
 *
 * @author Hannah Grace, Elise Newman
 * @see MainGameManager
 * @see TowerManager
 * @see UpgradeManager
 * @see InventoryService
 * @see ShopService
 */

public class ShopInventoryController {
    TowerManager towerManager;
    MainGameManager mainGameManager;
    UpgradeManager upgradeManager;
    ShopService shopService = new ShopService();
    InventoryService inventoryService = new InventoryService();
    private String lastSelectedInvList;
    private List<Tower> towersForSale;
    private List<Purchasable> totalShopItems;
    private int selectedItemIndex = -1;
    private int selectedActiveItemIndex = -1;
    private int selectedReserveItemIndex = -1;
    private int selectedUpgradeIndex = -1;
    @FXML
    private Button buyTowerButton;
    @FXML
    private Button buyItemButton;
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
    private Button useItemButton;
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
    @FXML
    private Label invItemNameLabel;
    @FXML
    private Label invDescriptionLabel;
    @FXML
    private Label invLevelLabel;
    @FXML
    private Label sellPriceLabel;
    private List<Button> buyTowerButtons;
    private List<Button> buyUpgradeButtons;
    private List<Label> upgradePriceLabels;
    private List<Button> activeTowerButtons;
    private List<Button> reserveTowerButtons;
    private List<Button> boughtItemButtons;
    private Alert alert;
    private Alert infoAlert;

    /**
     * Constructor for the ShopInventoryController.
     * Initializes the main game manager, tower manager, and upgrade manager.
     *
     * @param mainGameManager the main game manager
     */
    public ShopInventoryController(MainGameManager mainGameManager) {
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
        this.upgradeManager = mainGameManager.getUpgradeManager();
        totalShopItems = new ArrayList<>();
    }

    /**
     * Initializes the controller class.
     * Sets up the shop and inventory layout.
     * Prepares the UI elements and loads initial data.
     */
    public void initialize() {
        refreshShop();
        buyTowerButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
        buyUpgradeButtons = List.of(upgrade1Button, upgrade2Button, upgrade3Button, upgrade4Button, upgrade5Button);
        upgradePriceLabels = List.of(upgrade1PriceLabel, upgrade2PriceLabel, upgrade3PriceLabel, upgrade4PriceLabel, upgrade5PriceLabel);

        activeTowerButtons = List.of(active1Button, active2Button, active3Button, active4Button, active5Button);
        reserveTowerButtons = List.of(reserve1Button, reserve2Button, reserve3Button, reserve4Button, reserve5Button);
        boughtItemButtons = List.of(boughtItem1Button, boughtItem2Button, boughtItem3Button, boughtItem4Button, boughtItem5Button);

        alert = new Alert(Alert.AlertType.ERROR);
        infoAlert = new Alert(Alert.AlertType.INFORMATION);

        // List containing everything from List<Tower> defaultTowers and List<Purchasable> upgradesForSale
        totalShopItems.addAll(towerManager.getDefaultTowers());
        totalShopItems.addAll(upgradeManager.getUpgradesForSale());
        initialiseLayout();

    }

    /**
     * Updates the shop display with the details of the selected purchasable item.
     *
     * @param purchasable the selected item
     */
    public void updateShopDisplay(Purchasable purchasable) {
        viewItemNameLabel.setText("Available in shop: " + purchasable.getName());
        viewDescriptionLabel.setText(purchasable.getDescription());
    }

    /**
     * Updates the inventory display with the details of the selected purchasable item.
     *
     * @param purchasable the selected item
     */
    public void updateInvDisplay(Purchasable purchasable) {
        invItemNameLabel.setText(("In Inventory: " + purchasable.getName()));
        invDescriptionLabel.setText((purchasable.getDescription()));
        sellPriceLabel.setText(String.valueOf(purchasable.getSellPrice()));
        if (purchasable instanceof Tower) {
            invLevelLabel.setText("Level: " + ((Tower) purchasable).getLevel());
        } else {
            invLevelLabel.setText("");
        }
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

    /**
     * Sets the list of towers available for sale.
     *
     * @param towersForSale the list of towers to set
     */
    public void setTowersForSale(List<Tower> towersForSale) {
        this.towersForSale = towersForSale;
    }

    /**
     * Handles the action when the back button is clicked.
     * Closes the shop screen.
     */
    @FXML
    private void onBackClicked() {
        mainGameManager.closeShopScreen();
    }

    /**
     * Handles the action when the buy tower button is clicked.
     * Purchases the selected tower and updates the player inventory and coins.
     *
     * @param actionEvent the event triggered when the buy tower button is clicked
     * @throws CloneNotSupportedException if the tower cannot be cloned
     */
    public void onBuyTowerButtonClicked(ActionEvent actionEvent) throws CloneNotSupportedException {
        int moneyRequired = totalShopItems.get(selectedItemIndex).getPurchasePrice();
        boolean hasEnoughMoney = shopService.canPurchase(mainGameManager.getTotalMoney(), moneyRequired);
        boolean hasInventorySpace = shopService.hasTowerInventorySpace(towerManager.getPlayerTowers(), towerManager.getReserveTowers());
        if (selectedItemIndex != -1 && hasEnoughMoney && hasInventorySpace) {
            // Remove item from shop and place in first available spot in player inventory
            buyTowerButtons.get(selectedItemIndex).setVisible(false);
            int price = totalShopItems.get(selectedItemIndex).getPurchasePrice();
            String name = totalShopItems.get(selectedItemIndex).getName();
            mainGameManager.deductTotalMoney(price);
            updatePlayerCoinsLabel();

            Tower clonedTower;
            clonedTower = (Tower) totalShopItems.get(selectedItemIndex).cloneSelf();

            // Add last clicked tower to player inventory in the correct list
            if (Objects.equals(shopService.getNonemptyTowerList(), "playerTower")) {
                towerManager.addPlayerTower(clonedTower);

                // Set relevant button's image in inventory
                String imagePath = towerManager.getDefaultTowersImages().get(selectedItemIndex);
                towerManager.addPlayerTowersImage(imagePath);
                Image image = new Image(imagePath);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                Button newButton = activeTowerButtons.get(towerManager.getPlayerTowers().size() - 1);
                newButton.setVisible(true);
                newButton.setGraphic(imageView);

            } else {
                towerManager.addReserveTower(clonedTower);

                // Set relevant button's image in inventory
                String imagePath = towerManager.getDefaultTowersImages().get(selectedItemIndex);
                towerManager.addReserveTowersImage(imagePath);
                Image image = new Image(imagePath);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                Button newButton = reserveTowerButtons.get(towerManager.getReserveTowers().size() - 1);
                newButton.setVisible(true);
                newButton.setGraphic(imageView);
            }
            inventoryService.showAlert(infoAlert, "Tower purchase successful", "You have bought " + name, "");

        } else if (!hasEnoughMoney) {
            inventoryService.showAlert(alert, "Error", "Not enough money",
                    "Earn more money by creating recipes during the next round!");
        } else if (!hasInventorySpace) {
            inventoryService.showAlert(alert, "Error", "Not enough inventory space",
                    "Your active and reserve tower slots are full. Try upgrading your towers instead!");
        }
    }

    /**
     * Handles the action when the buy item button is clicked.
     * Purchases the selected item and updates the player inventory and coins.
     *
     * @param actionEvent the event triggered when the buy tower button is clicked
     */
    public void onBuyItemButtonClicked(ActionEvent actionEvent) {
        int moneyRequired = totalShopItems.get(selectedItemIndex).getPurchasePrice();
        boolean hasEnoughMoney = shopService.canPurchase(mainGameManager.getTotalMoney(), moneyRequired);
        boolean hasInventorySpace = shopService.hasItemInventorySpace(upgradeManager.getPlayerUpgrades());
        if (selectedItemIndex != -1 && hasEnoughMoney && hasInventorySpace) {

            int price = totalShopItems.get(selectedItemIndex).getPurchasePrice();
            String name = totalShopItems.get(selectedItemIndex).getName();
            mainGameManager.deductTotalMoney(price);
            updatePlayerCoinsLabel();

            // Remove item from shop and place in first available spot in player inventory
            buyUpgradeButtons.get(selectedItemIndex - 5).setVisible(false);
            upgradeManager.addPlayerUpgrade(totalShopItems.get(selectedItemIndex));

            // Set relevant button's image in inventory
            String imagePath = upgradeManager.getUpgradesForSaleImages().get(selectedItemIndex - 5);
            upgradeManager.addPlayerItemsImage(imagePath);
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            Button newButton = boughtItemButtons.get(upgradeManager.getPlayerUpgrades().size() - 1);
            newButton.setVisible(true);
            newButton.setGraphic(imageView);

            inventoryService.showAlert(infoAlert, "Item Purchase Successful", "", "You have bought " + name);

        } else if (!hasEnoughMoney) {
            inventoryService.showAlert(alert, "Error", "Not enough money",
                    "Earn more money by creating recipes during the next round!");
        } else if (!hasInventorySpace) {
            inventoryService.showAlert(alert, "Error", "Not enough inventory space",
                    "You can only store up to 5 items at a time. Try upgrading your towers or selling items!");
        }
        initialiseLayout();
    }

    /**
     * Handles the action event when the sell button is clicked.
     * Passes information into sellInvItem method in ShopService.
     *
     * @param actionEvent            The ActionEvent triggered by the sell button.
     */
    public void onSellClicked(ActionEvent actionEvent) {
        String sellAction = shopService.sellInvItem(mainGameManager, lastSelectedInvList, selectedUpgradeIndex, selectedActiveItemIndex, selectedReserveItemIndex);
        if (sellAction == "doRefresh") {
            initialiseLayout();
        } else if (sellAction == "showError") {
            inventoryService.showAlert(alert, "Error", "Cannot sell tower", "You need three towers at all times!");
        }
    }

    public void onMakeReserveButtonClicked(ActionEvent actionEvent) {
    }

    public void onMakeActiveButtonClicked(ActionEvent actionEvent) {
    }

    /**
     * Use item button appears when an active or reserve station is clicked.
     *
     * @param actionEvent When "Use Item" button is clicked
     */
    public void onUseItemButtonClicked(ActionEvent actionEvent) {
        Purchasable item = upgradeManager.getPlayerUpgrades().get(selectedUpgradeIndex);
        Tower tower = switch (lastSelectedInvList) {
            case "active" -> towerManager.getPlayerTowers().get(selectedActiveItemIndex);
            case "reserve" -> towerManager.getReserveTowers().get(selectedReserveItemIndex);
            default -> throw new IllegalArgumentException("Unexpected list clicked: " + lastSelectedInvList);
        };

        int requiredLevel = -1;
        try {
            switch (item.getName()) {
                case "Level 1 Upgrade":
                    requiredLevel = 1;
                    break;
                case "Level 2 Upgrade":
                    requiredLevel = 2;
                    break;
                case "Level 3 Upgrade":
                    requiredLevel = 3;
                    break;
                case "Repair kit":
                    // Checks if the tower is broken. If true, fix it. If not, give alert and do nothing.
                    if (tower.isBroken()) {
                        tower.setBroken(false);
                    } else {
                        inventoryService.showAlert(alert, "Error", "Cannot use repair kit", "This tower is not broken! Save your item for a broken tower.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + item.getName());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        // If item is a level upgrade and the tower is the correct level to use it, then level up
        if (requiredLevel != -1) {
            boolean isCorrectUpgradeLevel = inventoryService.isCorrectUpgradeLevel(tower, requiredLevel);
            if (isCorrectUpgradeLevel) {
                tower.setLevel(requiredLevel + 1);
            } else {
                inventoryService.showAlert(alert, "Error", "Cannot use " + item.getName() + " on this tower.",
                        "This upgrade must be used to upgrade a level " + requiredLevel + " tower to level " + (requiredLevel + 1) + ".");

            }
        }

        // Remove item from player inventory
        upgradeManager.removePlayerUpgrade(item);
        upgradeManager.removePlayerItemsImage(selectedUpgradeIndex);
        initialiseLayout();
    }

    public void updatePlayerCoinsLabel() {
        playerCoinsLabel.setText(String.valueOf(mainGameManager.getTotalMoney()));
    }

    public void initialiseLayout() {
        buyItemButton.setVisible(false);
        buyTowerButton.setVisible(false);
        useItemButton.setVisible(false);
        updatePlayerCoinsLabel();

        // Show first item in inventory active stations and first item in shop
        updateInvDisplay((towerManager.getPlayerTowers().get(0)));
        updateShopDisplay(towerManager.getDefaultTowers().get(0));
        sellPriceLabel.setText(String.valueOf(towerManager.getPlayerTowers().get(0).getSellPrice()));
        active1Button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
        tower1Button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");


        // PLAYER INVENTORY: ACTIVE TOWERS, RESERVE TOWERS, BOUGHT ITEMS

        // ACTIVE TOWERS
        for (int i = 0; i < activeTowerButtons.size(); i++) {
            int finalI = i;
            // Set images of the player's active towers
            if (i < towerManager.getPlayerTowers().size()) {
                // Set relevant button's image in inventory
                boolean isBroken = towerManager.getPlayerTowers().get(i).isBroken();
                setButtonImages(towerManager.getPlayerTowersImages(), activeTowerButtons, i, isBroken);
            } else {
                // Hide buttons
                activeTowerButtons.get(i).setVisible(false);
            }
            // Set on click functionality
            activeTowerButtons.get(i).setOnAction(event -> {
                selectedActiveItemIndex = finalI;
                lastSelectedInvList = "active";
                useItemButton.setVisible(true);
                updateInvDisplay(towerManager.getPlayerTowers().get(finalI));
//                if (selectedReserveItemIndex != -1) {
//                    // Swap active and reserve tower
//                    Tower reserveTower = towerManager.getReserveTowers().get(selectedReserveItemIndex);
//                    Tower activeTower = towerManager.getPlayerTowers().get(finalI);
//                    towerManager.swapActiveReserveTowers(finalI, activeTower, reserveTower);
//                    activeTowerButtons.get(finalI).setText(reserveTower.getName());
//                    reserveTowerButtons.get(selectedReserveItemIndex).setText(activeTower.getName());
//                }
                activeTowerButtons.forEach(button -> {
                    if (button == activeTowerButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

        // RESERVE TOWERS
        for (int i = 0; i < reserveTowerButtons.size(); i++) {
            int finalI = i;
            // Set names of the player's active towers
            if (i < towerManager.getReserveTowers().size()) {
                // Set relevant button's image in inventory
                setButtonImages(towerManager.getDefaultTowersImages(), reserveTowerButtons, i, false);
            } else {
                // Hide buttons
                reserveTowerButtons.get(i).setVisible(false);
            }
            // Set on click functionality RESERVE
            reserveTowerButtons.get(i).setOnAction(event -> {
                selectedReserveItemIndex = finalI;
                lastSelectedInvList = "reserve";
                useItemButton.setVisible(true);
                updateInvDisplay(towerManager.getReserveTowers().get(finalI));
//                if (selectedActiveItemIndex != -1) {
//                    // COPY PASTED -- DOES IT WORK???
//                    Tower reserveTower = towerManager.getReserveTowers().get(finalI);
//                    Tower activeTower = towerManager.getPlayerTowers().get(selectedActiveItemIndex);
//                    towerManager.swapActiveReserveTowers(finalI, activeTower, reserveTower);
//                    activeTowerButtons.get(selectedActiveItemIndex).setText(reserveTower.getName());
//                    reserveTowerButtons.get(finalI).setText(activeTower.getName());
//                }
                reserveTowerButtons.forEach(button -> {
                    if (button == reserveTowerButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

        // ITEM INVENTORY
        for (int i = 0; i < boughtItemButtons.size(); i++) {
            int finalI = i;
            if (i < upgradeManager.getPlayerUpgrades().size()) {
                // Set relevant button's image in inventory
                setButtonImages(upgradeManager.getPlayerItemsImages(), boughtItemButtons, i, false);
            } else {
                boughtItemButtons.get(i).setVisible(false);
            }

            // On click functionality
            boughtItemButtons.get(i).setOnAction(event -> {
                selectedUpgradeIndex = finalI;
                lastSelectedInvList = "item";
                useItemButton.setVisible(false);
                // SET MAKE RESERVE/ACTIVE TO FALSE
                updateInvDisplay(upgradeManager.getPlayerUpgrades().get(finalI));
                boughtItemButtons.forEach(button -> {
                    if (button == boughtItemButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

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
            setButtonImages(upgradeManager.getUpgradesForSaleImages(), buyUpgradeButtons, i, false);

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
    }


    /**
     * Sets button images for towers and items in inventory and shop.
     * Overloading of setButtonImages in the case when button and image are at the same index in their respective lists.
     *
     * @param listToGetFrom List of image paths being looped through
     * @param buttonList    List of buttons being looped through
     * @param index         Location of the desired button and image in their respective lists
     * @param needsOverlay  True if the tower is broken and needs overlay, false otherwise
     */
    public void setButtonImages(List<String> listToGetFrom, List<Button> buttonList, int index, boolean needsOverlay) {
        // Set relevant button's image in inventory
        String imagePath = listToGetFrom.get(index);
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        buttonList.get(index).setGraphic(imageView);

        // Set 'X' image overlay if tower is in broken state
        if (needsOverlay) {
            String overlayImagePath = getClass().getResource("/images/Broken.png").toExternalForm();
            Image overlayImage = new Image(overlayImagePath);
            ImageView overlayImageView = new ImageView(overlayImage);
            overlayImageView.setFitWidth(40);
            overlayImageView.setFitHeight(40);
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(imageView, overlayImageView);
            buttonList.get(index).setGraphic(stackPane);
        }
    }
}