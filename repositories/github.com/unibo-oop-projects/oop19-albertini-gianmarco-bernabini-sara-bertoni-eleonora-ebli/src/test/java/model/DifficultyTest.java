package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.difficulty.DiffValSpeedOffsetIncrement;
import model.difficulty.Difficulty;
import model.difficulty.SimpleIncreasingDifficulty;
import model.difficulty.SpeedOffsetIncrement;

public class DifficultyTest {
    private final int limit = 10;
    private final SpeedOffsetIncrement speedIncrement = new DiffValSpeedOffsetIncrement();
    private final Difficulty difficulty = new SimpleIncreasingDifficulty(limit, speedIncrement);

    @Test
    public void testDiffValSpeedOffsetIncrement() {
        for (int i = 0; i < limit; i++) {
            assertTrue(speedIncrement.getIncrement() == 0.1);
            assertTrue(speedIncrement.getIncrement() == 0.07);
            assertTrue(speedIncrement.getIncrement() == 0.06);
            assertTrue(speedIncrement.getIncrement() == 0.03);
        }

    }

    @Test
    public void testDifficulty() {
        int tempShort = difficulty.getNShort();
        int tempLong = difficulty.getNLong();
        double tempSpeed = difficulty.getMaxSpeed();
        boolean turn = true;

        for (int i = tempShort + tempLong; i < limit; i++) {
            difficulty.increase();
            if (turn) {
                assertTrue(difficulty.getNShort() == tempShort + 1);
                assertTrue(difficulty.getNLong() == tempLong);
                tempShort++;
            } else {
                assertTrue(difficulty.getNShort() == tempShort);
                assertTrue(difficulty.getNLong() == tempLong + 1);
                tempLong++;
            }
            turn = !turn;
            assertTrue(difficulty.getMaxSpeed() > tempSpeed);
        }
        difficulty.increase();
        assertTrue(difficulty.getNLong() == tempLong);
        assertTrue(difficulty.getNShort() == tempShort);

    }
}