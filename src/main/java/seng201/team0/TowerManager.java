package seng201.team0;

import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TowerManager {
    private final Consumer<TowerManager> setupScreenLauncher;
    private final Consumer<TowerManager> mainScreenLauncher;
    private final Runnable clearScreen;
    private final List<Tower> towerSelectionList = new ArrayList<>();

    /**
     * Constructor
     * @param setupScreenLauncher
     * @param mainScreenLauncher
     * @param clearScreen
     */
    public TowerManager(Consumer<TowerManager> setupScreenLauncher, Consumer<TowerManager> mainScreenLauncher, Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.mainScreenLauncher = mainScreenLauncher;
        this.clearScreen = clearScreen;

        Tower flourTower = new Tower("Flour Mill", "Creates flour", 5, 3, "Flour", 10, 1);
        Tower waterTower = new Tower("Water Tower", "Stores water", 5, 3, "Water", 20, 2);
        Tower sugarTower = new Tower("Sugarcane Mill", "Processes sugar", 5, 3, "Sugar", 5, 2);
        Tower dairyTower = new Tower("Dairy", "Creates butter, milk and cream", 6, 3, "Butter", 8, 3);

        towerSelectionList.addAll(List.of(flourTower, waterTower, sugarTower, dairyTower));
        launchSetupScreen();
    }


    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    public void closeSetupScreen() {
        clearScreen.run();
        launchMainScreen();
    }

    public void launchMainScreen() {
        // Screen where rounds will be played
        mainScreenLauncher.accept(this);
    }

    public void closeRoundScreen() {
        clearScreen.run();
        // LAUNCH NEXT SCREEN -- Shop/Inventory? Also have a quit function with System.exit(0)
    }
}
