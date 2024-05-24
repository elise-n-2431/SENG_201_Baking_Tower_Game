package seng201.team48.gui;
import javafx.animation.AnimationTimer;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import seng201.team48.MainGameManager;
import seng201.team48.TowerManager;
import seng201.team48.models.Bowl;
import seng201.team48.models.Tower;
import seng201.team48.services.BowlService;

import java.util.Collections;
import java.util.List;

/**
 * MainScreenController contains the game functionality, timers, recipe book checking and movement and creating of
 * Bowl objects
 * @author Hannah Grace, Elise Newman
 */
public class MainScreenController {
    public Button backButton;
    public Button shopButton;
    public Label moneyValue;
    public Button imageOne;
    public Button imageTwo;
    public Button imageThree;
    public Button imageFour;
    public Button imageFive;
    public Label announcement;
    public Label bowlNumber;
    public Label roundNumber;
    public Bowl currentBowl;
    public Button recipeButton;
    public ProgressBar reload1;
    public ProgressBar reload2;
    public ProgressBar reload3;
    public ProgressBar reload4;
    public ProgressBar reload5;
    public Label ingredient1;
    public Label header;
    public String ingredient1Contents = "";
    TowerManager towerManager;
    MainGameManager mainGameManager;
    BowlService bowlService;
    public AnimationTimer timer;
    private boolean endTimer = false;
    private boolean pauseTimer = false;
    private List<Tower> playerTowers;
    public ImageView bowlImage;
    public ImageView crossOne;
    public ImageView crossTwo;
    public ImageView crossThree;
    public ImageView crossFour;
    public ImageView crossFive;


    /**Constructor method
     * creates the connection to mainGameManager
     * timer is responsible for reloadTimer and moving bowl visual
     * "/*" tags indicate sections of the mainScreen functionality for easy navigation
     * @param mainGameManager stores global variables and can navigate between screens
     */
    public MainScreenController(MainGameManager mainGameManager) {
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
        /* TIME */
        timer = new AnimationTimer() {
            private long bowlTime = 0;
            private long reloadTimer = 0;
            @Override
            public void handle(long now) {
                if(bowlTime == 0){
                    bowlTime = now;
                    reloadTimer = now;
                }
                if (endTimer){
                    this.stop();
                    fail();
                }
                if (pauseTimer){
                    this.stop();
                }
                if(now - reloadTimer >= 200_000_000L){
                    increaseProgressBars();
                    moveBowlVisual();
                    reloadTimer = now;
                }
            }

        };
        timer.start();
    }

