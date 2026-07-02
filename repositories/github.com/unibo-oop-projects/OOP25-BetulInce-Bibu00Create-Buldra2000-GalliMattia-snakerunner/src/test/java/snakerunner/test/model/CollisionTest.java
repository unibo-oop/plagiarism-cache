package snakerunner.test.model;

import snakerunner.model.impl.GameModelImpl;
import snakerunner.commons.Point2D;
import snakerunner.model.Collectible;
import snakerunner.model.LevelData;
import snakerunner.model.VictoryCondition;
import snakerunner.model.Direction;
import snakerunner.model.impl.Key;
import snakerunner.model.Door;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/*
Verifies collisions between:
*Snake and an obstacle(walls)
*Snake and doors (Closed door case and open door case)
*Snake itself
*Snake and door when the key opens the door
*/

class CollisionTest {
    private static final int FOOD_X = 30;
    private static final int FOOD_Y = 20;
    private static final int TEST_Y = 11;
    private GameModelImpl gameModel;
    private TestLevelData levelData;
    /*
    *This is a setup that "cleans" the game before every test
    */

    @BeforeEach
    void setUp() {
        gameModel = new GameModelImpl(); 
        levelData = new TestLevelData(); /* New Level  */
        levelData.addFood(FOOD_X, FOOD_Y);
    }

    @Test
    /*Walls
    *Checks collisions with walls (obstacles)
    */
    void wallCollision() {

        /*We add an obstacle to 3,10. Since the snake is moving towards right it should hit */
        levelData.addObstacle(3, 10);
        gameModel.loadLevel(levelData); /* Empty level */

    assertEquals(3, gameModel.getLives());
    final Point2D<Integer, Integer> startPos = gameModel.getSnake().getHead();

    /* First collision */
    gameModel.update();
    assertEquals(2, gameModel.getLives(), "Lives should be 2 after 1st collision");
    assertEquals(startPos, gameModel.getSnake().getHead(), "Snake should reset to start");
    /* Second collision */
    gameModel.update();
    assertEquals(1, gameModel.getLives(), "Lives should be 1 after 2nd collision");
    assertEquals(startPos, gameModel.getSnake().getHead(), "Snake should reset to start");
    /* Third collision */
    gameModel.update();
    assertEquals(0, gameModel.getLives(), "Lives should be 0 after 3rd collision");
    assertTrue(gameModel.isGameOver(), " Game should be over");
    }

    @Test
    /*Snake itself */
    void selfCollision() {
        gameModel.loadLevel(levelData); /*Empty level */

        assertEquals(new Point2D<>(2, 10), gameModel.getSnake().getHead());

        /*Snake moves into a circle to collide with itself*/
        gameModel.getSnake().setDirection(Direction.RIGHT);
        gameModel.update();
        assertEquals(new Point2D<>(3, 10), gameModel.getSnake().getHead());

        gameModel.getSnake().setDirection(Direction.DOWN);
        gameModel.update();
        assertEquals(new Point2D<>(3, TEST_Y), gameModel.getSnake().getHead());

        gameModel.getSnake().setDirection(Direction.LEFT);
        gameModel.update();
        assertEquals(new Point2D<>(2, TEST_Y), gameModel.getSnake().getHead());

        gameModel.getSnake().setDirection(Direction.UP);
        gameModel.update();
        assertEquals(new Point2D<>(2, 10), gameModel.getSnake().getHead());

        assertEquals(2, gameModel.getLives(), "Lives should decrease after self-collision. Lives: " + gameModel.getLives());
        assertEquals(new Point2D<>(2, 10), gameModel.getSnake().getHead());
    }

    @Test
    /*Doors */
    void doorCollision() {
        /* Case door closed */
        levelData.addDoor(3, 10);
        gameModel.loadLevel(levelData); /*Empty level */

        assertFalse(levelData.getDoors().get(0).isOpen());
        gameModel.update();

        assertEquals(2, gameModel.getLives(), "Snake should lose life when it hits a closed door ");
        assertFalse(gameModel.isGameOver());

        /* Case door open */
        setUp(); 
        levelData.addDoor(3, 10);
        gameModel.loadLevel(levelData); /*Empty level */
        /* Opening a door */
        gameModel.openDoor();
        assertTrue(levelData.getDoors().get(0).isOpen());

        gameModel.update();
        assertEquals(3, gameModel.getLives(), "Snake should not lose life");
    }

      @Test
        /*Key that opens a door */
        void keyOpensDoor() {
            levelData.addKey(3, 10);
            levelData.addDoor(4, 10);
            gameModel.loadLevel(levelData); /*Empty level */
            /*Initally the door is closed */
            assertFalse(levelData.getDoors().get(0).isOpen());
            gameModel.update(); /*We update and the snake collects the key */

            assertTrue(gameModel.getCollectibles().size() == 1, "Key should be collected");
            assertTrue(levelData.getDoors().get(0).isOpen(), "Door should be open once the key is collected");
            gameModel.update(); 

            assertEquals(3, gameModel.getLives(), "Snake should pass through the door");
            assertFalse(gameModel.isGameOver());

        }
    /* Mocking for testing */

    static class TestLevelData implements LevelData {
        private final Set<Point2D<Integer, Integer>> obstacles = new HashSet<>();
        private final List<Collectible> collectibles = new ArrayList<>();
        private final List<Door> doors = new ArrayList<>();

    @Override
    public Set<Point2D<Integer, Integer>> getObstacles() {
    return obstacles;
    }

    @Override
    public List<Door> getDoors() {
    return doors;
    }

    @Override
    public List<Collectible> getCollectibles() {
        return collectibles;
    }

    @Override
    public VictoryCondition getVictoryCondition() {
        return VictoryCondition.COLLECT_ALL_FOOD;
    }

    public void addDoor(final int x, final int y) {
        doors.add(new Door(x, y));
    }

    public void addObstacle(final int x, final int y) {
        obstacles.add(new Point2D<>(x, y));
    }

    public void addKey(final int x, final int y) {
        collectibles.add(new Key(new Point2D<>(x, y)));
    }

    public void addFood(final int x, final int y) {
        collectibles.add(new snakerunner.model.impl.FoodImpl(new Point2D<>(x, y)));
    }
    }
}
