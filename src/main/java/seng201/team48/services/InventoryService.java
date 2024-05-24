package seng201.team48.services;

import javafx.scene.control.Alert;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;
import seng201.team48.models.Tower;

/**
 * Performs validation for the inventory, activates and reserves towers and shows alerts
 * @author Hannah Grace, Elise Newman
 */
public class InventoryService {
    TowerManager towerManager;

    /**
     * Checks if the tower is the correct level for an upgrade
     * @param tower Tower object
     * @param requiredLevel Integer of tower level
     * @return Boolean of whether levels match
     */
    public boolean isCorrectUpgradeLevel(Tower tower, int requiredLevel) {
        return tower.getLevel() == requiredLevel;
    }

    /**
     * Displays alerts for random events
     * @param alert is the alert instance
     * @param title "error"
     * @param header What went wrong
     * @param content More information about error
     */
    public void showAlert(Alert alert, String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Generates an error if the tower cannot be made into a reserve
     * @param mainGameManager manages main game variables
     * @return false if an error was generated, true if correct
     */
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

    /**
     * Generates an error if the tower cannot be made active
     * @param mainGameManager manages main game variables
     * @return false if an error has been generated, true if works
     */
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
