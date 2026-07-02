package model.ranking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import model.components.GameTimerImpl;
import model.ranking.Leaderboard;
import model.ranking.LeaderboardImpl;
import model.ranking.Player;
import model.ranking.PlayerImpl;

/**
 * 
 * Tests the functionality of classes LeaderboardImpl and PlayerImpl.
 *
 */
public class RankingTest {

    private static Leaderboard leaderboard = new LeaderboardImpl();
    private static GameTimerImpl timer = new GameTimerImpl();
    private static Player player1;
    private static Player player2;
    private static Player player3;
    private static Player player4;
    private static Player player5;

    /**
     * Prepares the tests by deleting the existing leaderboard's file (if it's present)
     * and by creating and adding 5 players to the leaderboard's json file.
     */
    @BeforeAll
    public static void before() {
        leaderboard.deleteLeaderboard();
        player1 = new PlayerImpl("p1", timer, 10);
        player2 = new PlayerImpl("p2", timer, 2);
        player3 = new PlayerImpl("p3", timer, 5);
        player4 = new PlayerImpl("p4", timer, 20);
        player5 = new PlayerImpl("p5", timer, 100);
        leaderboard.addPlayer(player1);
        leaderboard.addPlayer(player2);
        leaderboard.addPlayer(player3);
        leaderboard.addPlayer(player4);
        leaderboard.addPlayer(player5);
    }

    /**
     * Tests if the players are all written on json file.
     */
    @Test
    public void testAddPlayer() {
        final List<Player> playersList = new ArrayList<>();
        playersList.addAll(List.of(player1, player2, player3, player4, player5));
        assertEquals(playersList.size(), leaderboard.getSortedPlayersList().size());
    }

    /**
     * Tests if the players list is sorted in the correct order.
     */
    @Test
    public void testGetSortedPlayersList() {
        final List<Player> original = leaderboard.getSortedPlayersList();
        final List<Player> sorted = List.of(player5, player4, player1, player3, player2);
        assertEquals(original.get(0), sorted.get(0));
        assertEquals(original.get(1), sorted.get(1));
        assertEquals(original.get(2), sorted.get(2));
        assertEquals(original.get(3), sorted.get(3));
        assertEquals(original.get(4), sorted.get(4));
    }

    /**
     * Tests that if two players are compared in the right way;.
     */
    @Test
    public void testComparePlayers() {
        assertEquals(-5, player1.compareTo(player3));
        assertEquals(18, player2.compareTo(player4));
        assertEquals(95, player3.compareTo(player5));
        assertEquals(-10, player4.compareTo(player1));
        assertEquals(-98, player5.compareTo(player2));
    }

    /**
     * Deletes the file after all tests to avoid problems with the leaderboard.
     */
    @AfterAll
    public static void after() {
        leaderboard.deleteLeaderboard();
    }
}
