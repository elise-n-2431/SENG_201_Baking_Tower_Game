package seng201.team48.services;

import javafx.scene.control.Alert;
import seng201.team48.models.Tower;

public class InventoryService {

    public boolean isCorrectUpgradeLevel(Tower tower, int requiredLevel) {
        return tower.getLevel() == requiredLevel;
    }

    public void showAlert(Alert alert, String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
