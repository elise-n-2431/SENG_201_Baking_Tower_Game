package seng201.team48.services;

import seng201.team48.models.Tower;

/**
 * Tower service class which validates tower selection.
 * @author Hannah Grace, Elise Newman
 */

public class TowerService {
    /**
     * Checks whether the player has selected all three starting towers before beginning a round.
     * Run from onStartClicked in SetupController.java
     * @param selectedTowers List of Tower objects (length: 3)
     * @return false if any index in the array is null, else returns true
     */
    public boolean areAllTowersSelected(Tower[] selectedTowers) {
        for(int i = 0; i < selectedTowers.length; i++) {
            if(selectedTowers[i] == null) {
                return false;
            }
        }
        return true;
    }
}
