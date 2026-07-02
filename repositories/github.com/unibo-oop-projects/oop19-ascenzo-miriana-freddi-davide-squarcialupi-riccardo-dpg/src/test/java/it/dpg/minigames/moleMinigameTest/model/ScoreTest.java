package it.dpg.minigames.moleMinigameTest.model;


import it.dpg.minigames.molegame.model.Score;
import it.dpg.minigames.molegame.model.ScoreImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.stream.IntStream;

public class ScoreTest {
    @Test
    public void scoreTest() {
        Score s = new ScoreImpl();

        Assertions.assertSame(s.finalScore(), 0);
        IntStream.range(0, 10).forEach(i -> s.addPoint());
        Assertions.assertSame(s.finalScore(), 10);

    }
}
