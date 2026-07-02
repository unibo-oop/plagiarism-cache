package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// CHECKSTYLE: OFF
//The annotation is needed to avoid false positives
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
// CHECKSTYLE: ON
import javawulf.scoreboard.ResultImpl;
import javawulf.scoreboard.ScoreBoardImpl;
import javawulf.scoreboard.Scoreboard;

/**
 * Tests for Scoreboard add, sort, and overflow.
 */
// CHECKSTYLE: MagicNumber OFF
// During this test are used for representing hypotetical Results of players.
// For this reason this warning was suppressed.
final class ScoreBoardTest {

    private Scoreboard scoreboard;

    /**
     * Setup for hypotetical Scoreboard.
     */
    @BeforeEach
    void setUpScoreBoard() {
        scoreboard = new ScoreBoardImpl();
    }

    @Test
    @SuppressFBWarnings(
        value = {
            "L", "D", "UwF"
        },
        justification = "Score is initialized in the @BeforeEach"
    )
    void testAddNewScore() {
        scoreboard.addNewScore(new ResultImpl("marco", 1130, false));
        assertEquals(1, scoreboard.getAllScores().size());
        assertEquals(1130, scoreboard.getAllScores().get(0).getScore());
    }

    @Test
    @SuppressFBWarnings(
        value = {
            "L", "D", "UwF"
        },
        justification = "Score is initialized in the @BeforeEach"
    )
    void testSortScoreboard() {
        scoreboard.addNewScore(new ResultImpl("giovanni", 1200, false));
        scoreboard.addNewScore(new ResultImpl("giacomo", 1300, true));
        scoreboard.addNewScore(new ResultImpl("aldo", 1100, false));
        scoreboard.addNewScore(new ResultImpl("shrek", 100, false));

        assertEquals("giacomo", scoreboard.getAllScores().get(0).getUserName());
        assertEquals("giovanni", scoreboard.getAllScores().get(1).getUserName());
    }

    @Test
    @SuppressFBWarnings(
        value = {
            "L", "D", "UwF"
        },
        justification = "Score is initialized in the @BeforeEach"
    )
    void testTooManyResults() {
        //add of max players
        scoreboard.addNewScore(new ResultImpl("giovanni", 1200, false));
        scoreboard.addNewScore(new ResultImpl("giacomo", 1300, true));
        scoreboard.addNewScore(new ResultImpl("aldo", 1100, false));
        scoreboard.addNewScore(new ResultImpl("shrek", 100, false));
        scoreboard.addNewScore(new ResultImpl("franco", 1200, false));
        scoreboard.addNewScore(new ResultImpl("tony", 777, true));
        scoreboard.addNewScore(new ResultImpl("pyrex", 777, true));
        scoreboard.addNewScore(new ResultImpl("wayne", 777, true));
        scoreboard.addNewScore(new ResultImpl("side", 777, true));
        scoreboard.addNewScore(new ResultImpl("marco", 100, true));

        assertEquals(Scoreboard.SCOREBOARD_SIZE, scoreboard.getAllScores().size());

    }
}
// CHECKSTYLE: MagicNumber ON
