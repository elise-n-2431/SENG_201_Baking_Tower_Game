package seng201.team0;

import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TowerManager {
    //    private final Consumer<TowerManager> setupScreenLauncher;
//    private final Consumer<TowerManager> mainScreenLauncher;
//    private final Runnable clearScreen;
    private final List<Tower> defaultTowers = new ArrayList<>();
    private List<Tower> playerTowers;

    /**
     * Constructor
     */
    public TowerManager() {
//        this.setupScreenLauncher = setupScreenLauncher; // MOVED TO MAIN GAME MANAGER
//        this.mainScreenLauncher = mainScreenLauncher;
//        this.clearScreen = clearScreen;

        Tower flourTower = new Tower("Flour", "Creates flour", 5, 3, "Flour", 10, 1);
        Tower waterTower = new Tower("Eggs", "Eggs", 5, 3, "Water", 20, 2);
        Tower sugarTower = new Tower("Sugar", "Processes sugar", 5, 3, "Sugar", 5, 2);
        Tower dairyTower = new Tower("Milk", "Creates butter, milk and cream", 6, 3, "Butter", 8, 3);

        defaultTowers.addAll(List.of(flourTower, waterTower, sugarTower, dairyTower));
//        launchSetupScreen();
    }

// MOVED TO MAIN GAME MANAGER
//    public void launchSetupScreen() {
//        setupScreenLauncher.accept(this);
//    }
//
//    public void closeSetupScreen() {
//        clearScreen.run();
//        launchMainScreen();
//    }
//
//    public void launchMainScreen() {
//        // Screen where rounds will be played
//        mainScreenLauncher.accept(this);
//    }
//
//    public void closeRoundScreen() {
//        clearScreen.run();
//        // LAUNCH NEXT SCREEN -- Shop/Inventory? Also have a quit function with System.exit(0)
//    }

    public List<Tower> getDefaultTowers() {
        return defaultTowers;
    }

    public List<Tower> getPlayerTowers() {
        return playerTowers;
    }

    public void setPlayerTowers(List<Tower> playerTowers) {
        this.playerTowers = playerTowers;
    }

}
