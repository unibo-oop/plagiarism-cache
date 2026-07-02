package it.unibo.briscoola.leaderboard;

import it.unibo.briscoola.model.api.leaderboard.Leaderboard;
import it.unibo.briscoola.model.api.leaderboard.ScoreEntry;
import it.unibo.briscoola.model.impl.leaderboard.LeaderboardImpl;
import it.unibo.briscoola.model.impl.leaderboard.ScoreEntryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test suite for {@link Leaderboard} interface and
 *  * {@link LeaderboardImpl} implementation.
 */
class LeaderboardTest {
    private static final Integer LOW_SCORE = 100;
    private static final Integer AVERAGE_SCORE = 150;
    private static final Integer MEDIUM_SCORE = 200;
    private static final Integer HIGH_SCORE = 300;
    private static final List<ScoreEntry> TESTING_LIST = new ArrayList<>(List.of(
            new ScoreEntryImpl("Adam", LOW_SCORE),
                new ScoreEntryImpl("Giacomo", MEDIUM_SCORE),
                new ScoreEntryImpl("Francesca", HIGH_SCORE),
                new ScoreEntryImpl("Marta", AVERAGE_SCORE)
    ));
    private Leaderboard board;

    /**
     * Initializes the {@link Leaderboard} before every test.
     */
    @BeforeEach
    void init() {
        this.board = new LeaderboardImpl("testing.json");
    }

    /**
     * Tests that every singular added entry is added correctly,
     * that the board doesn't accept null values nor {@link ScoreEntry}
     * with score == 0.
     */
    @Test
    void leaderboardTest() {
        for (final ScoreEntry e : TESTING_LIST) {
            assertTrue(this.board.addEntry(e));
        }
        assertEquals(this.board.getEntries(),
                TESTING_LIST.stream()
                        .sorted(Comparator.comparing(ScoreEntry::getScore))
                        .toList());
        assertThrows(NullPointerException.class, () -> this.board.addEntry(null));
        assertFalse(this.board.addEntry(new ScoreEntryImpl("Gino", 0)));
    }

    /**
     * Tests that the method addEntries() works correctly.
     */
    @Test void leaderboardListTest() {
        assertTrue(this.board.addEntries(TESTING_LIST));
        assertEquals(this.board.getEntries(),
                TESTING_LIST.stream()
                        .sorted(Comparator.comparing(ScoreEntry::getScore))
                        .toList());
    }
}
