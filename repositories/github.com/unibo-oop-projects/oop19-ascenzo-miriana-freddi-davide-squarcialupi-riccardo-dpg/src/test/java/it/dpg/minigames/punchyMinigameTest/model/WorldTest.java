package it.dpg.minigames.punchyMinigameTest.model;

import it.dpg.minigames.punchygame.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WorldTest {

    @Test
    public void worldTest() {
        World w = new WorldImpl();
        Boxer b = new BoxerImpl();
        Timer t = new TimerImpl();
        Score s = new ScoreImpl();

        Assertions.assertFalse(w.isGameOver());
        Assertions.assertEquals(w.getBoxerDirection(), b.getDirection());
        Assertions.assertEquals(w.getTimer(), t.getMaxTime());
        Assertions.assertEquals(w.getScore(), s.getPoints());
        Assertions.assertEquals(w.getScoreMultiplier(), s.getMultiplier());

        Assertions.assertTrue(w.checkSackHit(w.getSacks().get(0)));

        s.incrementScore();
        int score = w.getScore();
        Assertions.assertEquals(score, s.getPoints());
        Assertions.assertEquals(w.getScoreMultiplier(), s.getMultiplier());

        Direction d = w.getSacks().get(0);
        if(d == Direction.LEFT) {
            d = Direction.RIGHT;
        } else {
            d = Direction.LEFT;
        }
        Assertions.assertFalse(w.checkSackHit(d));
        s.resetCombo();
        Assertions.assertEquals(w.getScoreMultiplier(), s.getMultiplier());
        Assertions.assertEquals(w.getScore(), score);


        float elapsed = 1.7f;
        w.updateTimer(elapsed);
        Assertions.assertEquals(w.getTimer(), t.getMaxTime() - elapsed);

        w.updateTimer(t.getMaxTime());
        Assertions.assertTrue(w.isGameOver());
    }
}
