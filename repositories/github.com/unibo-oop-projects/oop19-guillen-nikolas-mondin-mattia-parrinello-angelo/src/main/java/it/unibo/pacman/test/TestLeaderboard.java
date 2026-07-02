package it.unibo.pacman.test;

import static it.unibo.pacman.model.utilities.Difficulty.TEST;
import static it.unibo.pacman.model.utilities.Difficulty.NORMAL;
import static it.unibo.pacman.model.utilities.Difficulty.HARD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import it.unibo.pacman.controller.LeaderboardController;
import it.unibo.pacman.controller.LeaderboardControllerImpl;
import it.unibo.pacman.controller.utilities.LeaderboardIO;
import it.unibo.pacman.model.utilities.Pair;

public class TestLeaderboard {
    private static final int FIRST = 4820;
    private static final int SECOND = 1706;
    private static final int THIRD = 4496;
    private static final int FOURTH = 2455;
    private static final int FIFTH = 750;
    private static final String SEP = System.getProperty("file.separator");
    private static final String PATH = System.getProperty("user.home") + SEP + "pacmanRes" + SEP + "Leaderboards" + SEP;
    private LeaderboardController lbc;

    // Tests if the LeaderboardIO writes on the txt correctly.
    @Test
    public void testLeaderboardWrite() {
        if (new File(PATH).mkdirs()) {
            try {
                if (new File(PATH + "Ranking_" + TEST.toString() + ".txt").createNewFile()) {
                    System.out.println("Test ranking successfully created");
                }
                if (new File(PATH + "Ranking_" + NORMAL.toString() + ".txt").createNewFile()) {
                    System.out.println("Normal ranking successfully created");
                }
                if (new File(PATH + "Ranking_" + HARD.toString() + ".txt").createNewFile()) {
                    System.out.println("Hard ranking successfully created");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.lbc = new LeaderboardControllerImpl(null, null);
        Optional<PrintWriter> pwOb = Optional.empty();
        try {
            pwOb = Optional.of(new PrintWriter(new FileWriter(PATH + "Ranking_" + TEST.toString() + ".txt", false), false));
            pwOb.get().flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        pwOb.get().close();

        final List<String> players = Arrays.asList("Primo", "Secondo", "Terzo", "Quarto", "Quinto");
        final List<Integer> scores = Arrays.asList(FIRST, SECOND, THIRD, FOURTH, FIFTH);
        for (int i = 0; i <= 4; i++) {
            lbc.writeScore(players.get(i), scores.get(i), TEST);
        }

        // This part of the test cannot be done through Leaderboard's controller because
        // the method that does this action is private
        LeaderboardIO.readLeaderboard(TEST);
        final List<Pair<String, Integer>> ranking = new ArrayList<>();
        try {
            String player;
            player = LeaderboardIO.getNext();
            int score;
            while (player != null) {
                score = Integer.parseInt(LeaderboardIO.getNext());
                ranking.add(new Pair<>(player, score));
                player = LeaderboardIO.getNext();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final List<String> comparePlayers = new ArrayList<>();
        final List<Integer> compareScores = new ArrayList<>();
        for (final Pair<String, Integer> p : ranking) {
            comparePlayers.add(p.getX());
            compareScores.add(p.getY());
        }
        // If these tests return true, it means that the Read/Write operations were done succesfully
        assertEquals(players, comparePlayers);
        assertEquals(scores, compareScores);
    }

    // Tests if the map is sorted in descending order based on map's value.
    @Test
    public void testOrderedMap() {
        this.lbc = new LeaderboardControllerImpl(null, null);
        final Map<String, Integer> readScores = this.lbc.getSortedRanking(TEST);
        final List<Integer> scores = new ArrayList<>();
        for (final String s : readScores.keySet()) {
            scores.add(readScores.get(s));
        }
        assertTrue(isSorted(scores));
    }

    private boolean isSorted(final List<Integer> list) {
        boolean sorted = true;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) < 0) {
                sorted = false;
            }
        }
        return sorted;
    }
}
