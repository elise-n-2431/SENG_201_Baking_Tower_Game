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
            int increase = 0;

            @Override
            public void handle(long now) {
                // Check if 20 seconds have passed (20,000,000,000 nanoseconds)
                if (now - lastUpdate >= 2_000_000_000L) {
                    updateValue(increase);
                    increase += 100;
                    lastUpdate = now;
                    if (increase > 1000){
                        endGame();
                        increase = 0;
                    }
                }
            }
        };
        timer.start();
    }
    @FXML
    private void updateValue(int increase) {
        String s = String.valueOf(increase);
        moneyValue.setText(s);
        mainGameManager.setMoneyPerRound(s);
    }
    @FXML
    public void initialisation (Stage primaryStage){
        //for selected tower, change the image in the output
        //Can't test as not initialising correctly
        List<Tower> playerTowers = towerManager.getPlayerTowers();
        Image imgOne = new Image("Flour.png");
        imageOne.setImage(imgOne);
        Image imgTwo = new Image("src/main/resources/fxml/" + playerTowers.get(1) + ".png");
        imageTwo.setImage(imgTwo);
        Image imgThree = new Image("src/main/resources/fxml/" + playerTowers.get(2) + ".png");
        imageThree.setImage(imgThree);
        moneyValue.setText(mainGameManager.getMoneyPerRound());
    }
    @FXML
    private void onBackClicked () {
        mainGameManager.closeMainScreenHome();
    }
    @FXML
    private void onShopClicked () {
        mainGameManager.closeMainScreenShop();
    }
    private void endGame() {
        mainGameManager.closeMainScreenConclusion();
    }
}
