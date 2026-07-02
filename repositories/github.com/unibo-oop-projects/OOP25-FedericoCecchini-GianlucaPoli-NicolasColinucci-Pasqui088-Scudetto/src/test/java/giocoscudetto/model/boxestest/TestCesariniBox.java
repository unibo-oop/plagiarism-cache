package giocoscudetto.model.boxestest;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.api.match.Scoreboard;
import giocoscudetto.model.impl.boxes.CesariniBox;
import giocoscudetto.model.impl.match.ClubImpl;
import giocoscudetto.model.impl.match.MatchImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.boxes.CesariniBox}.
 */
class TestCesariniBox {

    private final Club clubHome = new ClubImpl("home", 1);
    private final Club clubAway = new ClubImpl("away", 2);
    private final Match match = new MatchImpl(clubHome, clubAway);
    private final Scoreboard scoreboard = match.getScore();
    private final Box cesariniBox = new CesariniBox(31);

    @BeforeEach
    void setUpCurrentPlayer() {

        //Setting homeClub as the match current club
        if (!match.getCurrentPlayer().equals(clubHome)) {
            match.turn();
        }
    }

    private void verifyResult(final int homeResult,
                            final int guestResult,
                            final Club actualCurrentClub) {
        assertEquals(homeResult, scoreboard.getHomeScore());
        assertEquals(guestResult, scoreboard.getGuestScore());
        assertEquals(actualCurrentClub, match.getCurrentPlayer());
    }

    @Test
    void testBoxEvent() {

        scoreboard.setHomeScore(3);
        scoreboard.setGuestScore(1);

        //Testing Initial Values
        verifyResult(3, 1, match.getClubHome());

        //Testing CesariniBox for each team, assuming they use it
        //consecutevely on it in the match
        cesariniBox.event(match);
        verifyResult(4, 1, match.getClubAway());

        cesariniBox.event(match);
        verifyResult(4, 2, match.getClubHome());

    }

    @Test
    void testBoxPosition() {
        assertEquals(31, cesariniBox.getPosition());
    }

    @Test
    void testBoxName() {
        assertEquals("Cesarini Zone", cesariniBox.getName());
    }

    @Test
    void testBoxImage() {
        assertEquals("casella_30.png", cesariniBox.getImage());
    }

}
