package seng201.team0;

import seng201.team0.models.Tower;

import java.util.List;
import java.util.function.Consumer;

/**
 * This class stores information about the current game session. EQUIVALENT TO ROCKETMANAGER IN TUTORIAL
 * It manages holding state, GUI windows, and passing objects into controller classes.
 * An
 * @author Hannah Grace, Elise Newman
 */

public class MainGameManager {
    TowerManager towerManager;
    private final Consumer<MainGameManager> setupScreenLauncher;
    private final Consumer<MainGameManager> preroundScreenLauncher;
    private final Consumer<MainGameManager> mainScreenLauncher;
    private final Consumer<MainGameManager> shopScreenLauncher;
    private final Consumer<MainGameManager> conclusionLauncher;
    private final Runnable clearScreen;
    private String gameDifficulty;
    private Integer roundDifficulty;
    private String name;
    private Integer numRounds;
    private Integer currentRound;
    private Integer remainingRounds;

    private String moneyPerRound;

    public MainGameManager(Consumer<MainGameManager> setupScreenLauncher, Consumer<MainGameManager> preroundScreenLauncher, Consumer<MainGameManager> mainScreenLauncher, Runnable clearScreen, Consumer<MainGameManager> shopScreenLauncher, Consumer<MainGameManager> conclusionLauncher) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.preroundScreenLauncher = preroundScreenLauncher;
        this.mainScreenLauncher = mainScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.clearScreen = clearScreen;
        this.towerManager = new TowerManager();
        this.conclusionLauncher = conclusionLauncher;

        launchSetupScreen();
    }

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    public void closeSetupScreen() {
        clearScreen.run();
        launchPreroundScreen();
    }

    public void launchPreroundScreen() {
        preroundScreenLauncher.accept(this);
    }

    public void closePreroundScreen() {
        clearScreen.run();
        launchMainScreen();
    }

    public void launchMainScreen() { mainScreenLauncher.accept(this); }

    public void closeMainScreenHome() {
        clearScreen.run();
        launchSetupScreen();
    }
    public void launchShopScreen() { shopScreenLauncher.accept(this); }
    public void closeShopScreen(){
        clearScreen.run();
        launchMainScreen();}

    public void closeMainScreenShop() {
        clearScreen.run();
        launchShopScreen();
        //how is data stored - continue progress when return
    }
    public void launchConclusionScreen() {conclusionLauncher.accept(this);}
    public void closeMainScreenConclusion() {
        clearScreen.run();
        launchConclusionScreen();
        //how is data stored - continue progress when return
    }
    public void closeConclusionScreen() {
        clearScreen.run();
        launchSetupScreen();
    }

    public TowerManager getTowerManager() {
        return towerManager;
    }


    /**
     * Get game difficulty
     * @return gameDifficulty
     */
    public String getGameDifficulty(){
        return gameDifficulty;
    }

    /**
     * Get difficulty of current round
     * @return roundDifficulty
     */
    public Integer getRoundDifficulty() {
        return roundDifficulty;
    }
    public String getMoneyPerRound() {
        return moneyPerRound;
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

    public void setGameDifficulty(String gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    public void setRoundDifficulty(Integer roundDifficulty) {
        this.roundDifficulty = roundDifficulty;
    }

    public void setRemainingRounds(Integer remainingRounds) {
        this.remainingRounds = remainingRounds;
    }
    public void setMoneyPerRound(String moneyPerRound) {
        this.moneyPerRound = moneyPerRound;
    }
}