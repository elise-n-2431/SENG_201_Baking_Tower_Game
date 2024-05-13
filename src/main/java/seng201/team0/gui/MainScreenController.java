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
public class MainScreenController {
    private TowerManager towerManager;

    // Constructor taking RocketManager as a parameter
    public MainScreenController(TowerManager towerManager) {
        this.towerManager = towerManager;
    }

    private int selectedTowerIndex = -1;
    private final Tower[] selectedTowers = new Tower[3];

    public void init() {

    }
}