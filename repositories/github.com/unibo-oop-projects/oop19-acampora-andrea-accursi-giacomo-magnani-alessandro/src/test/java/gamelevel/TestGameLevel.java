package gamelevel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import bubbleshooter.model.game.GameStatus;
import bubbleshooter.model.game.level.BasicLevel;
import bubbleshooter.model.game.level.Level;
import bubbleshooter.model.game.level.SurvivalLevel;

/**
 * JUnit Test class to test the {@link Level} of the Game.
 */
public class TestGameLevel {

    private static final double ELAPSED = 1;
    private static final double LONG_ELAPSED = 20_000;

    /**
     * Tests the status of the game before and after start the {@link BasicLevel}.
     */
    @Test
    public final void testStartBasicLevel() {
        final Level level = new BasicLevel();
        assertEquals(level.getGameStatus(), GameStatus.PAUSE);
        level.start();
        level.update(ELAPSED);
        assertEquals(level.getGameStatus(), GameStatus.RUNNING);
    }

    /**
     * Tests the status of the game before and after start the
     * {@link SurvivalLevel}.
     */
    @Test
    public final void testStartSurvivalLevel() {
        final Level level = new SurvivalLevel();
        assertEquals(level.getGameStatus(), GameStatus.PAUSE);
        level.start();
        level.update(ELAPSED);
        assertEquals(level.getGameStatus(), GameStatus.RUNNING);
    }

    /**
     * Tests the correct loading of {@link ShootingBubble}.
     */
    @Test
    public final void testLoadShootingBubble() {
        final Level level = new BasicLevel();
        assertEquals(level.getBubblesManager().getShootingBubble(), Optional.empty());
        level.start();
        level.update(ELAPSED);
        assertNotEquals(level.getBubblesManager().getShootingBubble(), Optional.empty());
    }

    /**
     * Tests the creation of new row in {@link SurvivalLevel}.
     */
    @Test
    public final void testNewRowSurvivalLevel() {
        final SurvivalLevel level = new SurvivalLevel();
        level.start();
        level.update(ELAPSED);
        assertEquals(level.getGridManager().getCreatedRows(), Level.NUM_ROWS);
        level.update(LONG_ELAPSED);
        assertNotEquals(level.getGridManager().getCreatedRows(), Level.NUM_ROWS);
    }

    /**
     * Tests the creation of new row in {@link BasicLevel}.
     */
    @Test
    public final void testNewRowBasicLevel() {
        final BasicLevel level = new BasicLevel();
        level.start();
        level.update(ELAPSED);
        assertEquals(level.getGridManager().getCreatedRows(), Level.NUM_ROWS);
        level.getGameData().addWrongShoot();
        level.getGameData().addWrongShoot();
        level.getGameData().addWrongShoot();
        level.getGameData().addWrongShoot();
        level.getGameData().addWrongShoot();
        level.update(ELAPSED);
        assertNotEquals(level.getGridManager().getCreatedRows(), Level.NUM_ROWS);    }
}
