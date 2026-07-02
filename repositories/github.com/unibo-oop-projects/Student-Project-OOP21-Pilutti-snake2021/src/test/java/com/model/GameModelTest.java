package test.java.com.model;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import main.java.com.model.GameModel;
import main.java.com.model.Model;
import main.java.com.utility.Direction;
import main.java.com.utility.Pos;
import main.java.com.utility.Position;

public class GameModelTest {

    private static final int BODY_LENGTH = 5;
    private static final int X_MAP_SIZE = 21;
    private static final int Y_MAP_SIZE = 21;
    private static final Position START_POS = new Pos(X_MAP_SIZE / 2, (Y_MAP_SIZE / 3) * 2);
    private static final int POINTS = 50;

    @Test
    @DisplayName("Tests that the GameModel constructor works properly.")
    public void testGameModelConstructor() {
        final Model model = new GameModel();
        assertEquals(Direction.UP, model.getSnake().getDirection());
        assertEquals(START_POS, model.getSnake().getPosition());
        // Verify that the getFreeCells method returns a set that does not include the snake's positions.
        for (int i = 0; i < BODY_LENGTH; i++) {
            assertFalse(model.getGameMap().getFreeCells(model.getSnake()).contains(new Pos(START_POS.getX(), START_POS.getY() + 1)));
        }
        // Verify that the game map contains all possible cells
        for (int i = 0; i <= X_MAP_SIZE; i++) {
            for (int j = 0; j <= Y_MAP_SIZE; j++) {
                assertTrue(model.getGameMap().getAllCells().contains(new Pos(i, j)));
            }
        }
        // Apple
        assertTrue(model.getGameMap().getFreeCells(model.getSnake()).contains(model.getApple().getPosition()));
        assertEquals(POINTS, model.getApple().getPointsValue());
        assertEquals(0, model.getApple().getTimesEaten());
        // Model
        assertEquals(0, model.getScore());
    }

    @Test
    @DisplayName("Test for the eatApple() method.")
    public void testEatApple() {
        final Model model = new GameModel();
        final Position a = model.getApple().getPosition();
        model.getSnake().setPosition(model.getApple().getPosition());
        model.eatApple();
        assertEquals(POINTS, model.getScore());
        assertNotEquals(a, model.getApple().getPosition());
        assertEquals(1, model.getApple().getTimesEaten());
    }

    @Test
    @DisplayName("Test resetGame() method")
    public void testReset() {
        final Model model = new GameModel();
        model.getSnake().move();
        model.getSnake().move();
        model.getSnake().setDirection(Direction.RIGHT);
        model.getSnake().move();
        model.getSnake().move();
        model.getApple().setPosition(model.getSnake().getPosition());
        model.eatApple();
        final Position a = model.getApple().getPosition();
        model.resetGame();
        assertEquals(START_POS, model.getSnake().getPosition());
        assertNotEquals(a, model.getApple().getPosition());
        assertEquals(0, model.getApple().getTimesEaten());
        assertEquals(0, model.getScore());
    }
}
