package seng201.team48.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;
/**Conclusion controller controls the conclusion screen and displays a message, either positive if you won
 * or negative if you lost
 */
public class ConclusionController {
    @FXML
    public Button backButton;
    @FXML
    public Label successOrFailure;
    TowerManager towerManager;
    MainGameManager mainGameManager;

    /**Setup for conclusion controller, sets up mainGameManager
     * @param mainGameManager is responsible for the navigation and getting / setting variables
     */
    public ConclusionController(MainGameManager mainGameManager) {
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
    }

    /**Runs on startup, sets the displayed text */
    public void initialize() {
        if (mainGameManager.getSuccess() == Boolean.TRUE){
            String successText = "Congratulations " + mainGameManager.getName() + ", you have won the game!";
            successOrFailure.setText(successText);
        } else{
            successOrFailure.setText("You have failed, try again next time!");
        }
    }

    /**Navigates back to the main screen */
    @FXML
    private void onBackClicked () {
        mainGameManager.closeConclusionScreen();
    }
}
