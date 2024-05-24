package seng201.team48.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;
/**
 * Displays the recipes for the user to use as a reference
 * @author Hannah Grace, Elise Newman
 */
public class RecipeBookController {
    @FXML
    public Button backButton;
    TowerManager towerManager;
    MainGameManager mainGameManager;

    /**Constructs recipeBook using mainGameManager
     * */
    public RecipeBookController(MainGameManager mainGameManager) {
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
    }
    public void initialize(){}

    /**Close recipe book screen */
    @FXML
    private void onBackClicked () {
        mainGameManager.closeRecipeBook();
    }
}

