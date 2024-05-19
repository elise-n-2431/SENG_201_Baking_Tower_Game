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

public class MainScreenController {
    public Button backButton;
    public Button shopButton;
    public Label moneyValue;
    public ImageView imageOne;
    public ImageView imageTwo;
    public ImageView imageThree;
    public ImageView imageFour;
    public ImageView imageFive;
    public Label announcement;
    public Button resetButton;
    public Bowl currentBowl = null;
    TowerManager towerManager;
    MainGameManager mainGameManager;
    BowlService bowlService = new BowlService();
    public AnimationTimer timer;

    public MainScreenController(MainGameManager mainGameManager) {
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();

        timer = new AnimationTimer() {
            private long bowlTime = 0;
            @Override
            public void handle(long now) {

                if (now - bowlTime >= 10_000_000_000L) {
                    this.stop();
                    fail();
                }
            }
            public void restartTimer(long thisTime){
                bowlTime = thisTime;
            }

        };
        timer.start();
    }
    public void resetBowlTimer() {
        //doesnt work :(
        timer.restartTimer(System.nanoTime());
    }
    @FXML
    private void updateValue(int increase) {
        mainGameManager.setMoneyPerRound(increase);
        String s = String.valueOf(increase);
        moneyValue.setText(s);
    }


    @FXML
    public void initialize(){
        List<Tower> playerTowers = towerManager.getPlayerTowers();
        String imagePath = "/images/"+playerTowers.get(0).getResourceType()+".png";
        Image imgOne = new Image(getClass().getResourceAsStream(imagePath));
        imageOne.setImage(imgOne);

        String imagePath2 = "/images/" +playerTowers.get(1).getResourceType()+ ".png";
        Image imgTwo = new Image(getClass().getResourceAsStream(imagePath2));
        imageTwo.setImage(imgTwo);

        String imagePath3 = "/images/"+playerTowers.get(2).getResourceType()+".png";
        Image imgThree = new Image(getClass().getResourceAsStream(imagePath3));
        imageThree.setImage(imgThree);
        /* prevents bank balance continuing to new rounds */
        if(mainGameManager.getCurrentRound()!=1){
            String s = String.valueOf(mainGameManager.getMoneyPerRound());
            moneyValue.setText(s);
        } else{
            mainGameManager.setMoneyPerRound(0);
        }
        Integer numSmall = mainGameManager.getNumSmall();
        System.out.println(numSmall);
        Integer numLarge = mainGameManager.getNumLarge();
        System.out.println(numLarge);
        if (numSmall != null){
            if (numLarge != null){
                bowlService.setNumBowlsSelected(numSmall, numLarge);
            }
        }
    }
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
        //should not restart from start of track
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
                currentBowl = bowlService.getNewBowl();
                //should put new bowl back to start of track
                resetBowlTimer();
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
