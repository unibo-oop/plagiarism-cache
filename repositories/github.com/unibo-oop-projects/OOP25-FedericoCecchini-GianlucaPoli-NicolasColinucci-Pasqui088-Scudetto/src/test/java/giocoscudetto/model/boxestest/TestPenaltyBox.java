package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.impl.boxes.PenaltyBox;
import giocoscudetto.model.impl.match.ClubImpl;
import giocoscudetto.model.impl.match.MatchImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.boxes.PenaltyBox}.
 */
class TestPenaltyBox {
    private final Club clubHome = new ClubImpl("home", 1);
    private final Club clubAway = new ClubImpl("away", 2);
    private Match match;
    private PenaltyBox penaltyBox;

    /**
     * Sets up the test environment before each test method is executed.
     */
    @BeforeEach
    void setUp() {
        match = new MatchImpl(clubHome, clubAway);
        penaltyBox = new PenaltyBox(5);
        if (!match.getCurrentPlayer().equals(clubHome)) {
            match.turn();
        }
    }

    /**
     * Tests that the event method of the PenaltyBox sets the game mode to "PENALTY".
     */
    @Test
    void testEventSetsGameModeToPenalty() {
        assertNotEquals("PENALTY", match.getGameMode());
        penaltyBox.event(match);
        assertEquals("PENALTY", match.getGameMode());
    }

    /**
     * Tests that the position of the PenaltyBox is correctly set, 
     * to position given in the constructor.
     */
    @Test
    void testPenaltyBoxPosition() {
        assertEquals(5, penaltyBox.getPosition());
    }

    /**
     * Tests that the name of the PenaltyBox is correctly set to "Penalty Box".
     */
    @Test
    void testPenaltyBoxName() {
        assertEquals("Penalty Box", penaltyBox.getName());
    }

    /**
     * Tests that the image of the PenaltyBox is correctly set to "casella_16.png".
     */
    @Test
    void testPenaltyBoxImage() {
        assertEquals("casella_16.png", penaltyBox.getImage());
    }
}
