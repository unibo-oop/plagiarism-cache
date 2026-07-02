package giocoscudetto.model.boxestest;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.api.match.Scoreboard;
import giocoscudetto.model.impl.boxes.GoalRemovedBox;
import giocoscudetto.model.impl.match.ClubImpl;
import giocoscudetto.model.impl.match.MatchImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.boxes.GoalRemovedBox}.
 */
class TestGoalRemoved {
    private final Club clubHome = new ClubImpl("home", 1);
    private final Club clubAway = new ClubImpl("away", 2);
    private final Match match = new MatchImpl(clubHome, clubAway);
    private final Scoreboard scoreboard = match.getScore();
    private final Box goalRemovedBox = new GoalRemovedBox(30);

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
    void testUnderZero() {

        verifyResult(0, 0, match.getClubHome());

        //Scores must not go below zero
        goalRemovedBox.event(match);
        verifyResult(0, 0, match.getClubAway());

        goalRemovedBox.event(match);
        verifyResult(0, 0, match.getClubHome());

    }

    @Test
    void testBoxEvent() {

        scoreboard.setHomeScore(2);
        scoreboard.setGuestScore(1);

        //Testing initial values
        verifyResult(2, 1, match.getClubHome());

        //Testing GoalRemovedBox for each team, assuming they use it
        //consecutevely on it in the match
        goalRemovedBox.event(match);
        verifyResult(1, 1, match.getClubAway());

        goalRemovedBox.event(match);
        verifyResult(1, 0, match.getClubHome());

    }

    @Test
    void testBoxPosition() {
        assertEquals(30, goalRemovedBox.getPosition());
    }

    @Test
    void testBoxName() {
        assertEquals("Goal Removed", goalRemovedBox.getName());
    }

    @Test
    void testBoxImage() {
        assertEquals("casella_33.png", goalRemovedBox.getImage());
    }
}
