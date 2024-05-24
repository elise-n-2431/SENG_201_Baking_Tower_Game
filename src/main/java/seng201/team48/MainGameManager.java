package seng201.team48;

import seng201.team48.models.Bowl;
import seng201.team48.services.BowlService;

import java.util.Random;
import java.util.function.Consumer;

/**
 * This class stores information about the current game session.
 * It manages holding state, GUI windows, and passing objects into controller classes.
 *
 * @author Hannah Grace, Elise Newman
 */

public class MainGameManager {
    TowerManager towerManager;
    UpgradeManager upgradeManager;
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
    private String preroundLocation;


    /**MainGameManager controls the navigation between different screens in the game
     * It also stores mid-game variables while moving between screens
     * @param setupScreenLauncher launches setupScreen
     * @param preroundScreenLauncher launches preroundScreen
     * @param mainScreenLauncher launches mainScreen
     * @param clearScreen removes children from pane to clear the page
     * @param shopScreenLauncher launches shopScreen
     * @param conclusionLauncher launches conclusion
     * @param recipeBookLauncher launches recipeBook
     */
    public MainGameManager(Consumer<MainGameManager> setupScreenLauncher, Consumer<MainGameManager> preroundScreenLauncher, Consumer<MainGameManager> mainScreenLauncher, Runnable clearScreen, Consumer<MainGameManager> shopScreenLauncher, Consumer<MainGameManager> conclusionLauncher, Consumer<MainGameManager> recipeBookLauncher) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.preroundScreenLauncher = preroundScreenLauncher;
        this.mainScreenLauncher = mainScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.clearScreen = clearScreen;
        this.towerManager = new TowerManager();
        this.upgradeManager = new UpgradeManager(new Random());
        this.conclusionLauncher = conclusionLauncher;
        this.recipeBookLauncher = recipeBookLauncher;

