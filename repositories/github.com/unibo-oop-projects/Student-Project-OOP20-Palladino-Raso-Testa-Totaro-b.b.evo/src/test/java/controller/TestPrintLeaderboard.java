package controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.utilities.IOLeaderboard;
import model.leaderboard.Leaderboard;
import model.leaderboard.LeaderboardImpl;
import model.leaderboard.LeaderboardSortingStrategy;
import model.leaderboard.StandardScoreSortingStrategy;

class TestPrintLeaderboard {

    private Leaderboard rank;
    private Map<String, Integer> map;
    private static final int NUMBER_PLAYER = 100_000;
    private final LeaderboardSortingStrategy ls = new StandardScoreSortingStrategy();
    private static final String SEP = System.getProperty("file.separator");
    private static final String RES_PATH = System.getProperty("user.home");
    public static final String LEADERBOARD_PATH = RES_PATH
                                                  + SEP
                                                  + ".BrickBreakerEvo" 
                                                  + SEP
                                                  + "Leaderboards"
                                                  + SEP
                                                  + "ranking.json"; 
    @BeforeEach
    void initRank() {
        this.rank = new LeaderboardImpl();
        this.map = new HashMap<>();
        for (int i = 0; i < NUMBER_PLAYER; i++) {
            this.rank.addPlayer(String.valueOf(i), i);
            this.map.put(String.valueOf(i), i);
        }
        this.rank.sortByScore(this.ls);
    }

    @Test
    void testCorrectPrintJsonFormat() {
        assertDoesNotThrow(() -> {
            IOLeaderboard.printInJsonFormat(LEADERBOARD_PATH, this.rank.getLeaderBoard());
        });
    }

    @Test
    void testCorrectReadJsonFormat() {
        assertDoesNotThrow(() -> {
            final Map<String, Integer> map2 = IOLeaderboard.readLeaderboard(LEADERBOARD_PATH);
            assertEquals(this.map, map2);
        });
    }

}
