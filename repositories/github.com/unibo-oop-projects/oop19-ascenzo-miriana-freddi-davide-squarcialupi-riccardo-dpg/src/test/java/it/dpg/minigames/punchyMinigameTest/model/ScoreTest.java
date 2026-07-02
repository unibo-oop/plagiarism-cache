package it.dpg.minigames.punchyMinigameTest.model;

import it.dpg.minigames.punchygame.model.Score;
import it.dpg.minigames.punchygame.model.ScoreImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class ScoreTest {

    @Test
    public void scoreTest() {
        Score s = new ScoreImpl();
        Assertions.assertSame(s.getPoints(), 0);
        Assertions.assertSame(s.getMultiplier(), 1);

        IntStream.range(0, 10).forEach(i -> s.incrementScore());
        Assertions.assertSame(s.getPoints(), 10);
        Assertions.assertSame(s.getMultiplier(), 2);
    }
}
