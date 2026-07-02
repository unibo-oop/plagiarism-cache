package model;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import model.leaderboard.LeaderboardImpl;
import model.leaderboard.LeaderboardSortingStrategy;
import model.leaderboard.StandardScoreSortingStrategy;


class TestLeaderboard {

    private LeaderboardImpl leaderboard;
    private final Map<String, Integer> testMap = new HashMap<>();
    private final LeaderboardSortingStrategy ls = new StandardScoreSortingStrategy();
    private static final String PLAYER_ALIAS_ALEX = "Alex00";
    private static final int ALEX00_SCORE = 1800;
    private static final int ALEX00_SCORE_2 = 1300;
    private static final int JACK_SCORE = 1700;
    private static final int TOMMY_SCORE = 1500;
    private static final int MARCUS_SCORE = 300;

    @BeforeEach
    void initLeaderboard() {
        this.leaderboard = new LeaderboardImpl();
        this.leaderboard.addPlayer(PLAYER_ALIAS_ALEX, ALEX00_SCORE);
        this.leaderboard.addPlayer("-<Jack>-", JACK_SCORE);
        this.leaderboard.addPlayer("_Tommy_", TOMMY_SCORE);
        this.leaderboard.addPlayer("Marcus", MARCUS_SCORE);

        this.testMap.put("-<Jack>-", JACK_SCORE);
        this.testMap.put(PLAYER_ALIAS_ALEX, ALEX00_SCORE);
        this.testMap.put("_Tommy_", TOMMY_SCORE);
        this.testMap.put("Marcus", MARCUS_SCORE);
    }

    @Test
    void testAddPlayer() {
        assertEquals(this.leaderboard.getLeaderBoard(), testMap);
    }

    @Test
    void testOverWritePlayer() {

        this.leaderboard.addPlayer(PLAYER_ALIAS_ALEX, ALEX00_SCORE_2);
        this.testMap.put(PLAYER_ALIAS_ALEX, ALEX00_SCORE_2);

        assertEquals(this.leaderboard.getLeaderBoard(), testMap);
    }

    @Test
    void testRemovePlayer() {
        this.leaderboard.removePlayer(PLAYER_ALIAS_ALEX, ALEX00_SCORE);
        this.testMap.remove(PLAYER_ALIAS_ALEX, ALEX00_SCORE);

        assertEquals(this.leaderboard.getLeaderBoard(), testMap);
    }

    @Test
    void testRemoveNonExsistentPlayer() {
        //The player is not present in ranking
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.leaderboard.removePlayer("Peppe", ALEX00_SCORE);
        });
    }


    @Test
    void testOrder() {
        this.leaderboard = new LeaderboardImpl();
        this.leaderboard.addPlayer("Fausto", ALEX00_SCORE);
        this.leaderboard.addPlayer("Mario", TOMMY_SCORE);
        this.leaderboard.addPlayer("Alex", TOMMY_SCORE);
        this.leaderboard.sortByScore(this.ls);
        assertEquals(this.leaderboard.getLeaderBoard(), Map.of("Fausto", ALEX00_SCORE,
                                                               "Mario", TOMMY_SCORE,
                                                               "Alex", TOMMY_SCORE));

    }


}
