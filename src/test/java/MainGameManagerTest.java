import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team48.MainGameManager;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainGameManagerTest {
    MainGameManager testMainGameManager;

    @BeforeEach
    public void init() {
        // Mock the Consumers and the Runnable
        Consumer mockSetupScreenLauncher = mock(Consumer.class);
        Consumer mockPreroundScreenLauncher = mock(Consumer.class);
        Consumer mockMainScreenLauncher = mock(Consumer.class);
        Runnable mockClearScreen = mock(Runnable.class);
        Consumer mockShopScreenLauncher = mock(Consumer.class);
        Consumer mockConclusionLauncher = mock(Consumer.class);
        Consumer mockRecipeBookLauncher = mock(Consumer.class);

        // Pass in the mocks to create a test mainGameManager
        testMainGameManager = new MainGameManager(mockSetupScreenLauncher, mockPreroundScreenLauncher,
                mockMainScreenLauncher, mockClearScreen, mockShopScreenLauncher, mockConclusionLauncher,
                mockRecipeBookLauncher);

        // Test that everything's initialised properly
        assertEquals(mockSetupScreenLauncher, testMainGameManager.getSetupScreenLauncher());
        assertEquals(mockPreroundScreenLauncher, testMainGameManager.getPreroundScreenLauncher());
        assertEquals(mockMainScreenLauncher, testMainGameManager.getMainScreenLauncher());
        assertEquals(mockClearScreen, testMainGameManager.getClearScreen());
        assertEquals(mockShopScreenLauncher, testMainGameManager.getShopScreenLauncher());
        assertEquals(mockConclusionLauncher, testMainGameManager.getConclusionLauncher());
        assertEquals(mockRecipeBookLauncher, testMainGameManager.getRecipeBookLauncher());

        assertNotNull(testMainGameManager.getTowerManager());
        assertNotNull(testMainGameManager.getUpgradeManager());
    }

    @Test
    public void updateRounds() {
        Integer current = testMainGameManager.getCurrentRound();
        testMainGameManager.updateRounds();
        assertEquals(current + 1, testMainGameManager.getCurrentRound());

    }
}