package giocoscudetto.model.match;

import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.impl.match.ClubImpl;
import giocoscudetto.model.impl.match.MatchImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Simple test for {@link MatchImpl}.
 */
class TestMatch {
    private Club clubHome;
    private Club clubAway;
    private Match match;

    @BeforeEach
    void setUp() {
        clubHome = new ClubImpl("clubHome", 1);
        clubAway = new ClubImpl("clubAway", 2);
        match = new MatchImpl(clubHome, clubAway);
    }

    /**
     * Tests that the creation of the match was correct.
     */
    @Test
    void testInitial() {
        assertNotNull(match.getClubHome());
        assertNotNull(match.getClubAway());
        assertEquals(clubHome, match.getClubHome());
        assertEquals(clubAway, match.getClubAway());
        assertEquals(0, match.getScore().getGuestScore());
        assertEquals(0, match.getScore().getHomeScore());
    }

    /**
     * Tests adding goals, removing goals and setting the goals to certain value.
     */
    @Test
    void testGoals() {
        match.goalHome();
        match.goalHome();
        match.goalAway();
        match.goalAway();
        match.goalAway();
        assertEquals(2, match.getScore().getHomeScore());
        assertEquals(3, match.getScore().getGuestScore());

        match.removeGoalHome();
        match.removeGoalAway();
        assertEquals(1, match.getScore().getHomeScore());
        assertEquals(2, match.getScore().getGuestScore());

        match.setGoalHome(0);
        match.setGoalAway(0);
        assertEquals(0, match.getScore().getHomeScore());
        assertEquals(0, match.getScore().getGuestScore());
    }

    @Test
    void testTurn() {
        final Club first = this.match.getCurrentPlayer();
        this.match.turn();
        final Club second = this.match.getCurrentPlayer();
        this.match.turn();
        assertEquals(first, this.match.getCurrentPlayer());
        this.match.turn();
        assertEquals(second, this.match.getCurrentPlayer());
    }

    /**
     * Tests that the winner and the loser of the match are assigned correctly.
     */
    @Test
    void testWinnerAndLoser() {
        //Fixed implementation by adding the Optional
        assertEquals(Optional.empty(), match.getWinnerClub());
        assertEquals(Optional.empty(), match.getLoserClub());

        match.setGoalHome(1);
        match.setGoalAway(0);
        assertEquals(clubHome, match.getWinnerClub().get());
        assertEquals(clubAway, match.getLoserClub().get());

        match.setGoalHome(0);
        match.setGoalAway(1);
        assertEquals(clubAway, match.getWinnerClub().get());
        assertEquals(clubHome, match.getLoserClub().get());
    }

    /**
     * Tests that before the half it throws 2 dices and after the half it throws 1 dice.
     */
    @Test
    void testDiceLogic() {
        int count;
        match.getClubHome().getPawn().setPosition(0);
        if (!this.match.getCurrentPlayer().equals(this.match.getClubHome())) {
            match.turn();
        }
        for (count = 0; count < 10_000; count++) {
            assertTrue(match.rollDice() <= 12);
        }
        match.getClubHome().getPawn().setPosition(24);
        for (count = 0; count < 10_000; count++) {
            assertTrue(match.rollDice() <= 6);
        }
    }

    /**
     * Tests the corner event.
     */
    @Test
    void testCornerEvent() {
        int count = 0;
        int first; 
        int second;
        if (!this.match.getCurrentPlayer().equals(this.match.getClubHome())) {
            this.match.turn();
        }
        assertEquals(0, this.match.getScore().getHomeScore());
        this.match.setGameMode(Match.GameMode.CORNER);
        assertEquals(Match.GameMode.CORNER.toString(), this.match.getGameMode());
        for (int i = 0; i < 10_000; i++) {
            first = this.match.diceEvent();
            second = this.match.diceEvent();
            if (first == 1 || second == 1) {
                count = count + 1;
            }
        }
        assertEquals(count, this.match.getScore().getHomeScore());
        assertEquals(0, match.getScore().getGuestScore());
    }

    /**
     * Tests the free kick event.
     */
    @Test
    void testFreekickEvent() {
        int count = 0;
        int first; 
        int second;
        if (!this.match.getCurrentPlayer().equals(this.match.getClubHome())) {
            this.match.turn();
        }
        assertEquals(0, this.match.getScore().getHomeScore());
        this.match.setGameMode(Match.GameMode.FREE_KICK);
        assertEquals(Match.GameMode.FREE_KICK.toString(), this.match.getGameMode());
        for (int i = 0; i < 10_000; i++) {
            first = this.match.diceEvent();
            second = this.match.diceEvent();
            if (first + second == 7) {
                count = count + 1;
            }
        }
        assertEquals(count, this.match.getScore().getHomeScore());
        assertEquals(0, match.getScore().getGuestScore());
    }

    /**
     * Tests the penalty event, the sets of the keeper position and the last shoot position.
     */
    @Test
    void testPenaltyEvent() {
        int count = 0;
        if (!this.match.getCurrentPlayer().equals(this.match.getClubHome())) {
            this.match.turn();
        }
        assertEquals(0, this.match.getScore().getHomeScore());
        this.match.setGameMode(Match.GameMode.PENALTY);
        assertEquals(Match.GameMode.PENALTY.toString(), this.match.getGameMode());
        for (int i = 0; i < 10_000; i++) {
            this.match.setKeeperPosition(1);
            this.match.setKeeperPosition(2);
            this.match.eventMode();
            if (this.match.getLastShootPosition() != 1 && this.match.getLastShootPosition() != 2) {
                count++;
            }
        }
        assertEquals(count, this.match.getScore().getHomeScore());
        assertEquals(0, match.getScore().getGuestScore());
    }

    /**
     * Tests the result event.
     */
    @Test
    void testResultEvent() {
        if (!this.match.getCurrentPlayer().equals(this.match.getClubHome())) {
            this.match.turn();
        }
        assertEquals(0, this.match.getScore().getHomeScore());
        assertEquals(0, this.match.getScore().getGuestScore());
        this.match.setGameMode(Match.GameMode.RESULT);
        for (int i = 0; i < 10_000; i++) {
            final int goalHome = this.match.diceEvent();
            final int goalAway = this.match.diceEvent();
            assertEquals(goalHome, this.match.getScore().getHomeScore());
            assertEquals(goalAway, this.match.getScore().getGuestScore());
        }
    }
}
