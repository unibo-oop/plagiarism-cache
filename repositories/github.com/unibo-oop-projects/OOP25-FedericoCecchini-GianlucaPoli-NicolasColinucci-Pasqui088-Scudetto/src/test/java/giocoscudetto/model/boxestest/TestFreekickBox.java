package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.impl.boxes.FreeKickBox;
import giocoscudetto.model.impl.match.ClubImpl;
import giocoscudetto.model.impl.match.MatchImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.boxes.FreeKickBox}.
 */
class TestFreekickBox {
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
        cornerBox = new FreeKickBox(5);
        if (!match.getCurrentPlayer().equals(clubHome)) {
            match.turn();
        }
    }

    /**
     * Tests that the event method of the FreeKickBox sets the game mode to "FREE_KICK".
     */
    @Test
    void testEventSetsGameModeToFreekick() {
        assertNotEquals("FREE_KICK", match.getGameMode());
        cornerBox.event(match);
        assertEquals("FREE_KICK", match.getGameMode());
    }

    /**
     * Tests that the position of the FreeKickBox,
     *  is correctly set to position given in the constructor.
     */
    @Test
    void testFreekickBoxPosition() {
        assertEquals(5, cornerBox.getPosition());
    }

    /**
     * Tests that the name of the FreeKickBox is correctly set to "Freekick Box".
     */
    @Test
    void testFreekickBoxName() {
        assertEquals("Freekick Box", cornerBox.getName());
    }

    /**
     * Tests that the image of the FreeKickBox is correctly set to "casella_26.png".
     */
    @Test
    void testFreekickBoxImage() {
        assertEquals("casella_26.png", cornerBox.getImage());
    }

    /**
     * Tests that the event method of the FreeKickBox correctly updates the score,
     * based on the dice rolls.
     */
    @Test
    void testFreekickEvent() {
        final Integer diceSum;
        final int dice1;
        final int dice2;
        final int oldHomeScore = this.match.getScore().getHomeScore();
        final int oldGuestScore = this.match.getScore().getGuestScore();

        cornerBox.event(match);
        dice1 = match.diceEvent();
        dice2 = match.diceEvent();
        diceSum = dice1 + dice2;
        if (diceSum.equals(7)) {
            assertEquals(oldHomeScore + 1, this.match.getScore().getHomeScore());
            assertEquals(oldGuestScore, this.match.getScore().getGuestScore());
        } else {
            assertEquals(oldHomeScore, this.match.getScore().getHomeScore());
            assertEquals(oldGuestScore, this.match.getScore().getGuestScore());
        }
    }
}
