package seng201.team48.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;

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

