package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import controller.HighScoreManager;
import controller.HighScoreManagerImpl;
import utilities.Pair;

public class HighScoreTest {

    @Test
    public void testHighScore() {
        final HighScoreManager hsm = new HighScoreManagerImpl("testHighScore.txt", 5);
        final List<Pair<String, Integer>> scoreList = new ArrayList<>();
        final Pair<String, Integer> p1 = new Pair<>("AAA", 20000);
        final Pair<String, Integer> p2 = new Pair<>("PAU", 19000);
        final Pair<String, Integer> p3 = new Pair<>("POO", 9000);
        final Pair<String, Integer> p4 = new Pair<>("POO", 16700);
        final Pair<String, Integer> p5 = new Pair<>("MAX", 15700);
        final Pair<String, Integer> p6 = new Pair<>("AND", 11000);

        /* insert first 5 scores */
        hsm.addScore(p1);
        hsm.addScore(p2);
        hsm.addScore(p3);
        hsm.addScore(p4);
        hsm.addScore(p5);

        /* creation of the scoreList */
        scoreList.add(p1);
        scoreList.add(p2);
        scoreList.add(p4);
        scoreList.add(p5);
        scoreList.add(p3);

        /* check if the lists are equals */
        assertEquals("The lists should be equals",scoreList, hsm.getScores());

        /* check if the high score list has a real limit */
        hsm.addScore(p6);
        assertSame("The high score list should have a limit!", 5, hsm.getScores().size());

        scoreList.remove(p3);
        scoreList.add(p6);
        assertEquals("The score should be displayed", scoreList, hsm.getScores());

    }
}
