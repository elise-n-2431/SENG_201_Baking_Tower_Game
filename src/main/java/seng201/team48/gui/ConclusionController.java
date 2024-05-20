package seng201.team48.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;

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
    public void initialize() {
        if (mainGameManager.getSuccess() == Boolean.TRUE){
            String successText = "Congratulations " + mainGameManager.getName() + ", you have won the game!";
            successOrFailure.setText(successText);
        } else{
            successOrFailure.setText("You have failed as a person, your mother is not proud of you, try again");
        }
    }
    @FXML
    private void onBackClicked () {
        mainGameManager.closeConclusionScreen();
    }
}
