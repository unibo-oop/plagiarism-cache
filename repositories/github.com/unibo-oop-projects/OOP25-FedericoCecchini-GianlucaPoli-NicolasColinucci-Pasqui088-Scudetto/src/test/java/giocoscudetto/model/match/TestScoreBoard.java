package giocoscudetto.model.match;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.impl.match.ScoreboardImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.match.ScoreboardImpl}.
 */
class TestScoreBoard {

    private ScoreboardImpl scoreboard;

    /**
     * Sets up the test environment before each test method is executed.
     */
    @BeforeEach
    void setUp() {
        scoreboard = new ScoreboardImpl();
    }

    /**
     * Tests that the getHomeScore method correctly returns the home score.
     */
    @Test
    void testGetHomeScore() {
        scoreboard.setHomeScore(3);
        assertEquals(3, scoreboard.getHomeScore());
    }

    /**
     * Tests that the getGuestScore method correctly returns the guest score.
     */
    @Test
    void testGetGuestScore() {
        scoreboard.setGuestScore(2);
        assertEquals(2, scoreboard.getGuestScore());
    }

    /**
     * Tests that the setHomeScore method correctly sets the home score.
     */
    @Test
    void testSetHomeScore() {
        scoreboard.setHomeScore(5);
        assertEquals(5, scoreboard.getHomeScore());
    }

    /**
     * Tests that the setGuestScore method correctly sets the guest score.
     */
    @Test
    void testSetGuestScore() {
        scoreboard.setGuestScore(4);
        assertEquals(4, scoreboard.getGuestScore());
    }

    /**
     * Tests that the increaseHomeScore method correctly increases the home score by 1.
     */
    @Test
    void testIncreaseHomeScore() {
        scoreboard.increaseHomeScore();
        assertEquals(1, scoreboard.getHomeScore());
        scoreboard.increaseHomeScore();
        assertEquals(2, scoreboard.getHomeScore());
    }

    /**
     * Tests that the increaseGuestScore method correctly increases the guest score by 1.
     */
    @Test
    void testIncreaseGuestScore() {
        scoreboard.increaseGuestScore();
        assertEquals(1, scoreboard.getGuestScore());
        scoreboard.increaseGuestScore();
        assertEquals(2, scoreboard.getGuestScore());
    }

    /**
     * Tests that the decreaseHomeScore method correctly decreases the home score by 1.
     */
    @Test
    void testDecreaseHomeScore() {
        scoreboard.setHomeScore(3);
        scoreboard.decreaseHomeScore();
        assertEquals(2, scoreboard.getHomeScore());
        scoreboard.setHomeScore(1);
        scoreboard.decreaseHomeScore();
        assertEquals(0, scoreboard.getHomeScore());
        scoreboard.decreaseHomeScore();
        assertEquals(0, scoreboard.getHomeScore(), "Home score should not go below 0");
    }

    /**
     * Tests that the decreaseGuestScore method correctly decreases the guest score by 1.
     */
    @Test
    void testDecreaseGuestScore() {
        scoreboard.setGuestScore(3);
        scoreboard.decreaseGuestScore();
        assertEquals(2, scoreboard.getGuestScore());
        scoreboard.setGuestScore(1);
        scoreboard.decreaseGuestScore();
        assertEquals(0, scoreboard.getGuestScore());
        scoreboard.decreaseGuestScore();
        assertEquals(0, scoreboard.getGuestScore(), "Guest score should not go below 0");
    }

    /**
     * Tests that the toString method correctly formats the score.
     */
    @Test
    void testToString() {
        scoreboard.setHomeScore(2);
        scoreboard.setGuestScore(1);
        assertEquals("2 - 1", scoreboard.toString());
    }

}
