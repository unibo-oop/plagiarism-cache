package it.unibo.scat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.common.GameResult;
import it.unibo.scat.model.game.CollisionReport;
import it.unibo.scat.model.game.DifficultyManager;
import it.unibo.scat.model.game.GameLogic;
import it.unibo.scat.model.game.GameWorld;
import it.unibo.scat.model.game.entity.EntityFactoryImpl;
import it.unibo.scat.model.game.entity.Invader;
import it.unibo.scat.model.game.entity.Player;
import it.unibo.scat.model.game.entity.Shot;

class AppTest {

    private static final int X = 5;
    private static final int Y = 5;
    private static final int WIDTH = 2;
    private static final int HEIGHT = 2;
    private static final int HEALTH = 1;

    private GameWorld world;
    private GameLogic logic;
    private EntityFactoryImpl factory;

    @BeforeEach
    void setup() {
        factory = new EntityFactoryImpl();
        world = new GameWorld(factory);
        logic = new GameLogic(world, factory);
    }

    @Test
    void testAddEntityUpdatesLists() {
        final Player player = (Player) factory.createEntity(EntityType.PLAYER, X, Y);

        world.addEntity(player);

        assertEquals(player, world.getPlayer());
        assertTrue(world.getEntities().contains(player));
    }

    @Test
    void testShotMovementUp() {
        final Shot shot = (Shot) factory.createEntity(EntityType.PLAYER_SHOT, X, Y);

        final int initialY = shot.getPosition().getY();

        shot.move();

        assertEquals(initialY - 1, shot.getPosition().getY());
    }

    @Test
    void testCollisionKillsInvader() {
        final Invader invader = new Invader(EntityType.INVADER_1, X, Y, WIDTH, HEIGHT, HEALTH);

        final Shot shot = new Shot(EntityType.PLAYER_SHOT, X, Y, WIDTH, HEIGHT, HEALTH, Direction.UP);

        world.addEntity(invader);
        world.addEntity(shot);

        final CollisionReport report = logic.checkCollisions();
        logic.handleCollisionReport(report);

        assertFalse(invader.isAlive());
    }

    @Test
    void testGameEndsWhenPlayerDead() {
        final Player player = new Player(EntityType.PLAYER, X, Y, WIDTH, HEIGHT, HEALTH);

        world.addEntity(player);

        player.onHit();

        assertEquals(GameResult.INVADERS_WON, logic.checkGameEnd());
    }

    @Test
    void testDifficultyIncreasesLevel() {
        final DifficultyManager manager = new DifficultyManager();
        final int levelBefore = manager.getLevel();

        manager.incrementLevel();

        assertEquals(levelBefore + 1, manager.getLevel());
    }

    @Test
    void testRemoveDeadShots() {
        final Shot shot = new Shot(EntityType.PLAYER_SHOT, X, Y, WIDTH, HEIGHT, HEALTH, Direction.UP);

        world.addEntity(shot);

        shot.onHit(); // health -> 0

        logic.removeDeadShots();

        assertTrue(world.getShots().isEmpty());
    }

    @Test
    void testInvaderDirectionChange() {
        final Invader invader = new Invader(EntityType.INVADER_1, X, Y, WIDTH, HEIGHT, HEALTH);

        world.addEntity(invader);

        invader.setCurrDirection(Direction.RIGHT);
        world.changeInvadersDirection();

        assertEquals(Direction.DOWN, invader.getCurrDirection());
    }

    @Test
    void testPlayerWinsWhenAllInvadersAreDead() {
        final Player player = new Player(EntityType.PLAYER, X, Y, WIDTH, HEIGHT, HEALTH);

        final Invader invader = new Invader(EntityType.INVADER_1, X, Y, WIDTH, HEIGHT, HEALTH);

        world.addEntity(player);
        world.addEntity(invader);

        invader.onHit(); // dies

        assertEquals(GameResult.PLAYER_WON, logic.checkGameEnd());
    }

}
