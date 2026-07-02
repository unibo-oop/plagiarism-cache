package test.minigames.oneway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import model.DifficultyLevel;
import model.minigames.oneway.OneWayModel;
import model.minigames.oneway.OneWayModelImpl;

/**
 * Class to test if {@link OneWayModel} works correctly.
 *
 */
public class OneWayTest {

    /**
     * Costants for the specific settings to test.
     * 
     */
    private static final int EASY_LEVEL_ARROW = 3;
    private static final int NORMAL_LEVEL_ARROW = 4;
    private static final int HARD_LEVEL_ARROW = 5;

    private static final int EASY_LEVEL_SIZE = 6;
    private static final int NORMAL_LEVEL_SIZE = 7;
    private static final int HARD_LEVEL_SIZE = 8;

    private static final int EASY_LEVEL_SEC = 40;
    private static final int NORMAL_LEVEL_SEC = 35;
    private static final int HARD_LEVEL_SEC = 30;

    private OneWayModel model;

    /**
     * Test for easy level settings.
     */
    @Test
    public void easyLevelTest() {
        this.model = new OneWayModelImpl(DifficultyLevel.EASY);
        this.model.oneWayInit();
        assertEquals(EASY_LEVEL_ARROW, this.model.getArrowsCount());
        assertEquals(EASY_LEVEL_SIZE, this.model.getGridSize());
        assertEquals(EASY_LEVEL_SEC, this.model.getSeconds());
    }

    /**
     * Test for normal level settings.
     */
    @Test
    public void normalLevelyTest() {
        this.model = new OneWayModelImpl(DifficultyLevel.NORMAL);
        this.model.oneWayInit();
        assertEquals(NORMAL_LEVEL_ARROW, this.model.getArrowsCount());
        assertEquals(NORMAL_LEVEL_SIZE, this.model.getGridSize());
        assertEquals(NORMAL_LEVEL_SEC, this.model.getSeconds());
    }

    /**
     * Test for hard level settings.
     */
    @Test
    public void hardLevelTest() {
        this.model = new OneWayModelImpl(DifficultyLevel.HARD);
        this.model.oneWayInit();
        assertEquals(HARD_LEVEL_ARROW, this.model.getArrowsCount());
        assertEquals(HARD_LEVEL_SIZE, this.model.getGridSize());
        assertEquals(HARD_LEVEL_SEC, this.model.getSeconds());
    }

    /**
     * Test index bounds on initial and final positions.
     * 
     */
    @Test
    public void testInitialAndFinalPosValidity() {
        final OneWayModel model = new OneWayModelImpl(DifficultyLevel.EASY);
        model.oneWayInit();

        assertTrue(model.getInitialPosition().getX() < model.getGridSize());
        assertTrue(model.getInitialPosition().getY() < model.getGridSize());
        assertTrue(model.getInitialPosition().getX() >= 0);
        assertTrue(model.getInitialPosition().getY() >= 0);

        assertTrue(model.getFinalPosition().getX() < model.getGridSize());
        assertTrue(model.getFinalPosition().getY() < model.getGridSize());
        assertTrue(model.getFinalPosition().getX() >= 0);
        assertTrue(model.getFinalPosition().getY() >= 0);
    }

    /**
     * Test inequalty between initial and final position.
     */
    @Test
    public void testPositions() {
        final OneWayModel model = new OneWayModelImpl(DifficultyLevel.EASY);
        model.oneWayInit();
        assertFalse(model.getInitialPosition().getX() == model.getFinalPosition().getX()
                && model.getInitialPosition().getY() == model.getFinalPosition().getY());
    }

}
