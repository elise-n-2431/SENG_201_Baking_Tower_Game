package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import seng201.team0.TowerManager;
import seng201.team0.models.Tower;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Controller for the main.fxml window
 * @author seng201 teaching team
 */
public class SetupController {
    private TowerManager towerManager;

    // Constructor taking RocketManager as a parameter
    public SetupController(TowerManager towerManager) {
        this.towerManager = towerManager;
    }

    private int selectedTowerIndex = -1;
    private final Tower[] selectedTowers = new Tower[3];

    public void init(Stage primaryStage) {
        List<Button> selectedTowerButtons = List.of(selectedTower1Button, selectedTower2Button, selectedTower3Button);
        List<Button> towerButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button, tower6Button);
        //give ids for tower slots on fxml

        /* Loops through tower index and sets up onclick functionality */
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            towerButtons.get(i).setOnAction(event -> {
                updateStats(towerManager.getTowerSelectionList().get(finalI));
                selectedTowersIndex = finalI; //TODO why doesn't this work??
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
                    selectedTowerButtons.get(finalI).setText(towerManager.getTowerSelectionList().get(selectedTowerIndex).getName());
                    selectedTowers[finalI] = towerManager.getTowerSelectionList().get(selectedTowerIndex);
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
    public void updateStats(Tower tower) {
        resourceTypeLabel.setText(tower.getResourceAmount());
        reloadSpeedLabel.setText(tower.getReloadSpeed());
        resourceAmountLabel.setText(tower.getResourceAmount());
    }

    /* Sends the information to the relevant classes - tower or */
    @FXML
    private void onAcceptClicked() {

        towerManager.setName(nameTextField.getText());
        towerManager.setTowerSelectionList(Arrays.stream(selectedTowers).filter((Objects::nonNull)).toList());
        towerManager.closeSetupScreen();
    }
}