package test.it.unibo.oop.manpac.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unibo.oop.manpac.model.score.*;

/**
 * Testing class for scoring.
 */
public class TestScore {

    /**
     * test semplice per il gestore dello score nel caso Integer.
     */
    @Test
    public void setNegativeValue() {
        final Points<Integer> hs = new PointsImpl.Mutable<>(-20);
        final ScorePoints<Integer> sp = new ScorePointsInteger(hs);

        assertTrue(sp.getCurrentScore().getValue() == 0);
        assertTrue(sp.getHighScore().getValue() == 0);

        sp.increaseCurrentScore(new PointsImpl<>(-10));
        sp.increaseCurrentScore(new PointsImpl<>(100));// 100
        sp.increaseCurrentScore(new PointsImpl<>(25));// 125
        sp.increaseCurrentScore(new PointsImpl<>(50));// 175
        sp.increaseCurrentScore(new PointsImpl<>(-100));// 75
        assertTrue(sp.getCurrentScore().getValue() == 75);// ==>75
        assertTrue(sp.getHighScore().getValue() == 175);// ==>175

        sp.increaseCurrentScore(new PointsImpl<>(-100));// 0
        assertTrue(sp.getCurrentScore().getValue() == 0);// ==>0
        assertTrue(sp.getHighScore().getValue() == 175);// ==>175

    }

    /**
     * test più approfondito sempre nel caso Integer.
     */
    @Test
    public void setAndResetScores() {

        final ScorePoints<Integer> sp = new ScorePointsInteger();
        assertTrue(sp.getCurrentScore().getValue() == 0);
        assertTrue(sp.getHighScore().getValue() == 0);

        sp.increaseCurrentScore(new PointsImpl<>(50));
        sp.increaseCurrentScore(null);
        assertTrue(sp.getCurrentScore().getValue() == 50);
        assertTrue(sp.getHighScore().getValue() == 50);

        sp.newHighScore();
        assertTrue(sp.getCurrentScore().getValue() == 50);
        assertTrue(sp.getHighScore().getValue() == 50);

        sp.resetCurrentScore();
        assertTrue(sp.getCurrentScore().getValue() == 0);
        assertTrue(sp.getHighScore().getValue() == 50);

        sp.setHighScore(new PointsImpl.Mutable<>(25));
        assertTrue(sp.getCurrentScore().getValue() == 0);
        assertTrue(sp.getHighScore().getValue() == 25);

        sp.resetScorePoints();
        assertTrue(sp.getCurrentScore().getValue() == 0);
        assertTrue(sp.getHighScore().getValue() == 0);

        sp.increaseCurrentScore(new PointsImpl<>(50));
        sp.setHighScore(null);
        sp.newHighScore();
        assertTrue(sp.getCurrentScore().getValue() == 50);
        assertTrue(sp.getHighScore().getValue() == 50);

    }

}