        launchSetupScreen();
    }

    /**Launches setup screen
     */
    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    /**Closes setup screen and goes to preround screen
     * Saves preround location, if from mainscreen random breaking is triggered
     */
    public void closeSetupScreen() {
        clearScreen.run();
        launchPreroundScreen();
        preroundLocation = "Setup";
    }

    /**Launches preround screen
     */
    public void launchPreroundScreen() {
        preroundScreenLauncher.accept(this);
    }

    /**Closes preround screen by clicking start button
     * Sends user to the main game screen
     */
    public void closePreroundStart() {
        clearScreen.run();
        launchMainScreen();
    }

    /**Closes preround screen by clicking the back button
     * Sends user to the setup screen and finishes resetting values for new game
     */
    public void closePreroundSetup() {
        setTotalMoney(0);
        clearScreen.run();
        launchSetupScreen();
    }

    /**Launches main screen */
    public void launchMainScreen() { mainScreenLauncher.accept(this); }

    /**Closes main screen and takes user to set up screen */
    public void closeMainScreenHome() {
        clearScreen.run();
        launchSetupScreen();
    }
    /**Launches shop screen and saves previous screen for return */
    public void launchShopScreen(String previousScreen1){
        clearScreen.run();
        previousScreenShop = previousScreen1;
        shopScreenLauncher.accept(this);
    }
    /**Returns to the page user was on prior to launching */
    public void closeShopScreen(){
        clearScreen.run();
        switch (previousScreenShop) {
            case "MainScreen" -> launchMainScreen();
            case "Preround" -> {
                preroundLocation = "Shop";
                launchPreroundScreen();

            }
        }
    }
    /**Launches conclusion screen*/
    public void launchConclusionScreen() {conclusionLauncher.accept(this);}
    /**Closes main screen and takes user to conclusion screen*/
    public void closeMainScreenConclusion() {
        clearScreen.run();
        launchConclusionScreen();
    }
    /**Closes conclusion screen and takes user to setup screen*/
    public void closeConclusionScreen() {
        clearScreen.run();
        launchSetupScreen();
    }
    /**Closes main screen and saves "MainScreen" when opening preround screen to trigger random event */
    public void closeMainScreenPreRound(){
        clearScreen.run();
        preroundLocation = "MainScreen";
        launchPreroundScreen();
    }
    /**Launches recipe book and saves past location as a parameter
     * @param previousScreen1 past screen location String
     */
    public void launchRecipeBook(String previousScreen1){
        clearScreen.run();
        previousScreen = previousScreen1;
        recipeBookLauncher.accept(this);}

    /**Closes recipe book and opens saved past screen location's screen*/
    public void closeRecipeBook(){
        clearScreen.run();
        switch (previousScreen) {
            case "MainScreen" -> launchMainScreen();
            case "Setup" -> launchSetupScreen();
            case "Preround" -> {
                preroundLocation = "Recipe";
                launchPreroundScreen();
            }
        }
    }


    /**Gets tower manager
     * @return TowerManager
     */
    public TowerManager getTowerManager() {
        return towerManager;
    }
    /**Gets upgrade manager
     * @return UpgradeManager
     */
    public UpgradeManager getUpgradeManager() { return upgradeManager; }


    /**Get game difficulty
     * @return gameDifficulty
     */
    public String getGameDifficulty(){
        return gameDifficulty;
    }

    /**Sets the number of bowls based on round difficulty
     * @return number of small bowls Integer
     */
    public Integer getNumSmall(){
        switch (roundDifficulty) {
            case "Hard: 6 small + 2 large", "Medium: 6 small" -> {
                return 6;}
            case "Easy: 4 small" -> {
                return 4;}
        }
        return null;
    }
    /**Sets the number of bowls based on round difficulty
     * @return number of large bowls Integer
     */
    public Integer getNumLarge(){
        switch (roundDifficulty) {
            case "Hard: 6 small + 2 large" -> {
                return 2;}
            case "Medium: 6 small", "Easy: 4 small" -> {
                return 0;}
        }
        return null;
    }

    /**Gets the money total
     * @return totalMoney Integer
     */
    public Integer getTotalMoney() {
        return totalMoney;
    }

    /**Decreases totalMoney based on price of purchasable
     * @param price of item from shop Integer
     */
    public void deductTotalMoney(int price) {
        this.totalMoney -= price;
    }
    /**Increases totalMoney based on price of product
     * @param price of product produced
     */
    public void addTotalMoney(int price) {
        this.totalMoney += price;
    }
    /**Checks if conclusion page should congratulate player or be sad
     * @return success Boolean
     */
    public Boolean getSuccess() { return success;}

    /**Get name of player
     * @return name
     */
    public String getName(){
        return name;
    }

    /**Get number of rounds in current game (between 5 and 15)
     * @return numRounds
     */
    public Integer getNumRounds(){
        return numRounds;
    }

    /**Get number of current round
     * @return currentRound
     */
    public Integer getCurrentRound(){
        return currentRound;
    }

    /**Returns value of progress bar for tower slot: 1
     * @return reload progress Double
     */
    public double getReload1Temp() { return reload1Temp; }
    /**Returns value of progress bar for tower slot: 2
     * @return reload progress Double
     */
    public double getReload2Temp() { return reload2Temp; }
    /**Returns value of progress bar for tower slot: 3
     * @return reload progress Double
     */
    public double getReload3Temp() { return reload3Temp; }
    /**Returns value of progress bar for tower slot: 4
     * @return reload progress Double
     */
    public double getReload4Temp() { return reload4Temp; }
    /**Returns value of progress bar for tower slot: 5
     * @return reload progress Double
     */
    public double getReload5Temp() { return reload5Temp; }


    /**Increase current round by 1, Called after round completion.
     */
    public void updateRounds(){
        currentRound += 1;
    }

    /**Changes the value of success
     * @param success whether the player won or lost Boolean
     */
    public void setSuccess(Boolean success) { this.success = success; }

    /**Sets name of user
     * @param name their name, between 3-15 alphanumeric characters
     */
    public void setName(String name) {
        this.name = name;
    }

    /**Sets the number of rounds the game is played for
     * @param numRounds Integer based on difficulty
     */
    public void setNumRounds(Integer numRounds) {
        this.numRounds = numRounds;
    }

    /**Sets game difficulty for whole game
     * @param gameDifficulty String ("Easy", "Medium" or "Hard")
     */
    public void setGameDifficulty(String gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    /**Sets round difficulty each round
     * @param roundDifficulty String ("Easy", "Medium" or "Hard")
     */
    public void setRoundDifficulty(String roundDifficulty) {
        this.roundDifficulty = roundDifficulty;
    }

    /**Sets the totalMoney stored
     * @param totalMoney is the money the user can use to buy objects
     */
    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    /** Sets the value of the progress bar for tower slot: 1
     * @param reload1Temp reload progress Double
     */
    public void setReload1Temp(double reload1Temp) {
        this.reload1Temp = reload1Temp;
    }
    /** Sets the value of the progress bar for tower slot: 2
     * @param reload2Temp reload progress Double
     */
    public void setReload2Temp(double reload2Temp) {
        this.reload2Temp = reload2Temp;
    }
    /** Sets the value of the progress bar for tower slot: 3
     * @param reload3Temp reload progress Double
     */
    public void setReload3Temp(double reload3Temp) {
        this.reload3Temp = reload3Temp;
    }
    /** Sets the value of the progress bar for tower slot: 4
     * @param reload4Temp reload progress Double
     */
    public void setReload4Temp(double reload4Temp) {
        this.reload4Temp = reload4Temp;
    }
    /** Sets the value of the progress bar for tower slot: 5
     * @param reload5Temp reload progress Double
     */
    public void setReload5Temp(double reload5Temp) {
        this.reload5Temp = reload5Temp;
    }

    /**Sets and returns the bowl location based on step size
     * @return new bowlLocation double
     */
    public double updateBowlStepSize(){
        bowlLocation = bowlLocation + bowlStepSize;
        return bowlLocation;
    }

    /**Generates and sets bowl step size based on game difficulty */
    public void setBowlStepSize(){
        switch (gameDifficulty) {
            case "Easy" -> { bowlStepSize = 2;}
            case "Medium" -> { bowlStepSize = 4;}
            case "Hard" -> { bowlStepSize = 6;}
        }
    }

    /**Gets bowl location
     * @return bowl location Double
     */
    public double getBowlLocation(){
        return bowlLocation;
    }

    /**Sets bowl location to start of track at -25 on x-axis */
    public void resetBowlLocation(){
        bowlLocation = -25 - bowlStepSize;
    }

    /**Sets start of round
     * @param isStartOfRound boolean true = is start of round, false is mid-round
     */
    public void setIsStartOfRound(boolean isStartOfRound){
        this.isStartOfRound = isStartOfRound;
    }

    /**Gets start of round
     * @return isStartOfRound boolean
     */
    public boolean getIsStartOfRound(){
        return isStartOfRound;
    }

    /**Sets a bowl mid-game to allow shop / recipe access, rather than making a new bowl
     * @param currentBowl is the active Bowl
     */
    public void setCurrentBowl(Bowl currentBowl){
        this.currentBowl = currentBowl;
    }

    /**Gets the saved current bowl
     * @return active current bowl of type Bowl
     */
    public Bowl getCurrentBowl(){
        return currentBowl;
    }

    /**Sets ingredient1Contents
     * @param ingredient1Contents is a string containing bowl items as a string for a text box
     */
    public void setIngredient1Contents(String ingredient1Contents){
        this.ingredient1Contents = ingredient1Contents;
    }

    /**Gets ingredient1Contents
     * @return contents of bowl in string form
     */
    public String getIngredient1Contents(){
        return ingredient1Contents;
    }

    /**Sets bowlService
     * @param bowlService is the service that gets the bowls
     */
    public void setBowlService(BowlService bowlService){
        this.bowlService = bowlService;
    }

    /**Gets the bowl service
     * @return bowlService
     */
    public BowlService getBowlService(){
        return bowlService;
    }

    /**Gets the location of the preround screen in the past pane
     * @return preRoundLocation
     */
    public String getPreroundLocation(){ return preroundLocation; }

    /**
     * Gets setup screen launcher
     * @return setup screen launcher
     */
    public Consumer<MainGameManager> getSetupScreenLauncher() {
        return setupScreenLauncher;
    }

    /**
     * Gets preround screen launcher
     * @return preround screen launcher
     */
    public Consumer<MainGameManager> getPreroundScreenLauncher() {
        return preroundScreenLauncher;
    }

    /**
     * Gets main screen launcher
     * @return main screen launcher
     */
    public Consumer<MainGameManager> getMainScreenLauncher() {
        return mainScreenLauncher;
    }

    /**
     * Gets shop screen launcher
     * @return shop screen launcher
     */
    public Consumer<MainGameManager> getShopScreenLauncher() {
        return shopScreenLauncher;
    }

    /**
     * Gets launcher for the end screen
     * @return conclusion screen launcher
     */
    public Consumer<MainGameManager> getConclusionLauncher() {
        return conclusionLauncher;
    }

    /**
     * Gets launcher for recipe book screen
     * @return recipe book launcher
     */
    public Consumer<MainGameManager> getRecipeBookLauncher() {
        return recipeBookLauncher;
    }

    /**
     * Gets clear screen Runnable
     * @return Runnable to clear screen
     */
    public Runnable getClearScreen() {
        return clearScreen;
    }
}
