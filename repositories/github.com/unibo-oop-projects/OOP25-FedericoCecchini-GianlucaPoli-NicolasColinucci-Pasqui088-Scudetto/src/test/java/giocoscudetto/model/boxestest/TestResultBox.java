package giocoscudetto.model.boxestest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.impl.boxes.ResultBox;
import giocoscudetto.model.impl.match.ClubImpl;
import giocoscudetto.model.impl.match.MatchImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.boxes.ResultBox}.
 */
class TestResultBox {
    private final Club clubHome = new ClubImpl("home", 1);
    private final Club clubAway = new ClubImpl("away", 2);
    private Match match;
    private ResultBox resultBox;

    /**
     * Sets up the test environment before each test method is executed.
     */
    @BeforeEach
    void setUp() {
        match = new MatchImpl(clubHome, clubAway);
        resultBox = new ResultBox(5);
        if (!match.getCurrentPlayer().equals(clubHome)) {
            match.turn();
        }
    }

    /**
     * Tests that the event method of the ResultBox sets the game mode to "RESULT".
     */
    @Test
    void testEventSetsGameModeToResult() {
        assertNotEquals("RESULT", match.getGameMode());
        resultBox.event(match);
        assertEquals("RESULT", match.getGameMode());
    }

    /**
     * Tests that the position of the ResultBox is correctly set,
     *  to position given in the constructor.
     */
    @Test
    void testResultBoxPosition() {
        assertEquals(5, resultBox.getPosition());
    }

    /**
     * Tests that the name of the ResultBox is correctly set to "result box".
     */
    @Test
    void testResultBoxName() {
        assertEquals("result box", resultBox.getName());
    }

    /**
     * Tests that the image of the ResultBox is correctly set to "casella_3.png".
     */
    @Test
    void testResultBoxImage() {
        assertEquals("casella_3.png", resultBox.getImage());
    }

    /**
     * Tests that the event method of the ResultBox correctly updates the score.
     */
    @Test
    void testResultEvent() {
        final int dice1;
        final int dice2;

        resultBox.event(match);
        dice1 = match.diceEvent();
        dice2 = match.diceEvent();

        assertEquals(dice1, match.getScore().getHomeScore());
        assertEquals(dice2, match.getScore().getGuestScore());
    }
}
