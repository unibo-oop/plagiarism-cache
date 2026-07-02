package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.impl.boxes.CornerBox;
import giocoscudetto.model.impl.match.ClubImpl;
import giocoscudetto.model.impl.match.MatchImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.boxes.CornerBox}.
 */
class TestCornerBox {
    private final Club clubHome = new ClubImpl("home", 1);
    private final Club clubAway = new ClubImpl("away", 2);
    private Match match;
    private Box cornerBox;

    /**
     * Sets up the test environment before each test method is executed.
     */
    @BeforeEach
    void setUp() {
        match = new MatchImpl(clubHome, clubAway);
        cornerBox = new CornerBox(5);
        if (!match.getCurrentPlayer().equals(clubHome)) {
            match.turn();
        }
    }

    /**
     * Tests that the event method of the CornerBox sets the game mode to "CORNER".
     */
    @Test
    void testEventSetsGameModeToCorner() {
        assertNotEquals("CORNER", match.getGameMode());
        cornerBox.event(match);
        assertEquals("CORNER", match.getGameMode());
    }

    /**
     * Tests that the position of the CornerBox is correctly set,
     *  to position given in the constructor.
     */
    @Test
    void testCornerBoxPosition() {
        assertEquals(5, cornerBox.getPosition());
    }

    /**
     * Tests that the name of the CornerBox is correctly set to "Corner Box".
     */
    @Test
    void testCornerBoxName() {
        assertEquals("Corner Box", cornerBox.getName());
    }

    /**
     * Tests that the image of the CornerBox is correctly set to "casella_19.png".
     */
    @Test
    void testCornerBoxImage() {
        assertEquals("casella_19.png", cornerBox.getImage());
    }

    /**
     * Tests that the event method of the CornerBox is correct.
     */
    @Test
    void testCornerEvent() {
        final Integer dice1;
        final Integer dice2;
        final int oldHomeScore = this.match.getScore().getHomeScore();
        final int oldGuestScore = this.match.getScore().getGuestScore();

        cornerBox.event(match);
        dice1 = match.diceEvent();
        dice2 = match.diceEvent();

        if (dice1.equals(1) || dice2.equals(1)) {
            assertEquals(oldHomeScore + 1, this.match.getScore().getHomeScore());
            assertEquals(oldGuestScore, this.match.getScore().getGuestScore());
        } else {
            assertEquals(oldHomeScore, this.match.getScore().getHomeScore());
            assertEquals(oldGuestScore, this.match.getScore().getGuestScore());
        }
    }
}
