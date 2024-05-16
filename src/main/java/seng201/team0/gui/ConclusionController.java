package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
    public void initialize() {
        if (mainGameManager.getSuccess() == Boolean.TRUE){
            String successText = "Congratulations " + mainGameManager.getName() + ", you have won the game!";
            successOrFailure.setText(successText);
        } else{
            successOrFailure.setText("Sorry, you have failed, try again");
        }
    }
    @FXML
    private void onBackClicked () {
        mainGameManager.closeConclusionScreen();
    }
}
