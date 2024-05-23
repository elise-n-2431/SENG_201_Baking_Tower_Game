import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team48.MainGameManager;
import seng201.team48.gui.FXWrapper;
import seng201.team48.gui.SetupController;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainGameManagerTest {
    MainGameManager testMainGameManager;
    @BeforeEach
    public void init(){
        testMainGameManager = new MainGameManager(null, null, null, null, null, null, null);
    }
    @Test
    public void updateRounds() {
        Integer current = testMainGameManager.getCurrentRound();
        testMainGameManager.updateRounds();
        assertEquals(current + 1, testMainGameManager.getCurrentRound());

    }
    @Test
    public void updateBowlStepSize() {

    }
    @Test
    public void setTotalMoney() {

    }
    @Test
    public void setSuccess() {

    }
    @Test
    public void setRoundDifficulty() {

    }
    @Test
    public void setReload5Temp() {

    }
    @Test
    public void setReload4Temp() {

    }
    @Test
    public void setReload3Temp() {

    }
    @Test
    public void setReload2Temp() {

    }
    @Test
    public void setReload1Temp() {

    }
    @Test
    public void setNumRounds() {

    }
    @Test
    public void setName() {

    }
    @Test
    public void setIsStartOfRound() {

    }
    @Test
    public void setIngredient1Contents() {

    }
    @Test
    public void setGameDifficulty() {

    }
    @Test
    public void setCurrentRound() {

    }
    @Test
    public void setCurrentBowl() {

    }
    @Test
    public void setBowlStepSize() {

    }
    @Test
    public void setBowlService() {

    }
    @Test
    public void resetBowlLocation() {

    }
    @Test
    public void MainGameManager() {

    }
    @Test
    public void launchShopScreen() {

    }
    @Test
    public void launchSetupScreen() {

    }
    @Test
    public void launchRecipeBook() {

    }
    @Test
    public void launchPreroundScreen() {

    }
    @Test
    public void launchMainScreen() {

    }
    @Test
    public void launchConclusionScreen() {

    }
    @Test
    public void getTowerManager() {

    }
    @Test
    public void getTotalMoney() {

    }
    @Test
    public void getSuccess() {

    }
    @Test
    public void getRoundDifficulty() {

    }
    @Test
    public void getReload5Temp() {

    }
    @Test
    public void getReload4Temp() {

    }
    @Test
    public void getReload3Temp() {

    }
    @Test
    public void getReload2Temp() {

    }
    @Test
    public void getReload1Temp() {

    }
    @Test
    public void getNumSmall() {

    }
    @Test
    public void getNumRounds() {

    }
    @Test
    public void getNumLarge() {

    }
    @Test
    public void getName() {

    }
    @Test
    public void getIsStartOfRound() {

    }
    @Test
    public void getIngredient1Contents() {

    }
    @Test
    public void getGameDifficulty() {

    }
    @Test
    public void getCurrentRound() {

    }
    @Test
    public void getCurrentBowl() {

    }
    @Test
    public void getBowlService() {

    }{}
    @Test
    public void getBowlLocation() {

    }
    @Test
    public void deductTotalMoney() {

    }
    @Test
    public void closeShopScreen() {

    }
    @Test
    public void closeSetupScreen() {

    }
    @Test
    public void closeRecipeBook() {

    }
    @Test
    public void closePreroundStart() {

    }
    @Test
    public void closePreroundSetup() {

    }
    @Test
    public void closeMainScreenPreRound() {

    }
    @Test
    public void closeMainScreenHome() {

    }
    @Test
    public void closeMainScreenConclusion() {

    }
    @Test
    public void closeConclusionScreen() {

    }
}
