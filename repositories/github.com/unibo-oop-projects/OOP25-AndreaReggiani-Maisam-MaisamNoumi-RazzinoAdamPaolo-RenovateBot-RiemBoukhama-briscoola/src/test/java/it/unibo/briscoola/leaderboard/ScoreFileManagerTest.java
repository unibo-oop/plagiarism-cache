package it.unibo.briscoola.leaderboard;

import it.unibo.briscoola.model.api.leaderboard.Leaderboard;
import it.unibo.briscoola.model.api.leaderboard.ScoreEntry;
import it.unibo.briscoola.model.api.leaderboard.ScoreFileManager;
import it.unibo.briscoola.model.impl.leaderboard.LeaderboardImpl;
import it.unibo.briscoola.model.impl.leaderboard.ScoreEntryImpl;
import it.unibo.briscoola.model.impl.leaderboard.ScoreFileManagerImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests persistence behavior of the leaderboard score file manager.
 */
class ScoreFileManagerTest {

    private static final String TESTING_FILE = "testing.json";
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
    private final ScoreFileManager manager = new ScoreFileManagerImpl(TESTING_FILE);
    private Leaderboard board;

    /**
     * Creates a fresh leaderboard instance before each test.
     */
    @BeforeEach
    void init() {
        this.board = new LeaderboardImpl(TESTING_FILE);
    }

    @AfterEach
    void clearLeaderboard() {
        this.manager.clearLeaderBoard();
    }

    /**
     * Verifies scores are saved and then loaded in score-sorted order.
     */
    @Test void saveTest() {
        this.board.addEntries(TESTING_LIST);
        assertTrue(manager.save(board.getEntries()));
        assertEquals(manager.load(), TESTING_LIST.stream().sorted(Comparator.comparing(ScoreEntry::getScore)).toList());
    }

    /**
     * Verifies loading without saved data returns an empty list.
     */
    @Test void loadTest() {
        assertEquals(List.of(), manager.load());
    }

    /**
     * Clears persisted leaderboard data after each test.
     */
    @Test
    void clear() {
        assertTrue(this.manager.save(TESTING_LIST));
        assertEquals(TESTING_LIST, this.manager.load());
        assertTrue(this.manager.clearLeaderBoard());
    }

}
