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
    public Bowl currentBowl = null;
    TowerManager towerManager;
    MainGameManager mainGameManager;
    BowlService bowlService = new BowlService();

    public MainScreenController(MainGameManager mainGameManager) {
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;
            int increase = mainGameManager.getMoneyPerRound();
            int activeTime = 0;
            @Override
            public void handle(long now) {
                // Check if 20 seconds have passed (20,000,000,000 nanoseconds)
                if (now - lastUpdate >= 2_000_000_000L) { //time should change based on difficulty
                    updateValue(increase);
                    increase += 100;
                    lastUpdate = now;
                    if (increase > 1000){ //should end round when carts have all gone through
                        endRound();
                        mainGameManager.setMoneyPerRound(0);
                        increase = 0;
                        this.stop();
                    }
                }
                if (now - activeTime >= 10_000_000_000L) {
                    //loses game

                }
            }
        };
        timer.start();
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
    private void onRecipeClicked(){
        mainGameManager.launchRecipeBook("MainScreen");
    }
    private void fail() {
        mainGameManager.setSuccess(Boolean.FALSE);
        mainGameManager.closeMainScreenConclusion();
    }
    private void addToBowl(Tower tower){
        // call for this method when the user clicks a tower
        //currentBowl.addToBowl( "Tower goes here" );
        if(currentBowl.getFullBowl().equals(true)){
            if(inRecipe()){
                //destroy currentBowl
                currentBowl = bowlService.getNewBowl();
            }
        }
    }
    private Boolean inRecipe(){
        // check the items in bowl against recipes
    }
}
