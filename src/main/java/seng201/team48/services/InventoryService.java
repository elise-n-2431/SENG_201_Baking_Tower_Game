package seng201.team48.services;

import javafx.scene.control.Alert;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;
import seng201.team48.models.Tower;

public class InventoryService {
    TowerManager towerManager;

    public boolean isCorrectUpgradeLevel(Tower tower, int requiredLevel) {
        return tower.getLevel() == requiredLevel;
    }

    public void showAlert(Alert alert, String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public boolean canMakeReserve(MainGameManager mainGameManager) {
        towerManager = mainGameManager.getTowerManager();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        // An active item can go into reserve if the length of active towers list is > 1 and reserve list is < 5
        if (towerManager.getPlayerTowers().size() > 1 && towerManager.getReserveTowers().size() < 5) {
            return true;
        } else if (towerManager.getPlayerTowers().size() <= 1) {
            showAlert(alert, "Error", "Cannot move tower to reserve",
                    "You must have at least 1 active tower at all times");
        } else if (towerManager.getReserveTowers().size() == 5) {
            showAlert(alert, "Error", "Cannot move tower to reserve",
                    "Reserve station inventory is already full");
        } else {
            showAlert(alert, "Error", "Cannot move tower to reserve",
                    "An unexpected event occurred");
        }
        return false;
    }

    public boolean canMakeActive(MainGameManager mainGameManager) {
        towerManager = mainGameManager.getTowerManager();
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (towerManager.getPlayerTowers().size() < 5) {
            return true;
        } else if (towerManager.getPlayerTowers().size() == 5) {
            showAlert(alert, "Error", "Cannot activate tower",
                    "You already have five active towers");
        } else {
            showAlert(alert, "Error", "Cannot activate tower",
                    "An unexpected event occurred");
        }
        return false;
    }
}
