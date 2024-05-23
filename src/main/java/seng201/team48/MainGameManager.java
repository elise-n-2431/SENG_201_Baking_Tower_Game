package seng201.team48;

import seng201.team48.models.Bowl;
import seng201.team48.services.BowlService;

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
    private final Consumer<MainGameManager> recipeBookLauncher;
    private final Runnable clearScreen;
    private String gameDifficulty = "Medium";
    private String roundDifficulty = "Medium: 6 small";
    private String name;
    private Integer numRounds = 5; // Initialise default numRounds and currentRound to prevent "null" cases
    private Integer currentRound = 1;
    private Boolean success = true;
    private double reload1Temp = 0;
    private double reload2Temp = 0;
    private double reload3Temp = 0;
    private double reload4Temp = 0;
    private double reload5Temp = 0;
    private Integer totalMoney = 500; // CHANGE BACK LATER
    private String previousScreen;
    private double bowlStepSize;
    private double bowlLocation = -25;
    private boolean isStartOfRound = true;
    private Bowl currentBowl;
    private String ingredient1Contents;
    private BowlService bowlService = new BowlService();
    private String previousScreenShop;



    public MainGameManager(Consumer<MainGameManager> setupScreenLauncher, Consumer<MainGameManager> preroundScreenLauncher, Consumer<MainGameManager> mainScreenLauncher, Runnable clearScreen, Consumer<MainGameManager> shopScreenLauncher, Consumer<MainGameManager> conclusionLauncher, Consumer<MainGameManager> recipeBookLauncher) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.preroundScreenLauncher = preroundScreenLauncher;
        this.mainScreenLauncher = mainScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.clearScreen = clearScreen;
        this.towerManager = new TowerManager();
        this.conclusionLauncher = conclusionLauncher;
        this.recipeBookLauncher = recipeBookLauncher;

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

    public void closePreroundStart() {
        clearScreen.run();
        launchMainScreen();
    }
    public void closePreroundSetup() {
        clearScreen.run();
        launchSetupScreen();
    }

    public void launchMainScreen() { mainScreenLauncher.accept(this); }

    public void closeMainScreenHome() {
        clearScreen.run();
        launchSetupScreen();
    }
    public void launchShopScreen(String previousScreen1){
        clearScreen.run();
        previousScreenShop = previousScreen1;
        shopScreenLauncher.accept(this);}
    public void closeShopScreen(){
        clearScreen.run();
        switch (previousScreenShop) {
            case "MainScreen" -> launchMainScreen();
            case "Preround" -> launchPreroundScreen();
        }
    }
    public void launchConclusionScreen() {conclusionLauncher.accept(this);}
    public void closeMainScreenConclusion() {
        clearScreen.run();
        launchConclusionScreen();
    }
    public void closeConclusionScreen() {
        clearScreen.run();
        launchSetupScreen();
    }
    public void closeMainScreenPreRound(){
        clearScreen.run();
        launchPreroundScreen();
    }
    public void launchRecipeBook(String previousScreen1){
        clearScreen.run();
        previousScreen = previousScreen1;
        recipeBookLauncher.accept(this);}
    public void closeRecipeBook(){
        clearScreen.run();
        switch (previousScreen) {
            case "MainScreen" -> launchMainScreen();
            case "Setup" -> launchSetupScreen();
            case "Preround" -> launchPreroundScreen();
        }

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
    public String getRoundDifficulty() {
        return roundDifficulty;
    }
    public Integer getNumSmall(){
        switch (roundDifficulty) {
            case "Hard: 6 small + 2 large", "Medium: 6 small" -> {
                return 6;}
            case "Easy: 4 small" -> {
                return 4;}
        }
        return null;
    }
    public Integer getNumLarge(){
        switch (roundDifficulty) {
            case "Hard: 6 small + 2 large" -> {
                return 2;}
            case "Medium: 6 small", "Easy: 4 small" -> {
                return 0;}
        }
        return null;
    }
    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void deductTotalMoney(int price) {
        this.totalMoney -= price;
    }
    public void addTotalMoney(int price) {
        this.totalMoney += price;
    }

    public Boolean getSuccess() { return success;}

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
    public double getReload1Temp() { return reload1Temp; }
    public double getReload2Temp() { return reload2Temp; }
    public double getReload3Temp() { return reload3Temp; }
    public double getReload4Temp() { return reload4Temp; }
    public double getReload5Temp() { return reload5Temp; }


    /**
     * Increase current round by 1, Called after round completion.
     */
    public void updateRounds(){
        currentRound += 1;
    }

    public void setSuccess(Boolean success) { this.success = success; }
    public void setName(String name) {
        this.name = name;
    }

    public void setNumRounds(Integer numRounds) {
        this.numRounds = numRounds;
    }

    public void setCurrentRound(Integer currentRound) {
        this.currentRound = 1;
    }

    public void setGameDifficulty(String gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    public void setRoundDifficulty(String roundDifficulty) {
        this.roundDifficulty = roundDifficulty;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setReload1Temp(double reload1Temp) {
        this.reload1Temp = reload1Temp;
    }
    public void setReload2Temp(double reload2Temp) {
        this.reload2Temp = reload2Temp;
    }
    public void setReload3Temp(double reload3Temp) {
        this.reload3Temp = reload3Temp;
    }
    public void setReload4Temp(double reload4Temp) {
        this.reload4Temp = reload4Temp;
    }
    public void setReload5Temp(double reload5Temp) {
        this.reload5Temp = reload5Temp;
    }
    public double updateBowlStepSize(){
        bowlLocation = bowlLocation + bowlStepSize;
        return bowlLocation;
    }
    public void setBowlStepSize(){
        System.out.println(gameDifficulty);
        switch (gameDifficulty) {
            case "Easy" -> { bowlStepSize = 2;}
            case "Medium" -> { bowlStepSize = 4;}
            case "Hard" -> { bowlStepSize = 6;}
        }
    }
    public double getBowlLocation(){
        return bowlLocation;
    }
    public void resetBowlLocation(){
        bowlLocation = -25 - bowlStepSize;
    }
    public void setIsStartOfRound(boolean isStartOfRound){
        this.isStartOfRound = isStartOfRound;
    }
    public boolean getIsStartOfRound(){
        return isStartOfRound;
    }
    public void setCurrentBowl(Bowl currentBowl){
        this.currentBowl = currentBowl;
    }
    public Bowl getCurrentBowl(){
        return currentBowl;
    }
    public void setIngredient1Contents(String ingredient1Contents){
        this.ingredient1Contents = ingredient1Contents;
    }
    public String getIngredient1Contents(){
        return ingredient1Contents;
    } //comment
    public void setBowlService(BowlService bowlService){
        this.bowlService = bowlService;
    }
    public BowlService getBowlService(){
        return bowlService;
    }
}
