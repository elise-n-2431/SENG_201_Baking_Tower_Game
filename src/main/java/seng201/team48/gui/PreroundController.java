package seng201.team48.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;

public class PreroundController {
    private final TowerManager towerManager;
    MainGameManager mainGameManager;
    @FXML
    private Label greetingLabel;
    @FXML
    private Label currentRoundLabel;
    @FXML
    private Label gameDifficultyLabel;
    @FXML
    private ChoiceBox<String> numCartsChoiceBox;

    public PreroundController(MainGameManager mainGameManager){
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
    }

    public void initialize() {
        greetingLabel.setText("Good to see you " + mainGameManager.getName() + "! Prepare to start round...");
        currentRoundLabel.setText("Current round: " + mainGameManager.getCurrentRound() + " of " + mainGameManager.getNumRounds());
        gameDifficultyLabel.setText("Game difficulty: " + mainGameManager.getGameDifficulty());
    }

    public void onNumCartsChanged() {
        mainGameManager.setRoundDifficulty(numCartsChoiceBox.getValue());
    }

    public void onStartClicked(ActionEvent actionEvent) {
        mainGameManager.closePreroundScreen();
    }
}
