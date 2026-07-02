package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// CHECKSTYLE: OFF
//The annotation is needed to avoid false positives
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
// CHECKSTYLE: ON
import javawulf.model.player.Player;
import javawulf.model.player.PlayerImpl;
import javawulf.model.player.Score;
import javawulf.model.player.Score.Multiplier;

/**
 * ScoreTest checks whether the implementation of Score increases
 * and decreases the point total as it should, considering the
 * current mulitplier.
 */
final class ScoreTest {

    private static final int HEALTH = 3;
    private static final int STARTING_X = 12;
    private static final int STARTING_Y = 12;
    private static final int STARTING_POINTS = 0;
    private Score score;
    private static final int INCREASE = 100;

    @BeforeEach
    void createScore() {
        final Player player = new PlayerImpl(STARTING_X, STARTING_Y, HEALTH, STARTING_POINTS);
        this.score = player.getScore();
    }

    @Test
    @SuppressFBWarnings(
        value = {
            "L", "D", "UwF"
        },
        justification = "Score is initialized in the @BeforeEach"
    )
    void testStartingScore() {
        assertEquals(STARTING_POINTS, this.score.getPoints());
        assertEquals(Multiplier.DEFAULT.getValue(), this.score.getMultiplier());
    }

    @Test
    @SuppressFBWarnings(
        value = {
            "L", "D", "UwF"
        },
        justification = "Score is initialized in the @BeforeEach"
    )
    void testIncrease() {
        this.score.addPoints(INCREASE);
        assertNotEquals(STARTING_POINTS, this.score.getPoints());
        assertEquals(INCREASE, this.score.getPoints());
    }

    @Test
    @SuppressFBWarnings(
        value = {
            "L", "D", "UwF"
        },
        justification = "Score is initialized in the @BeforeEach"
    )
    void testIncreaseWithMultiplier() {
        this.score.setMultiplier(Multiplier.DOUBLE);
        this.score.addPoints(INCREASE);
        assertNotEquals(Multiplier.DEFAULT.getValue(), this.score.getMultiplier());
        assertNotEquals(INCREASE, this.score.getPoints());
        assertEquals(INCREASE * 2, this.score.getPoints());

        this.score.setMultiplier(Multiplier.DEFAULT);
        this.score.addPoints(INCREASE);
        assertEquals(Multiplier.DEFAULT.getValue(), this.score.getMultiplier());
        assertNotEquals(INCREASE * 2, this.score.getPoints());
        assertEquals(INCREASE * 3, this.score.getPoints());
    }
}
