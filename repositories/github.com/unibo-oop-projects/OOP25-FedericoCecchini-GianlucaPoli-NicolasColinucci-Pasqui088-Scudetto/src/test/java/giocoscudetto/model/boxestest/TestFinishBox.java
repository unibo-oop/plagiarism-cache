package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.impl.boxes.FinishBox;
import giocoscudetto.model.impl.match.ClubImpl;
import giocoscudetto.model.impl.match.MatchImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Simple test for {@link FinishBox}.
 */
class TestFinishBox {
    private static final int BOX_POSITION = 32;

    private Club clubHome;
    private Club clubAway;
    private Match match;
    private Box finishBox;

    @BeforeEach
    void setUp() {
        clubHome = new ClubImpl("home", 1);
        clubAway = new ClubImpl("away", 2);
        this.clubHome.getPawn().setPosition(32);
        this.clubAway.getPawn().setPosition(16);
        match = new MatchImpl(clubHome, clubAway);
        finishBox = new FinishBox(BOX_POSITION);
        match.setGoalHome(3);
        match.setGoalAway(2);
        if (!match.getCurrentPlayer().equals(clubHome)) {
            match.turn();
        }
    }

    /**
     * Tests the get name.
     */
    @Test
    void testGetName() {
        assertEquals("Finish Box", finishBox.getName());
    }

    /**
     * Tests the get position.
     */
    @Test
    void testGetPosition() {
        assertEquals(BOX_POSITION, finishBox.getPosition());
    }

    /**
     * Tests the get image.
     */
    @Test
    void testGetImage() {
        assertEquals("casella_32.png", finishBox.getImage());
    }

    /**
     * Tests the box event.
     */
    @Test
    void testEventGameMode() {
        finishBox.event(match);
        assertEquals(32, clubHome.getPawn().getPosition());
        assertEquals(32, clubAway.getPawn().getPosition());
        assertEquals(3, match.getScore().getHomeScore());
        assertEquals(2, match.getScore().getGuestScore());
    }
}

