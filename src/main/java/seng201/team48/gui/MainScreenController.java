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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public Button resetButton;
    public Button resetBowl;
    public Bowl currentBowl = null;
    public Button recipeButton;
    public ProgressBar reloadOne;
    public ProgressBar reloadTwo;
    public ProgressBar reloadThree;
    public ProgressBar reloadFour;
    public ProgressBar reloadFive;
    TowerManager towerManager;
    MainGameManager mainGameManager;
    BowlService bowlService = new BowlService();
    public AnimationTimer timer;
    private boolean resetTimer = false;
    private double reloadOneTemp = 0;
    private double reloadTwoTemp = 0;
    private double reloadThreeTemp = 0;
    private double reloadFourTemp = 0;
    private double reloadFiveTemp = 0;



    public MainScreenController(MainGameManager mainGameManager) {
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();

        timer = new AnimationTimer() {
            private long bowlTime = 0;
            private long reloadTimer = 0;
            @Override
            public void handle(long now) {
                if(bowlTime == 0){
                    bowlTime = now;
                    reloadTimer = now;
                }
                if (resetTimer){
                    bowlTime = now;
                    resetTimer = false;
                }
                if(now - reloadTimer >= 1_000_000_000L){
                    increaseProgressBars();
                    reloadTimer = now;
                }
                if (now - bowlTime >= 30_000_000_000L) {
                    this.stop();
                    fail();
                }
            }

        };
        timer.start();
    }

    @FXML
    public void initialize(){
        List<Tower> playerTowers = towerManager.getPlayerTowers();
        String imagePath = "/images/"+playerTowers.get(0).getResourceType()+".png";
        Image imgOne = new Image(getClass().getResourceAsStream(imagePath));
        ImageView view = new ImageView(imgOne);
        view.setFitHeight(100);
        view.setPreserveRatio(true);
        imageOne.setGraphic(view);
        imageOne.setDisable(false); //disabled if not in use
        reloadOne.setVisible(true);

        String imagePath2 = "/images/" +playerTowers.get(1).getResourceType()+ ".png";
        Image imgTwo = new Image(getClass().getResourceAsStream(imagePath2));
        ImageView view2 = new ImageView(imgTwo);
        view2.setFitHeight(100);
        view2.setPreserveRatio(true);
        imageTwo.setGraphic(view2);
        imageTwo.setDisable(false);
        reloadTwo.setVisible(true);

        String imagePath3 = "/images/"+playerTowers.get(2).getResourceType()+".png";
        Image imgThree = new Image(getClass().getResourceAsStream(imagePath3));
        ImageView view3 = new ImageView(imgThree);
        view3.setFitHeight(100);
        view3.setPreserveRatio(true);
        imageThree.setGraphic(view3);
        imageThree.setDisable(false);
        reloadThree.setVisible(true);

        /* prevents bank balance continuing to new rounds */
        if(mainGameManager.getCurrentRound()!=1){
            String s = String.valueOf(mainGameManager.getMoneyPerRound());
            moneyValue.setText(s);
        } else{
            mainGameManager.setMoneyPerRound(0);
        }
        Integer numSmall = mainGameManager.getNumSmall();
        Integer numLarge = mainGameManager.getNumLarge();
        if (numSmall != null){
            if (numLarge != null){
                bowlService.setNumBowlsSelected(numSmall, numLarge);
            }
        }

        /* testing for recipe book
        List<Tower> allTowers = towerManager.getDefaultTowers();
        List<Tower> towers = new ArrayList<Tower>(3);
        towers.add(allTowers.get(0));
        towers.add(allTowers.get(0));
        towers.add(allTowers.get(1));
        String product = inRecipe(towers);
        if(product != null){
            System.out.println(product);
        } */

    }
    public void increaseProgressBars() {
        //get reload speed from somewhere - temp is same
        reloadOne.setProgress(reloadOneTemp + 0.1);
        reloadOneTemp += 0.1;
        reloadTwo.setProgress(reloadTwoTemp + 0.1);
        reloadTwoTemp += 0.1;
        reloadThree.setProgress(reloadThreeTemp + 0.1);
        reloadThreeTemp += 0.1;
        reloadFour.setProgress(reloadFourTemp + 0.1);
        reloadFourTemp += 0.1;
        reloadFive.setProgress(reloadFiveTemp + 0.1);
        reloadFiveTemp += 0.1;
    }

    public void resetBowlTimer() {
        resetTimer = true;
    }
    @FXML
    private void resetBowlClicked(){
        //temporary way to test bowl timer
        resetBowlTimer();
    }
    @FXML
    private void updateValue(int increase) {
        mainGameManager.setMoneyPerRound(increase);
        String s = String.valueOf(increase);
        moneyValue.setText(s);
    }

    @FXML
    private void onOneClicked(){
        reloadOne.setProgress(0);
        reloadOneTemp = 0;
    }
    @FXML
    private void onTwoClicked(){
        reloadTwo.setProgress(0);
        reloadTwoTemp = 0;
    }
    @FXML
    private void onThreeClicked(){
        reloadThree.setProgress(0);
        reloadThreeTemp = 0;
    }
    @FXML
    private void onFourClicked(){
        reloadFour.setProgress(0);
        reloadFourTemp = 0;
    }
    @FXML
    private void onFiveClicked(){
        reloadFive.setProgress(0);
        reloadFiveTemp = 0;
    }
    //TESTING ENDS

    @FXML
    private void onBackClicked() {
        mainGameManager.closeMainScreenHome();
    }
    @FXML
    private void onShopClicked() {
        mainGameManager.closeMainScreenShop();
    }
    private void endRound() {
        if (mainGameManager.getCurrentRound().equals(mainGameManager.getNumRounds())){
            mainGameManager.closeMainScreenConclusion();
        } else {
            mainGameManager.updateRounds();
            mainGameManager.closeMainScreenPreRound();
        }
    }
    @FXML
    private void onResetClicked(){
        //resets the bowl contents by creating a new bowl at same location
        currentBowl = bowlService.getNewBowl();
    }
    @FXML
    private void onRecipeClicked(){
        mainGameManager.launchRecipeBook("MainScreen");
    }
    private void fail() {
        mainGameManager.setSuccess(Boolean.FALSE);
        mainGameManager.closeMainScreenConclusion();
    }
    private void addToBowl(Tower tower){
        // call for this method when the user clicks a tower
        currentBowl.addToBowl(tower);
        if(currentBowl.getFullBowl().equals(true)){
            String product = inRecipe();
            if(product != null){
                announcement.setText("You have made: " + product);
                resetBowlTimer();
                if(bowlService.getBowlsUsed()){
                    //no more bowls
                    endRound();
                } else {
                    currentBowl = bowlService.getNewBowl();
                }
            } else {
                announcement.setText("Sorry, that can't be baked, try again");
            }
        }
    }
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
    private String getString(){
        List<Tower> allTowers = towerManager.getDefaultTowers();
        Tower egg = allTowers.get(0);
        Tower milk = allTowers.get(1);
        Tower flour = allTowers.get(2);
        Tower banana = allTowers.get(3);
        Tower sugar = allTowers.get(4);

        List<Tower> filled = currentBowl.getFilled();
        int num_egg = Collections.frequency(filled, egg);
        int num_milk = Collections.frequency(filled, milk);
        int num_flour = Collections.frequency(filled, flour);
        int num_banana = Collections.frequency(filled, banana);
        int num_sugar = Collections.frequency(filled, sugar);
        return "" + num_egg + num_milk + num_flour + num_banana + num_sugar;
    }
}
