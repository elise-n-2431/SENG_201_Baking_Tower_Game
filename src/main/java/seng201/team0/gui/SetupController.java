package seng201.team0.gui;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseDragEvent;
import javafx.stage.Stage;
import seng201.team0.MainGameManager;
import seng201.team0.TowerManager;
import seng201.team0.models.Tower;
import seng201.team0.services.NameService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Controller for the setup_screen.fxml window
 * @author seng201 teaching team
 */
public class SetupController {
    // Instantiate manager objects to control game state
    TowerManager towerManager;
    MainGameManager mainGameManager;

    NameService nameService = new NameService();

    private int selectedTowerIndex = -1;

    private final Tower[] selectedTowers = new Tower[3];

    @FXML
    private TextField nameTextField;

    @FXML
    private Slider numRoundsSlider;

    @FXML
    private ChoiceBox<String> gameDifficultyChoiceBox;

    @FXML
    private Button flourTowerButton;

    @FXML
    private Button waterTowerButton;

    @FXML
    private Button sugarTowerButton;

    @FXML
    private Button milkTowerButton;

    @FXML
    private Button selectedTower1Button;

    @FXML
    private Button selectedTower2Button;

    @FXML
    private Button selectedTower3Button;

    @FXML
    private Button startGameButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Label statsResourceTypeLabel;

    @FXML
    private Label statsReloadSpeedLabel;

    @FXML
    private Label statsResourceAmountLabel;

    @FXML
    private Label statsLevelLabel;

    public SetupController(MainGameManager mainGameManager){
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
    }

    @FXML
    public void initialize(){
        List<Button> selectedTowerButtons = List.of(selectedTower1Button, selectedTower2Button, selectedTower3Button);
        List<Button> towerButtons = List.of(flourTowerButton, waterTowerButton, sugarTowerButton, milkTowerButton);
        errorLabel.setText("");

        /* Loops through tower index and sets up on click functionality.
        * Calls updateStats. Sets selectedTowerIndex to index of clicked button. */
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            towerButtons.get(i).setOnAction(event -> {
                updateStats(towerManager.getDefaultTowers().get(finalI));
                selectedTowerIndex = finalI;
                towerButtons.forEach(button -> {
                    if (button == towerButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
        for (int i = 0; i < selectedTowerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            selectedTowerButtons.get(i).setOnAction(event -> {
                if (selectedTowerIndex != -1) {
                    selectedTowerButtons.get(finalI).setText(towerManager.getDefaultTowers().get(selectedTowerIndex).getName());
                    selectedTowers[finalI] = towerManager.getDefaultTowers().get(selectedTowerIndex);
                }
            });
        }

        /* Sets number of rounds based on user input */
        numRoundsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mainGameManager.setNumRounds(newValue.intValue());
            mainGameManager.setCurrentRound(1);
            mainGameManager.setRemainingRounds(mainGameManager.getNumRounds() - 1);
        });
    }
    /* Displays relevant tower info in the window -SHOULD IT BE @FXML? */
    public void updateStats(Tower tower){
        statsResourceTypeLabel.setText(tower.getResourceType());
        statsReloadSpeedLabel.setText(String.valueOf(tower.getReloadSpeed()));
        statsResourceAmountLabel.setText(String.valueOf(tower.getResourceAmount()));
    }
    /* Sends the information to the relevant classes - tower or */
    @FXML
    private void onStartClicked() {
        String name = nameTextField.getText();
        if (nameService.isValidName(name)) {
            mainGameManager.setName(name);
            towerManager.setPlayerTowers(Arrays.stream(selectedTowers).filter((Objects::nonNull)).toList());
            mainGameManager.closeSetupScreen();
        }
        else {
            errorLabel.setText("Name must be between 3 and 15 characters, and contain no special characters");
        }
    }

    @FXML
    private void onDifficultyChange() {
        mainGameManager.setGameDifficulty(gameDifficultyChoiceBox.getValue());
    }

    public void onNumRoundsChange(MouseDragEvent mouseDragEvent) {
        System.out.println("Number of rounds changed");
        mainGameManager.setNumRounds((int) numRoundsSlider.getValue());
    }

    /* Prefilled in content
    @FXML
    private Label defaultLabel;

    @FXML
    private Button defaultButton;

    private CounterService counterService;

    public void init(Stage stage) {
        counterService = new CounterService();
    }


    @FXML
    public void onButtonClicked() {
        System.out.println("Button has been clicked");
        counterService.incrementCounter();

        int count = counterService.getCurrentCount();
        defaultLabel.setText(Integer.toString(count));
    }*/
}
