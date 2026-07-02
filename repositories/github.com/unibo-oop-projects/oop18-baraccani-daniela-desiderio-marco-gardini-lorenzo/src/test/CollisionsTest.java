package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Model;
import model.ModelImpl;
import model.entities.Bubble;
import model.entities.Enemy;
import model.entities.Hero;
import model.entities.PowerUp;
import model.entities.Wall;
import model.entitiesutil.EnemyState;
import model.entitiesutil.Entity;
import model.entitiesutil.EntityDirection;
import model.entitiesutil.EntityMovable;
import model.entitiesutil.MovementValue;
import model.levels.GameLevel;
import model.levels.GameLevelImpl;

/**
 * Test class for {@link CollisionManager} and {@link Entity}s movements.
 */
public class CollisionsTest {

    private static final int HERO_X = 15;
    private static final int HERO_X2 = 1;
    private static final int HERO_Y = 8;
    private static final int HERO_W = 5;
    private static final int HERO_H = 5;
    private static final int ENEMY_X = 7;
    private static final int ENEMY_Y = 8;
    private static final int ENEMY_W = 5;
    private static final int ENEMY_H = 5;
    private static final int POWERUP_X = 24;
    private static final int POWERUP_Y = 8;
    private static final int POWERUP_W = 5;
    private static final int POWERUP_H = 5;
    private static final int WALL_X = 7;
    private static final int WALL_Y = 8;
    private static final int WALL_W = 5;
    private static final int WALL_H = 5;
    private static final int LEVEL_NUMBER = 1;
    private static final int ZERO = 0;
    private static final int TOTAL_LIVES = 3;
    private static final int BUBBLE_POINTS = 5;
    private static final int POWERUP_POINTS = 100;
    private static final int ENEMY_POINTS = 1000;
    private static final String SUCCESS_SUBTRACTION_LIVES_MSG = "Hero lost a life";
    private static final String SUCCESS_DEATH_MSG = "Hero dead";
    private static final String SUCCESS_POINTS_MSG = "Points added successfully";

    private final EntityMovable bubble;
    private final Hero hero;
    private final Enemy enemy;
    private final EntityMovable powerUp;
    private final Entity wall;
    private Model model;
    private GameLevel gameLevel;

    /**
     * Initialize the {@link Model} and the {@link Entity}s.
     */
    public CollisionsTest() {
        this.model = new ModelImpl();
        this.hero = new Hero(this.model, HERO_X, HERO_Y, HERO_W, HERO_H, EntityDirection.LEFT);
        this.enemy = new Enemy(this.model, ENEMY_X, ENEMY_Y, ENEMY_W, ENEMY_H, EntityDirection.RIGHT);
        this.powerUp = new PowerUp(this.model, POWERUP_X, POWERUP_Y, POWERUP_W, POWERUP_H, EntityDirection.DOWN_LEFT);
        this.bubble = new Bubble(this.model, HERO_X, HERO_Y, HERO_W, HERO_H, EntityDirection.LEFT);
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
     * Test for a correct collision between {@link Hero} and {@link Enemy}.
     */
    @Test
    public void collisionEnemyHeroTest() {
        this.gameLevel.addEntity(this.hero);
        this.gameLevel.addEntity(this.enemy);

        this.hero.move(MovementValue.CHARACTER_MOVE_LEFT);
        this.gameLevel.getCollisionManager().collision();
        this.hero.doStep();
        assertEquals(SUCCESS_SUBTRACTION_LIVES_MSG, this.model.getLives(), TOTAL_LIVES - 1);

        this.hero.move(MovementValue.CHARACTER_MOVE_RIGHT);
        this.hero.move(MovementValue.CHARACTER_MOVE_LEFT);
        this.gameLevel.getCollisionManager().collision();
        this.hero.doStep();

        this.hero.move(MovementValue.CHARACTER_MOVE_RIGHT);
        this.hero.move(MovementValue.CHARACTER_MOVE_LEFT);
        this.gameLevel.getCollisionManager().collision();
        this.hero.doStep();
        assertEquals(SUCCESS_DEATH_MSG, this.model.getLives(), ZERO);
    }

    /**
     * Test for a correct collision between {@link Hero} and {@link PowerUp}.
     */
    @Test
    public void collisionHeroPowerUpTest() {
        this.gameLevel.addEntity(this.hero);
        this.gameLevel.addEntity(this.powerUp);

        this.hero.move(MovementValue.CHARACTER_MOVE_RIGHT);
        this.gameLevel.getCollisionManager().collision();
        assertTrue(SUCCESS_POINTS_MSG, this.model.getScore() > ZERO);
        assertEquals(SUCCESS_POINTS_MSG, this.model.getScore(), POWERUP_POINTS);
    }

    /**
     * Test for a correct collision between {@link Bubble} and {@link Enemy}.
     */
    @Test
    public void collisionEnemyBubbleTest() {
        this.gameLevel.addEntity(this.enemy);
        this.gameLevel.addEntity(this.bubble);
        assertEquals(this.enemy.getState(), EnemyState.NORMAL);

        this.bubble.doStep();
        this.gameLevel.getCollisionManager().collision();
        assertEquals(this.enemy.getState(), EnemyState.BUBBLED);
    }

    /**
     * Test for a correct collision between a bubbled {@link Enemy} and
     * {@link Hero}.
     */
    @Test
    public void collisionBubbledEnemyHeroTest() {
        this.gameLevel.addEntity(this.enemy);
        this.gameLevel.addEntity(this.bubble);
        this.gameLevel.addEntity(new Hero(this.model, HERO_X2, HERO_Y, HERO_W, HERO_H, EntityDirection.LEFT));
        this.bubble.doStep();
        this.gameLevel.getCollisionManager().collision();
        assertEquals(this.enemy.getState(), EnemyState.BUBBLED);

        this.hero.move(MovementValue.CHARACTER_MOVE_LEFT);
        this.gameLevel.getCollisionManager().collision();
        this.hero.doStep();
        assertTrue(SUCCESS_POINTS_MSG, this.model.getScore() > ZERO);
        assertEquals(SUCCESS_POINTS_MSG, this.model.getScore(), ENEMY_POINTS);
    }

    /**
     * Test for a correct collision between {@link Bubble} and {@link Wall}.
     */
    @Test
    public void collisionBubbleWallTest() {
        this.gameLevel.addEntity(this.wall);
        this.gameLevel.addEntity(this.bubble);
        this.bubble.doStep();
        this.gameLevel.getCollisionManager().collision();
        assertTrue(SUCCESS_POINTS_MSG, this.model.getScore() > ZERO);
        assertEquals(SUCCESS_POINTS_MSG, this.model.getScore(), BUBBLE_POINTS);
    }

}
