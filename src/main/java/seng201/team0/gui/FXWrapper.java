//TODO create towerManager, mainscreen(and its controllers) navigate to shopinventory


package seng201.team0.gui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.MainGameManager;

import java.io.IOException;

public class FXWrapper {
    @FXML
    private Pane pane;
    private Stage stage;

    public void init(Stage stage){
        this.stage = stage;
        // Changes: TowerManager becomes MainGameInfo.
        new MainGameManager(this::launchSetupScreen, this::launchPreroundScreen, this::launchMainScreen, this::clearPane);
    }

    public void launchSetupScreen(MainGameManager mainGameManager) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new SetupController(mainGameManager));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Tower Game Setup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

    private void launchPreroundScreen(MainGameManager mainGameManager) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/preround_screen.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new PreroundController(mainGameManager));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Prepare for Round");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchMainScreen(MainGameManager mainGameManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/main_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new MainScreenController(mainGameManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Tower Game Main Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchShopInventoryScreen(MainGameManager mainGameManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/shop_inventory.fxml"));
            mainScreenLoader.setControllerFactory(param -> new ShopInventoryController(mainGameManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Shop Inventory Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
