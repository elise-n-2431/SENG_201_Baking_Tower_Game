package seng201.team48.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;

import java.util.Objects;
import java.util.Random;

/**Sets variables for roundDifficulty and broken tower alerts */
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
    private Random rand;
    private Integer randInt;
    private Alert brokenTowerAlert;

    /**Constucts controller by calling mainGameManager
     * @param mainGameManager main game functionality
     */
    public PreroundController(MainGameManager mainGameManager){
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
    }

    /**On startup
     * defines gamedifficulty and sets broken tower alerts
     */
    public void initialize() {
        if (mainGameManager.getCurrentRound() > 1) {
            setGreetingText("Congrats, you've completed round " + (mainGameManager.getCurrentRound() - 1) + "! Ready for the next one?");
            if (Objects.equals(mainGameManager.getPreroundLocation(), "MainScreen")){
                brokenTowerAlert = new Alert(Alert.AlertType.INFORMATION);
                rand = new Random();
                switch (mainGameManager.getGameDifficulty()) {
                    case "Easy": {
                        randInt = rand.nextInt(60);
                    }
                    case "Medium": {
                        randInt = rand.nextInt(45);
                    }
                    case "Hard": {
                        randInt = rand.nextInt(6);
                    }
                }
                for (int i = 0; i < towerManager.getPlayerTowers().size(); i++) {
                    if ((randInt - 1) == i) {
                        towerManager.getPlayerTowers().get(i).setBroken(true);
                        brokenTowerAlert.setTitle("Uh oh...");
                        brokenTowerAlert.setHeaderText(towerManager.getPlayerTowers().get(i).getResourceType() + " Tower is broken.");
                        brokenTowerAlert.setContentText("You must fix this tower with a repair kit from the shop before you can use it again.");
                        brokenTowerAlert.showAndWait();
                    }
                }
            }
        }
        else {
            setGreetingText("Welcome to the game, " + mainGameManager.getName() + "! Prepare to get baking...");
        }
        greetingLabel.setText(greetingText);
        currentRoundLabel.setText("Current round: " + mainGameManager.getCurrentRound() + " of " + mainGameManager.getNumRounds());
        gameDifficultyLabel.setText("Game difficulty: " + mainGameManager.getGameDifficulty());
    }

    /**Sets roundDifficulty value based on num carts inputted */
    public void onNumCartsChanged() {
        mainGameManager.setRoundDifficulty(numCartsChoiceBox.getValue());
    }

    /**Closes preround screen */
    public void onStartClicked(ActionEvent actionEvent) {
        mainGameManager.closePreroundStart();
    }

    /**Sets greeting text using name input */
    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }

    /**Go back to preround setup */
    public void onBackClicked(){
        mainGameManager.closePreroundSetup();
    }

    /**Enters shop */
    public void onShopClicked(){
        mainGameManager.launchShopScreen("Preround");
    }

    /**Enters recipeBook */
    public void onRecipeClicked(){
        mainGameManager.launchRecipeBook("Preround");
    }
}
