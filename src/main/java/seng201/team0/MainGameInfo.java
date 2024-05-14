package seng201.team0;

import com.sun.tools.javac.Main;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * This class stores information about the current game session. EQUIVALENT TO ROCKETMANAGER IN TUTORIAL
 * It manages holding state, GUI windows, and passing objects into controller classes.
 * An
 * @author Hannah Grace, Elise Newman
 */

public class MainGameInfo {

    private Integer gameDifficulty;
    private Integer roundDifficulty;
    private String name;
    private Integer numRounds;
    private Integer currentRound;
    private Integer remainingRounds;

    // MOVED INTO TOWERMANAGER
//    public MainGameInfo(Consumer<MainGameInfo> setupScreenLauncher, Consumer<MainGameInfo> mainScreenLauncher, Runnable clearScreen) {
//        this.setupScreenLauncher = setupScreenLauncher;
//        this.mainScreenLauncher = mainScreenLauncher;
//        this.clearScreen = clearScreen;
//
//        Tower flourTower = new Tower("Flour Mill", "Creates flour", 5, 3, "Flour", 10, 1);
//        Tower waterTower = new Tower("Water Tower", "Stores water", 5, 3, "Water", 20, 2);
//        Tower sugarTower = new Tower("Sugarcane Mill", "Processes sugar", 5, 3, "Sugar", 5, 2);
//        Tower dairyTower = new Tower("Dairy", "Creates butter, milk and cream", 6, 3, "Butter", 8, 3);
//
//        towerSelectionList.addAll(List.of(flourTower, waterTower, sugarTower, dairyTower));
//        launchSetupScreen();
//    }


    /**
     * Get game difficulty
     * @return gameDifficulty
     */
    public Integer getGameDifficulty(){
        return gameDifficulty;
    }

    /**
     * Get difficulty of current round
     * @return roundDifficulty
     */
    public Integer getRoundDifficulty() {
        return roundDifficulty;
    }

    /**
     * Get name of player
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Get number of rounds in current game (between 5 and 15)
     * @return numRounds
     */
    public Integer getNumRounds(){
        return numRounds;
    }

    /**
     * Get number of current round
     * @return currentRound
     */
    public Integer getCurrentRound(){
        return currentRound;
    }

    /**
     * Increase current round by 1, and decrease remaining rounds by 1. Called after round completion.
     */
    public void updateRounds(){
        currentRound += 1;
        remainingRounds -= 1;
        //should it do anything if numRounds exceeded?
    }

    /**
     * Get number of remaining rounds in current game.
     * @return remainingRounds
     */
    public Integer getRemainingRounds(){
        return remainingRounds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumRounds(Integer numRounds) {
        this.numRounds = numRounds;
    }

    public void setCurrentRound(Integer currentRound) {
        this.currentRound = currentRound;
    }

    public void setGameDifficulty(Integer gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    public void setRoundDifficulty(Integer roundDifficulty) {
        this.roundDifficulty = roundDifficulty;
    }

    public void setRemainingRounds(Integer remainingRounds) {
        this.remainingRounds = remainingRounds;
    }
}
