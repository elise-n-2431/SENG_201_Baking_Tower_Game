package seng201.team48.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;

public class PreroundController {
    private final TowerManager towerManager;
    private String greetingText = "Welcome to the game, ";
    MainGameManager mainGameManager;
    @FXML
    private Label greetingLabel;
    @FXML
    private Label currentRoundLabel;
    @FXML
    private Label gameDifficultyLabel;
    @FXML
    private ChoiceBox<String> numCartsChoiceBox;
    public Button backButton;
    public Button shopButton;
    public Button recipeButton;


    public PreroundController(MainGameManager mainGameManager){
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
    }

    public void initialize() {
        if (mainGameManager.getCurrentRound() > 1) {
            setGreetingText("Congrats, you've completed round " + (mainGameManager.getCurrentRound() - 1) + "! Ready for the next one?");
        }
        else {
            setGreetingText("Welcome to the game, " + mainGameManager.getName() + "! Prepare to get baking...");
        }
        greetingLabel.setText(greetingText);
        currentRoundLabel.setText("Current round: " + mainGameManager.getCurrentRound() + " of " + mainGameManager.getNumRounds());
        gameDifficultyLabel.setText("Game difficulty: " + mainGameManager.getGameDifficulty());
    }

    public void onNumCartsChanged() {
        mainGameManager.setRoundDifficulty(numCartsChoiceBox.getValue());
    }

    public void onStartClicked(ActionEvent actionEvent) {
        mainGameManager.closePreroundStart();
    }

    public String getGreetingText() {
        return greetingText;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }

    public void onBackClicked(){
        mainGameManager.closePreroundSetup();
    }
    public void onShopClicked(){
        mainGameManager.launchShopScreen("Preround");
    }
    public void onRecipeClicked(){
        mainGameManager.launchRecipeBook("Preround");
    }
}