    /** is called on startup and sets up variables based on current progress stored in mainGameManager and towerManager
     * sets tower graphics and enables images
     * sets progress bars state
     * creates or fetches current bowl, updates headers
     */
    /* ON STARTUP */
    @FXML
    public void initialize(){
        playerTowers = towerManager.getPlayerTowers();
        bowlService = mainGameManager.getBowlService();
        for (int i = 0; i < playerTowers.size(); i++) {
            String imagePath = "/images/"+playerTowers.get(i).getResourceType()+".png";
            Image imageI = new Image(getClass().getResourceAsStream(imagePath));
            ImageView viewI = new ImageView(imageI);
            viewI.setFitHeight(100);
            viewI.setPreserveRatio(true);
            switch (i) {
                case 0:
                    imageOne.setGraphic(viewI);
                    imageOne.setDisable(false);
                    reload1.setVisible(true);
                    if (playerTowers.get(i).isBroken()){ crossOne.setVisible(true);}
                    break;
                case 1:
                    imageTwo.setGraphic(viewI);
                    imageTwo.setDisable(false);
                    reload2.setVisible(true);
                    if (playerTowers.get(i).isBroken()){ crossTwo.setVisible(true);}
                    break;
                case 2:
                    imageThree.setGraphic(viewI);
                    imageThree.setDisable(false);
                    reload3.setVisible(true);
                    if (playerTowers.get(i).isBroken()){ crossThree.setVisible(true);}
                    break;
                case 3:
                    imageFour.setGraphic(viewI);
                    imageFour.setDisable(false);
                    reload4.setVisible(true);
                    if (playerTowers.get(i).isBroken()){ crossFour.setVisible(true);}
                    break;
                case 4:
                    imageFive.setGraphic(viewI);
                    imageFive.setDisable(false);
                    reload5.setVisible(true);
                    if (playerTowers.get(i).isBroken()){ crossFive.setVisible(true);}
                    break;
            }
        }

        String s = String.valueOf(mainGameManager.getTotalMoney());
        moneyValue.setText(s);
        bowlService.setNumBowlsSelected(mainGameManager.getNumSmall(), mainGameManager.getNumLarge());
        if (mainGameManager.getIsStartOfRound()){
            mainGameManager.setIsStartOfRound(false);
            currentBowl = bowlService.getNewBowl();
            mainGameManager.resetBowlLocation();
            mainGameManager.setBowlStepSize();

        } else{ //continue progress from before shop or recipe book
            currentBowl = mainGameManager.getCurrentBowl();
            bowlImage.setTranslateX(mainGameManager.getBowlLocation());
            for (int i = 0; i < playerTowers.size(); i++) {
                switch (i) {
                    case 0:
                        reload1.setProgress(mainGameManager.getReload1Temp());
                        break;
                    case 1:
                        reload2.setProgress(mainGameManager.getReload2Temp());
                        break;
                    case 2:
                        reload3.setProgress(mainGameManager.getReload3Temp());
                        break;
                    case 3:
                        reload4.setProgress(mainGameManager.getReload4Temp());
                        break;
                    case 4:
                        reload5.setProgress(mainGameManager.getReload5Temp());
                        break;
                    default:
                        System.out.println("error - cannot identify tower");
                        break;
                }
            }

            ingredient1Contents = mainGameManager.getIngredient1Contents();
            ingredient1.setText(ingredient1Contents);
        }
        updateBowlNumber();
        header.setText("In " + currentBowl.getSize() + " Bowl: ");
        String setCurrentRoundStats = mainGameManager.getCurrentRound() + " / " + mainGameManager.getNumRounds();
        roundNumber.setText(setCurrentRoundStats);
    }

    /** Increases the values for reloadTemp in mainGameManager per active tower
     * Sets the reload bar's progress in the fxml page
     */
    /* PROGRESS BARS */
    public void increaseProgressBars() {
        for (int i = 0; i < playerTowers.size(); i++) {
            double reloadSpeed = playerTowers.get(i).getReloadSpeed();
            double currentReload = 0;
            switch (i) {
                case 0:
                    currentReload = mainGameManager.getReload1Temp() + reloadSpeed;
                    mainGameManager.setReload1Temp(currentReload);
                    reload1.setProgress(currentReload);
                    break;
                case 1:
                    currentReload = mainGameManager.getReload2Temp() + reloadSpeed;
                    mainGameManager.setReload2Temp(currentReload);
                    reload2.setProgress(currentReload);
                    break;
                case 2:
                    currentReload = mainGameManager.getReload3Temp() + reloadSpeed;
                    mainGameManager.setReload3Temp(currentReload);
                    reload3.setProgress(currentReload);
                    break;
                case 3:
                    currentReload = mainGameManager.getReload4Temp() + reloadSpeed;
                    mainGameManager.setReload4Temp(currentReload);
                    reload4.setProgress(currentReload);
                    break;
                case 4:
                    currentReload = mainGameManager.getReload5Temp() + reloadSpeed;
                    mainGameManager.setReload5Temp(currentReload);
                    reload5.setProgress(currentReload);
                    break;
                default:
                    System.out.println("error - cannot identify tower");
            }
        }
    }
    /** The following methods control the tower buttons
     * Tower item can only be added to bowl is the reload bar is full (>= 1)
     * Progress bar value is reset in both fxml bar and in mainGameManager
     */
    /* ADD ITEMS TO BOWLS */
    @FXML
    public void onOneClicked(){
        if (mainGameManager.getReload1Temp() >= 1 && !playerTowers.get(0).isBroken()){
            reload1.setProgress(0);
            mainGameManager.setReload1Temp(0);
            addToBowl(playerTowers.get(0));}
    }
    @FXML
    private void onTwoClicked(){
        if (mainGameManager.getReload2Temp() >= 1 && !playerTowers.get(1).isBroken()){
            reload2.setProgress(0);
            mainGameManager.setReload2Temp(0);
            addToBowl(playerTowers.get(1));}
    }
    @FXML
    private void onThreeClicked(){
        if (mainGameManager.getReload3Temp() >= 1 && !playerTowers.get(2).isBroken()) {
            reload3.setProgress(0);
            mainGameManager.setReload3Temp(0);
            addToBowl(playerTowers.get(2));}
    }
    @FXML
    private void onFourClicked(){
        if (mainGameManager.getReload4Temp() >= 1 && !playerTowers.get(3).isBroken()){
            reload4.setProgress(0);
            mainGameManager.setReload4Temp(0);
            addToBowl(playerTowers.get(3));}
    }
    @FXML
    private void onFiveClicked(){
        if (mainGameManager.getReload5Temp() >= 1 && !playerTowers.get(4).isBroken()){
            reload5.setProgress(0);
            mainGameManager.setReload5Temp(0);
            addToBowl(playerTowers.get(4));}
    }

