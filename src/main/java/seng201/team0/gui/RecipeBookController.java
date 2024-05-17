package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.MainGameManager;
import seng201.team0.TowerManager;

public class RecipeBookController {
    @FXML
    public Button backButton;
    TowerManager towerManager;
    MainGameManager mainGameManager;

    public RecipeBookController(MainGameManager mainGameManager) {
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
    }
    public void initialize(){}
    @FXML
    private void onBackClicked () {
        mainGameManager.closeRecipeBook();
    }
}

