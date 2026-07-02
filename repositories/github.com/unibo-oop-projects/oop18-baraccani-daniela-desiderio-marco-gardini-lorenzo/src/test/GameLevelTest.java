package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Model;
import model.ModelImpl;
import model.entities.Enemy;
import model.entities.Hero;
import model.entities.Wall;
import model.entitiesutil.Entity;
import model.entitiesutil.EntityDirection;
import model.entitiesutil.EntityMovable;
import model.entitiesutil.MovementValue;
import model.levels.GameLevel;
import model.levels.GameLevelImpl;

/**
 * Test class for {@link GameLevel}.
 */
public class GameLevelTest {

    private static final int HERO_X = 10;
    private static final int HERO_Y = 20;
    private static final int HERO_W = 5;
    private static final int HERO_H = 5;
    private static final int ENEMY_X = 5;
    private static final int ENEMY_Y = 8;
    private static final int ENEMY_W = 5;
    private static final int ENEMY_H = 5;
    private static final int WALL_X = 10;
    private static final int WALL_Y = 8;
    private static final int WALL_W = 5;
    private static final int WALL_H = 5;
    private static final int SIZE = 3;
    private static final int LEVEL_NUMBER = 1;
    private static final int MOVED_ENTITIES = 2;
    private static final String SUCCESS_ADD_MSG = "Entities added successfully";
    private static final String SUCCESS_CLEAR_MSG = "All entities removed successfully";
    private static final String SUCCESS_REMOVE_MSG = "There aren't 3 Entities";

    private final Hero hero;
    private final EntityMovable enemy;
    private final Entity wall;
    private Model model;
    private GameLevel gameLevel;

    /**
     * Initialize the {@link Model} and the {@link Entity}s.
     */
    public GameLevelTest() {
        this.model = new ModelImpl();
        this.hero = new Hero(this.model, HERO_X, HERO_Y, HERO_W, HERO_H, EntityDirection.DOWN_RIGHT);
        this.enemy = new Enemy(this.model, ENEMY_X, ENEMY_Y, ENEMY_W, ENEMY_H, EntityDirection.DOWN_RIGHT);
        this.wall = new Wall(this.model, WALL_X, WALL_Y, WALL_W, WALL_H);
    }

    /**
     * Build a new {@link GameLevel} and set it in the {@link Model}.
     */
    @Before
    public void initializeGameLevel() {
        this.gameLevel = new GameLevelImpl(this.model, LEVEL_NUMBER);
        this.model.setLevel(this.gameLevel);
    }

    /**
     * Test for a correct addition of {@link Entity}s in the {@link GameLevel},
     * {@link Entity}s already created.
     */
    @Test
    public void addEntityTest() {
        this.gameLevel.addEntity(this.hero);
        this.gameLevel.addEntity(this.enemy);
        this.gameLevel.addEntity(this.wall);
        assertEquals(SUCCESS_ADD_MSG, this.gameLevel.getEntities().size(), SIZE);
    }

    /**
     * Test for a correct addition of {@link Entity}s with specific coordinates in
     * the {@link GameLevel}.
     */
    @Test
    public void addEntityWithCoordsTest() {
        this.gameLevel.addEntity(new Hero(this.model, HERO_X, HERO_Y, HERO_W, HERO_H, EntityDirection.LEFT));
        this.gameLevel.addEntity(new Enemy(this.model, ENEMY_X, ENEMY_Y, ENEMY_W, ENEMY_H, EntityDirection.LEFT));
        this.gameLevel.addEntity(new Wall(this.model, WALL_X, WALL_Y, WALL_W, WALL_H));
        assertEquals(SUCCESS_ADD_MSG, this.gameLevel.getEntities().size(), SIZE);
    }

    /**
     * Test for the correct removal of {@link Entity}s in the {@link GameLevel}.
     */
    @Test
    public void removeEntityTest() {
        this.gameLevel.addEntity(this.hero);
        this.gameLevel.addEntity(this.enemy);
        this.gameLevel.addEntity(this.wall);
        assertEquals(SUCCESS_ADD_MSG, this.gameLevel.getEntities().size(), SIZE);
        this.gameLevel.removeEntity(this.hero);
        assertNotEquals(SUCCESS_REMOVE_MSG, this.gameLevel.getEntities().size(), SIZE);
        assertEquals(SUCCESS_ADD_MSG, this.gameLevel.getEntities().size(), SIZE - 1);
    }

    /**
     * Test for the correct removal of all {@link Entity}s in the {@link GameLevel}.
     */
    @Test
    public void clearAllTest() {
        this.gameLevel.addEntity(this.hero);
        this.gameLevel.addEntity(this.enemy);
        this.gameLevel.addEntity(this.wall);
        assertEquals(SUCCESS_ADD_MSG, this.gameLevel.getEntities().size(), SIZE);
        this.gameLevel.clearAll();
        assertTrue(SUCCESS_CLEAR_MSG, this.gameLevel.getEntities().isEmpty());
    }

    /**
     * Test for the correct addition of the moved {@link Entity}s.
     */
    @Test
    public void addToRecheckEntityTest() {
        this.gameLevel.addEntity(this.hero);
        this.gameLevel.addEntity(this.enemy);
        this.gameLevel.addEntity(this.wall);
        assertEquals(SUCCESS_ADD_MSG, this.gameLevel.getEntities().size(), SIZE);
        this.hero.move(MovementValue.CHARACTER_MOVE_RIGHT);
        this.enemy.doStep();
        assertEquals(SUCCESS_ADD_MSG, this.gameLevel.getToRecheckEntities().size(), MOVED_ENTITIES);
    }

}
