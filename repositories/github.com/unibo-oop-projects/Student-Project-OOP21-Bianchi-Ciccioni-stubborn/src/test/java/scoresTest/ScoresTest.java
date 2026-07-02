package scoresTest;

import models.Pair;
import models.Scores;
import models.ScoresImpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ScoresTest {
    private Scores s = new ScoresImpl();

    @Test
    public void writeScoreTest() {
        s.setScore("Marco", 50);
        s.setScore("Matteo", 150);
        s.setScore("Andrea", 180);
        List<Pair<String, Integer>> result = s.getAllScores();
        assertTrue(result.contains(new Pair<>("Marco", 50)));
        assertTrue(result.contains(new Pair<>("Matteo", 150)));
        assertTrue(result.contains(new Pair<>("Andrea", 180)));
    }

    @Test
    public void readScoreTest() {
        Pair<String, Integer> expected = new Pair<>("Marco", 50);
        List<Pair<String, Integer>> result = s.getAllScores();
        assertTrue(result.contains(expected));
    }
}
