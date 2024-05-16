package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.MainGameManager;
import seng201.team0.TowerManager;

public class ConclusionController {
    @FXML
    public Button backButton;
    @FXML
    public Label successOrFailure;
    TowerManager towerManager;
    MainGameManager mainGameManager;

    public ConclusionController(MainGameManager mainGameManager) {
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
    }
    @FXML
    private void onBackClicked () {
        mainGameManager.closeConclusionScreen();
    }
}