    /** Adds the item to the bowl then checks if currentBowl is full
     * if full, the items are passed to inRecipe to confirm validity
     * if valid, new bowl is generated
     * else bowl is reset and error is displayed
     * @param tower is the tower passing in its item to the currentBowl
     */
    private void addToBowl(Tower tower){
        currentBowl.addToBowl(tower);
        ingredient1Contents = ingredient1Contents + "- " + tower.getResourceType() + "\n";
        ingredient1.setText(ingredient1Contents);
        if(currentBowl.getFullBowl().equals(true)){
            String product = inRecipe();
            if(product != null){
                announcement.setText("You have made: " + product);
                if(bowlService.getBowlsUsed()){
                    endRound();
                } else {
                    currentBowl = bowlService.getNewBowl();
                    resetBowlVisual();
                    header.setText("In " + currentBowl.getSize() + " Bowl: ");
                    ingredient1Contents = "";
                    ingredient1.setText("");
                    updateBowlNumber();

                }
            } else {
                announcement.setText("Sorry, that can't be baked, try again");
                resetBowlContents();
                header.setText("In " + currentBowl.getSize() + " Bowl: ");
            }
        }
    }

    /** Checks string of tower contents against chosen recipe combinations
     * calls for method getString to turn currentBowl contents into quantity sting
     * @return String displaying food item created
     */
    /* RECIPE BOOK CONFIRMATION */
    private String inRecipe(){
        String all = getString();
        switch (all) {
            case "30000" -> {
                updateValue(20);
                return "Fried Eggs";
            }
            case "02100" -> {
                updateValue(20);
                return "Pasta Sauce";
            }
            case "21000" -> {
                updateValue(30);
                return "Scrambled Eggs";
            }
            case "10200" -> {
                updateValue(30);
                return "Pasta";
            }
            case "11100" -> {
                updateValue(50);
                return "Pancakes";
            }
            case "02010" -> {
                updateValue(70);
                return "Banana Smoothie";
            }
            case "12200" -> {
                updateValue(100);
                return "Pasta Carbonara";
            }
            case "11201" -> {
                updateValue(150);
                return "Cake";
            }
            case "11111" -> {
                updateValue(200);
                return "Banana Bread";
            }
        } return null;
    }

    /** Compares the list of possible towers against the ones in currentBowl
     * frequencies are concatenated into a single string as max frequency is 5
     * @return String of number of single digit occurrences of tower in currentBowl
     */
    private String getString(){
        List<Tower> allTowers = towerManager.getDefaultTowers();
        Tower egg = allTowers.get(1);
        Tower milk = allTowers.get(3);
        Tower flour = allTowers.get(0);
        Tower banana = allTowers.get(4);
        Tower sugar = allTowers.get(2);

        List<Tower> filled = currentBowl.getFilled();
        int num_egg = Collections.frequency(filled, egg);
        int num_milk = Collections.frequency(filled, milk);
        int num_flour = Collections.frequency(filled, flour);
        int num_banana = Collections.frequency(filled, banana);
        int num_sugar = Collections.frequency(filled, sugar);
        return "" + num_egg + num_milk + num_flour + num_banana + num_sugar;
    }


