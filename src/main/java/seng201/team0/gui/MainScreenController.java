package seng201.team0.gui;
import javafx.animation.AnimationTimer;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import seng201.team0.MainGameManager;
import seng201.team0.TowerManager;
import seng201.team0.models.Tower;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    TowerManager towerManager;
    MainGameManager mainGameManager;

    public MainScreenController(MainGameManager mainGameManager) {
        this.mainGameManager = mainGameManager;
        this.towerManager = mainGameManager.getTowerManager();
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;
            int increase = mainGameManager.getMoneyPerRound();

            @Override
            public void handle(long now) {
                // Check if 20 seconds have passed (20,000,000,000 nanoseconds)
                if (now - lastUpdate >= 2_000_000_000L) {
                    updateValue(increase);
                    increase += 100;
                    lastUpdate = now;
                    if (increase > 1000){
                        endRound();
                        mainGameManager.setMoneyPerRound(0);
                        increase = 0;
                        this.stop();
                    }
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
        String s = String.valueOf(mainGameManager.getMoneyPerRound());
        moneyValue.setText(s);
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
    private void fail() {
        mainGameManager.setSuccess(Boolean.FALSE);
        mainGameManager.closeMainScreenConclusion();
    }
}
