package seng201.team48.gui;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.shape.Line;
import javafx.util.Duration;
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
    public Label bowlNumber;
    public Label roundNumber;
    public Button resetBowl;
    public Bowl currentBowl = null;
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
    BowlService bowlService = new BowlService();
    public AnimationTimer timer;
    private boolean resetTimer = false;
    private boolean endTimer = false;
    private List<Tower> playerTowers;
    public ImageView bowlImage;



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
                if (endTimer){
                    this.stop();
                    fail();
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

    @FXML
    public void initialize(){
        playerTowers = towerManager.getPlayerTowers();
        String imagePath = "/images/"+playerTowers.get(0).getResourceType()+".png";
        Image imgOne = new Image(getClass().getResourceAsStream(imagePath));
        ImageView view = new ImageView(imgOne);
        view.setFitHeight(100);
        view.setPreserveRatio(true);
        imageOne.setGraphic(view);
        imageOne.setDisable(false); //disabled if not in use
        reload1.setVisible(true);

        String imagePath2 = "/images/" +playerTowers.get(1).getResourceType()+ ".png";
        Image imgTwo = new Image(getClass().getResourceAsStream(imagePath2));
        ImageView view2 = new ImageView(imgTwo);
        view2.setFitHeight(100);
        view2.setPreserveRatio(true);
        imageTwo.setGraphic(view2);
        imageTwo.setDisable(false);
        reload2.setVisible(true);

        String imagePath3 = "/images/"+playerTowers.get(2).getResourceType()+".png";
        Image imgThree = new Image(getClass().getResourceAsStream(imagePath3));
        ImageView view3 = new ImageView(imgThree);
        view3.setFitHeight(100);
        view3.setPreserveRatio(true);
        imageThree.setGraphic(view3);
        imageThree.setDisable(false);
        reload3.setVisible(true);

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
        if (mainGameManager.getIsStartOfRound()){
            mainGameManager.setIsStartOfRound(false);
            currentBowl = bowlService.getNewBowl();
            updateBowlNumber();
            mainGameManager.setBowlStepSize();
        } else{ //continue progress from before shop or recipe book
            currentBowl = mainGameManager.getCurrentBowl();
            bowlImage.setTranslateX(mainGameManager.getBowlLocation());
            mainGameManager.setMoneyPerRound(0);
            for (int i = 0; i < playerTowers.size(); i++) {
                switch (i) {
                    case 0:
                        reload1.setProgress(mainGameManager.getReload1Temp());

                    case 1:
                        reload2.setProgress(mainGameManager.getReload2Temp());
                    case 2:
                        reload3.setProgress(mainGameManager.getReload3Temp());
                    case 3:
                        reload4.setProgress(mainGameManager.getReload4Temp());
                    case 4:
                        reload5.setProgress(mainGameManager.getReload5Temp());
                    default:
                        System.out.println("error - cannot identify tower");
                }
            }
            ingredient1Contents = mainGameManager.getIngredient1Contents();
            ingredient1.setText(ingredient1Contents);
        }
        header.setText("In " + currentBowl.getSize() + " Bowl: ");
        String setCurrentRoundStats = mainGameManager.getCurrentRound() + " / " + mainGameManager.getNumRounds();
        roundNumber.setText(setCurrentRoundStats);
    }
    public void updateBowlNumber(){
        String setCurrentBowlStats = bowlService.getNumBowlsSent() + " / " + bowlService.getNumBowlsSelected();
        bowlNumber.setText(setCurrentBowlStats);
    }

    public void increaseProgressBars() {
        //updates progress bars for each active tower in towers
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



    public void resetBowlTimer() {
        resetTimer = true;
    }
    @FXML
    private void resetBowlClicked(){
        currentBowl.setEmpty();
        ingredient1Contents = "";
        ingredient1.setText("");
    }
    @FXML
    private void updateValue(int increase) {
        Integer newTotal = mainGameManager.getMoneyPerRound() + increase;
        mainGameManager.setMoneyPerRound(newTotal);
        String s = String.valueOf(newTotal);
        moneyValue.setText(s);
    }

    @FXML
    private void onOneClicked(){
        if (mainGameManager.getReload1Temp() >= 1){
            reload1.setProgress(0);
            mainGameManager.setReload1Temp(0);
            addToBowl(playerTowers.get(0));}
    }
    @FXML
    private void onTwoClicked(){
        if (mainGameManager.getReload2Temp() >= 1){
            reload2.setProgress(0);
            mainGameManager.setReload2Temp(0);
            addToBowl(playerTowers.get(1));}
    }
    @FXML
    private void onThreeClicked(){
        if (mainGameManager.getReload3Temp() >= 1) {
            reload3.setProgress(0);
            mainGameManager.setReload3Temp(0);
            addToBowl(playerTowers.get(2));}
    }
    @FXML
    private void onFourClicked(){
        if (mainGameManager.getReload4Temp() >= 1){
            reload4.setProgress(0);
            mainGameManager.setReload4Temp(0);
            addToBowl(playerTowers.get(3));}
    }
    @FXML
    private void onFiveClicked(){
        if (mainGameManager.getReload5Temp() >= 1){
            reload5.setProgress(0);
            mainGameManager.setReload5Temp(0);
            addToBowl(playerTowers.get(4));}
    }


    @FXML
    private void onBackClicked() {
        mainGameManager.closeMainScreenHome();
    }
    @FXML
    private void onShopClicked() {
        mainGameManager.setCurrentBowl(currentBowl);
        mainGameManager.setIngredient1Contents(ingredient1Contents);
        mainGameManager.closeMainScreenShop();
    }
    private void endRound() {
        if (mainGameManager.getCurrentRound().equals(mainGameManager.getNumRounds())) {
            mainGameManager.closeMainScreenConclusion();
        } else {
            mainGameManager.updateRounds();
            mainGameManager.setIsStartOfRound(true);
            mainGameManager.closeMainScreenPreRound();
        }
    }
    @FXML
    private void onRecipeClicked(){
        mainGameManager.setCurrentBowl(currentBowl);
        mainGameManager.setIngredient1Contents(ingredient1Contents);
        mainGameManager.launchRecipeBook("MainScreen");
    }
    private void fail() {
        mainGameManager.setSuccess(Boolean.FALSE);
        mainGameManager.closeMainScreenConclusion();
    }
    private void addToBowl(Tower tower){
        currentBowl.addToBowl(tower);
        ingredient1Contents = ingredient1Contents + "- " + tower.getResourceType() + "\n";
        ingredient1.setText(ingredient1Contents);
        if(currentBowl.getFullBowl().equals(true)){
            String product = inRecipe();
            System.out.println(product);
            if(product != null){
                announcement.setText("You have made: " + product);
                resetBowlTimer();
                if(bowlService.getBowlsUsed()){
                    //no more bowls
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
                resetBowlClicked();
                header.setText("In " + currentBowl.getSize() + " Bowl: ");

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
        System.out.println(allTowers);
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

    /* CONTROL BOWL MOVEMENT BELOW */
    public void moveBowlVisual(){
        double x_value = mainGameManager.updateBowlStepSize();
        bowlImage.setTranslateX(x_value);
        if (x_value >= 720){
            endTimer = true;
        }
    }
    public void resetBowlVisual(){
        if (currentBowl.getSize() == "Large"){
            bowlImage.setFitHeight(120);
            bowlImage.setFitWidth(120);
            bowlImage.setTranslateY(-40);
        }
        mainGameManager.resetBowlLocation();
    }

}