    /** Bowl step size is set by the gameDifficulty
     * Image is translated on step along the x-axis unless end is reached
     */
    /* CONTROL BOWL MOVEMENT */
    public void moveBowlVisual(){
        double x_value = mainGameManager.updateBowlStepSize();
        bowlImage.setTranslateX(x_value);
        if (x_value >= 720){
            endTimer = true;
            System.out.println(endTimer);
        }
    }

    /** Bowl has been used to create a recipe and returns to start of track
     * If bowl has large capacity (5), it is set visually larger
     */
    public void resetBowlVisual(){
        if (currentBowl.getSize() == "Large"){
            bowlImage.setFitHeight(120);
            bowlImage.setFitWidth(120);
            bowlImage.setTranslateY(-30);
        }
        mainGameManager.resetBowlLocation();
    }

    /** Bowl model has to be reset and all text or live variables also
     */
    /* RESET OR UPDATE VALUES */
    private void resetBowlContents(){
        currentBowl.setEmpty();
        ingredient1Contents = "";
        mainGameManager.setIngredient1Contents("");
        ingredient1.setText("");
    }

    /** when reset button is clicked, implement resetBowlContents method
     */

    /** Any time the bank balance increases - this method is called
     * sets the totalMoney in mainGameManager so is saved between rounds and onShopClicked
     * @param increase is an Integer value which we are adding to the bank balance saved
     */
    @FXML
    private void updateValue(int increase) {
        Integer newTotal = mainGameManager.getTotalMoney() + increase;
        mainGameManager.setTotalMoney(newTotal);
        String s = String.valueOf(newTotal);
        moneyValue.setText(s);
    }

    /** Updates the little "num bowl / all bowl" statistic in the top left corner
     */
    public void updateBowlNumber(){
        String setCurrentBowlStats = bowlService.getNumBowlsSent() + " / " + bowlService.getNumBowlsSelected();
        bowlNumber.setText(setCurrentBowlStats);
    }

    /** Player leaves to go to shop or recipe book
     * Some local variables need to be pushed into the mainGameManager, so they can be saved for when they return
     */
    public void saveValuesMidRound(){
        mainGameManager.setCurrentBowl(currentBowl);
        mainGameManager.setIngredient1Contents(ingredient1Contents);
        mainGameManager.setBowlService(bowlService);
        pauseTimer = true;
    }

    /** Player has either won the game or they have lost
     * Reset saved values, so they don't persist to later games
     */
    public void resetValuesEndGame(){
        mainGameManager.setIsStartOfRound(true);
        mainGameManager.resetBowlLocation();
        mainGameManager.setTotalMoney(0);
        pauseTimer = true;
    }

    /** Change the active screen and may pass in "MainScreen" as a parameter
     * The parameter sets the navigation back to this page
     */
    /* LEAVE MAIN GAME SCREEN */
    @FXML
    private void onShopClicked() {
        saveValuesMidRound();
        mainGameManager.launchShopScreen("MainScreen");
    }

    /** Change the active screen and may pass in "MainScreen" as a parameter
     * The parameter sets the navigation back to this page
     */
    @FXML
    private void onRecipeClicked(){
        saveValuesMidRound();
        mainGameManager.launchRecipeBook("MainScreen");
    }

    /** Change the active screen and may pass in "MainScreen" as a parameter
     * The parameter sets the navigation back to this page
     */
    @FXML
    private void onBackClicked() {
        resetValuesEndGame();
        mainGameManager.closeMainScreenHome();
    }

    /** The player has won the round
     * If they are on the last round, they have won the game
     */
    private void endRound() {

        if (mainGameManager.getCurrentRound().equals(mainGameManager.getNumRounds())) {
            resetValuesEndGame();
            mainGameManager.closeMainScreenConclusion();
        } else {
            pauseTimer = true;
            mainGameManager.updateRounds();
            mainGameManager.setIsStartOfRound(true);
            mainGameManager.resetBowlLocation();
            mainGameManager.closeMainScreenPreRound();
        }
    }

    /** The bowl has passed the threshold and they player lost
     * They go to the failing conclusion page
     */
    private void fail() {
        mainGameManager.setSuccess(Boolean.FALSE);
        resetValuesEndGame();
        mainGameManager.closeMainScreenConclusion();
    }

}
