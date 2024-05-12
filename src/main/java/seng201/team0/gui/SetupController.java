package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import seng201.team0.MainGameInfo;
import seng201.team0.TowerManager;
import seng201.team0.models.Tower;
import seng201.team0.services.CounterService;

import java.util.List;

/**
 * Controller for the setup_screen.fxml window
 * @author seng201 teaching team
 */
public class SetupController {
    TowerManager towerManager;

    private int selectedTowerIndex = -1;

    private final Tower[] selectedTowers = new Tower[3];

    @FXML
    private TextField nameTextField;

    @FXML
    private Slider numRoundsSlider;

    @FXML
    private ChoiceBox gameDifficultySlider;

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
    private Label statsResourceTypeLabel;

    @FXML
    private Label statsReloadSpeedLabel;

    @FXML
    private Label statsResourceAmountLabel;

    @FXML
    private Label statsLevelLabel;

    public SetupController(TowerManager towerManager){
        this.towerManager = towerManager;
    }

    public void initialisation(){
        List<Button> selectedTowerButtons = List.of(selectedTower1Button, selectedTower2Button, selectedTower3Button);
        List<Button> towerButtons = List.of(flourTowerButton, waterTowerButton, sugarTowerButton, milkTowerButton;

        /* Loops through tower index and sets up on click functionality */
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            towerButtons.get(i).setOnAction(event -> {
                updateStats(towerManager.getDefaultTowers().get(finalI)); //towerManager could be Inventory/MainGameInfo???
                selectedTowersIndex = finalI;
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
                    selectedTowerButtons.get(finalI).setText(towerManager.getDefaultRockets().get(selectedTowerIndex).getName());
                    selectedTowers[finalI] = towerManager.getDefaultRockets().get(selectedTowerIndex);
                }
            });
        }
        roundSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            for (int i = 0; i < selectedTowerButtons.size(); i++) {
                selectedTowerButtons.get(i).setDisable(i >= newValue.intValue());
            }
        });
    }
    /* Displays relevant tower info in the window -SHOULD IT BE @FXML? */
    public void updateStats(Tower tower){
        resourceTypeLabel.setText(tower.getResourceAmount());
        reloadSpeedLabel.setText(tower.getReloadSpeed());
        resourceAmountLabel.setText(tower.getResourceAmount());
    }
    /* Sends the information to the relevant classes - tower or */
    @FXML
    private void onAcceptClicked() {

        towerManager.setName(nameTextField.getText());
        towerManager.setRocketList(Arrays.stream(selectedRockets).filter((Objects::nonNull)).toList());
        towerManager.closeSetupScreen();
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
