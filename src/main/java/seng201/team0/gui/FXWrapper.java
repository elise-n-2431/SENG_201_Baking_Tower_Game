//TODO create towerManager, mainscreen(and its controllers) navigate to shopinventory


package seng201.team0.gui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.TowerManager;

import java.io.IOException;

public class FXWrapper {
    @FXML
    private Pane pane;
    private Stage stage;

    public void init(Stage stage){
        this.stage = stage;
        // Changes: TowerManager becomes MainGameInfo.
        new TowerManager(this::launchSetupScreen, this::launchMainScreen, this::clearPane);
    }
    public void launchSetupScreen(TowerManager towerManager) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new SetupController(towerManager));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Rocket Manager Setup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

    public void launchMainScreen(TowerManager towerManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/main_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new MainScreenController(towerManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Tower Manager Main Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchShopInventoryScreen(TowerManager towerManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/ShopInventory.fxml"));
            mainScreenLoader.setControllerFactory(param -> new ShopInventoryController(towerManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Shop Inventory Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
