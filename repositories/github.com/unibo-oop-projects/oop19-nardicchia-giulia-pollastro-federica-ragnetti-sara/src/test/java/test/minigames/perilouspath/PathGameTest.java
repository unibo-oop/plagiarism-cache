package test.minigames.perilouspath;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import model.DifficultyLevel;
import model.minigames.perilouspath.PerilousPathDifficulty;
import model.minigames.perilouspath.PerilousPathDifficultyBuilderImpl;
import model.minigames.perilouspath.PathGameImpl;
import utility.Pair;

/**
 * Class to test if {@link PathGame} works correctly.
 */
public class PathGameTest {

    private final PerilousPathDifficulty difficulty;
    private LinkedList<Pair<Integer, Integer>> path; //NOPMD

    public PathGameTest() {
        this.difficulty = new PerilousPathDifficultyBuilderImpl().setDifficultyLevel(DifficultyLevel.EASY).build();
    }

    /**
     * Test the length game's path.
     */
    @Test
    public void pathGameLengthTest() {
        this.path = PathGameImpl.createRandomDirectionPath(difficulty.getSize(), difficulty.getNumMines()).getPathList();
        assertTrue(this.path.size() <= (difficulty.getSize() * difficulty.getSize()) - difficulty.getNumMines());
    }

    /**
     * Test if the first element of the path is the start.
     */
    @Test
    public void pathGameDefaultStartTest() {
        this.path = PathGameImpl.createRandomDirectionPathDefaultStartFinish(difficulty.getSize(), difficulty.getNumMines()).getPathList();
        assertEquals(new Pair<>(0, 0), this.path.getFirst());
    }

    /**
     * Test if the last element of the path is the finish.
     */
    @Test
    public void pathGameDefaultFinishTest() {
        this.path = PathGameImpl.createRandomDirectionPathDefaultStartFinish(difficulty.getSize(), difficulty.getNumMines()).getPathList();
        assertEquals(new Pair<>(4, 4), this.path.getLast());
    }

    /**
     * Test if the first element of the path is the start and the last is the finish.
     */
    @Test
    public void pathGameDefaultStartFinishTest() {
        this.path = PathGameImpl.createRandomDirectionPathDefaultStartFinish(difficulty.getSize(), difficulty.getNumMines()).getPathList();
        assertEquals(new Pair<>(0, 0), this.path.getFirst());
        assertEquals(new Pair<>(4, 4), this.path.getLast());
    }

    /**
     * Test if throws IllegalStateException if the size and the number of mines are equals to zero.
     */
    @Test
    public void pathGameZeroTest() {
        assertThrows(IllegalStateException.class, () -> PathGameImpl.createRandomDirectionPath(0, 0));
    }
}
