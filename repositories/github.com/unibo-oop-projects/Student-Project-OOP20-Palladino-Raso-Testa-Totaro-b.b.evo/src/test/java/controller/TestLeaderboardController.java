package controller;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.leaderboard.LeaderboardController;
import controller.leaderboard.LeaderboardControllerImpl;
import model.leaderboard.LeaderboardSortingStrategy;
import model.leaderboard.Player;
import model.leaderboard.PlayerImpl;
import model.leaderboard.StandardScoreSortingStrategy;

class TestLeaderboardController {

    private static final String NAME_1 = "Alex";
    private static final String NAME_2 = "Giacomo";
    private static final String NAME_3 = "Alessandro";
    private static final String NAME_4 = "Francesco";
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
    private static final int SCORE_1 = 1234;
    private static final int SCORE_2 = 1700;
    private static final int SCORE_3 = 2000;
    private static final int SCORE_4 = 2400;
    private static final int LIFE = 3;
    private static final int PODIUM = 3;
    private LeaderboardController controller;
    private Map<String, Integer> map;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    @BeforeEach
    void initController() {
        this.controller = new LeaderboardControllerImpl(LEADERBOARD_PATH);
        this.player1 = new PlayerImpl(NAME_1, SCORE_1, LIFE, LIFE);
        this.player2 = new PlayerImpl(NAME_2, SCORE_2, LIFE, LIFE);
        this.player3 = new PlayerImpl(NAME_3, SCORE_3, LIFE, LIFE);
        this.player4 = new PlayerImpl(NAME_4, SCORE_4, LIFE, LIFE);
        this.map = new HashMap<>();
        this.map.put(this.player1.getAlias(), this.player1.getScore());
        this.map.put(this.player2.getAlias(), this.player2.getScore());
        this.map.put(this.player3.getAlias(), this.player3.getScore());
        this.map.put(this.player4.getAlias(), this.player4.getScore());
    }

    @Test
    void testAddPlayer() {
        assertDoesNotThrow(() -> {
            this.controller.addPlayerInLeaderBoard(this.player1);
            this.controller.addPlayerInLeaderBoard(this.player2);
            this.controller.addPlayerInLeaderBoard(this.player3);
            this.controller.addPlayerInLeaderBoard(this.player4); 
            assertEquals(this.map, this.controller.viewLeaderboard());
        });
    }

    @Test
    void testGetPodium() {
        assertDoesNotThrow(() -> {
            this.testAddPlayer();
            assertEquals(this.map.entrySet()
                                 .stream()
                                 .filter(x -> x.getValue() >= SCORE_2)
                                 .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), 
                         this.controller.getPodium(PODIUM, this.ls));
        });
    }

    @Test
    void testClearLeaderboard() {
        assertDoesNotThrow(() -> {
            this.controller.clearLeaderboard();
            assertEquals(Map.of(), this.controller.viewLeaderboard());
        });
    }

    @Test
    void testSaveLeaderboard() {
        assertDoesNotThrow(() -> {
            this.controller.clearLeaderboard();
            this.testAddPlayer();
            this.controller.saveSortLeaderboard(this.ls);
        });
    }

}
