import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import model.obstacle.CircularObstacle;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleBehavior;
import model.score.Score;
import model.score.ScoreImpl;

class ScoreTest {

    private static final int MULTIPLIER_X2 = 2;
    private static final int MULTIPLIER_X3 = 3;
    private static final int MULTIPLIER_X5 = 5;
    private static final int MULTIPLIER_X10 = 10;

    @Test
    final void testUpdateScore() {

        // Adds a blue obstacle and tests if the score and multiplier are correct
        final Score score = new ScoreImpl();
        score.updateScore(List.of(new CircularObstacle(Pair.of(1., 1.))));
        assertEquals(1, score.getMultiplier());
        assertEquals(ObstacleBehavior.BLU.getScore(), score.getCurrentScore());

        // Adds 10 red obstacles and tests if the multiplier is correct
        final List<Obstacle> redlist = Stream.iterate(0, i -> i + 1).limit(10)
                .map(i -> new CircularObstacle(Pair.of((double) i, (double) i)))
                .peek(o -> o.setObstacleBehavior(ObstacleBehavior.RED)).collect(Collectors.toList());
        score.updateScore(redlist);
        assertEquals(MULTIPLIER_X2, score.getMultiplier());

        // Adds red obstacles to a total of 15 and tests if the multiplier is correct
        final List<Obstacle> redlist2 = Stream.iterate(0, i -> i + 1).limit(5)
                .map(i -> new CircularObstacle(Pair.of((double) i, (double) i)))
                .peek(o -> o.setObstacleBehavior(ObstacleBehavior.RED)).collect(Collectors.toList());
        score.updateScore(redlist2);
        assertEquals(MULTIPLIER_X3, score.getMultiplier());

        // Adds red obstacles to a total of 19 and tests if the multiplier is correct
        final List<Obstacle> redlist3 = Stream.iterate(0, i -> i + 1).limit(4)
                .map(i -> new CircularObstacle(Pair.of((double) i, (double) i)))
                .peek(o -> o.setObstacleBehavior(ObstacleBehavior.RED)).collect(Collectors.toList());
        score.updateScore(redlist3);
        assertEquals(MULTIPLIER_X5, score.getMultiplier());
        // Adds red obstacles to a total of 22 and tests if the multiplier is correct
        final List<Obstacle> redlist4 = Stream.iterate(0, i -> i + 1).limit(3)
                .map(i -> new CircularObstacle(Pair.of((double) i, (double) i)))
                .peek(o -> o.setObstacleBehavior(ObstacleBehavior.RED)).collect(Collectors.toList());
        score.updateScore(redlist4);
        assertEquals(MULTIPLIER_X10, score.getMultiplier());

        // Adds a last obstacle and tests if the score is correct
        final int previousScore = score.getCurrentScore();
        score.updateScore(List.of(new CircularObstacle(Pair.of(2., 2.))));
        assertEquals(ObstacleBehavior.BLU.getScore() * MULTIPLIER_X10 + previousScore, score.getCurrentScore());

    }

}
