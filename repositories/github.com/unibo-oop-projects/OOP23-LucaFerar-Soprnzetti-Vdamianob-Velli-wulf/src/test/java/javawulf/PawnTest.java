package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javawulf.model.AbstractEntity;
import javawulf.model.BoundingBox;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.Direction;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.enemy.EnemyFactory;
import javawulf.model.enemy.EnemyFactoryImpl;
import javawulf.model.enemy.Pawn;
import javawulf.model.map.Map;
import javawulf.model.map.factory.MapFactoryImpl;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerImpl;

/**
 * Test class for the Pawn class.
 */
final class PawnTest {

    private static final int STARTING_X = 24;
    private static final int STARTING_Y = 24;
    private static final int STARTING_HEALTH = 3;
    private static final int PAWN_NEAR_WALL_Y_MULTIPLIER = 6;
    private static final int WALL_POSITION_Y_MULTIPLIER = 7;

    private final EnemyFactory factory = new EnemyFactoryImpl();
    private Player player = new PlayerImpl(STARTING_X * 3, STARTING_Y * 3, STARTING_HEALTH, 0);
    private final Coordinate position = new CoordinateImpl(STARTING_X, STARTING_Y);
    private final Map map = new MapFactoryImpl().getTestMap(player);
    private final Pawn pawn = factory.createPawn(position);

    @Test
    void testMove() throws InterruptedException {
        // Test coordinates and BoundingBox
        final Coordinate testPos = pawn.getPosition();
        final var testBounds = pawn.getBounds().getCollisionArea();
        final int delta = pawn.getSpeed() * AbstractEntity.MOVEMENT_DELTA;
        // Test coordinates and boundingbox for expected position of the pawn after
        // moving
        final Coordinate expectedCoordinate = new CoordinateImpl(
                pawn.getPosition().getX() + (int) (pawn.getDirection().getX() * delta),
                pawn.getPosition().getY() + (int) (pawn.getDirection().getY() * delta));
        final BoundingBox expectedBounds = new BoundingBoxImpl(
                expectedCoordinate.getX(),
                expectedCoordinate.getY(),
                AbstractEntity.OBJECT_SIZE,
                AbstractEntity.OBJECT_SIZE,
                CollisionType.ENEMY);
        // Move the pawn
        pawn.move(player, map);
        // Test if the pawn moved
        assertNotEquals(testPos, pawn.getPosition());
        assertNotEquals(testBounds, pawn.getBounds().getCollisionArea());
        // Test if the pawn has moved where he should have to
        assertEquals(expectedCoordinate.getPosition(), pawn.getPosition().getPosition());
        assertEquals(expectedBounds.getCollisionArea(), pawn.getBounds().getCollisionArea());
        // Position the player in the pawn's path
        player.setPosition(new CoordinateImpl(pawn.getPosition().getX(),
                pawn.getPosition().getY()));
        player.getBounds().setCollisionArea(player.getPosition().getX(), player.getPosition().getY(),
                AbstractEntity.OBJECT_SIZE,
                AbstractEntity.OBJECT_SIZE);
        // Test if the player is hit
        assertTrue(player.isHit(pawn.getBounds()));

        // Move away the player
        player.setPosition(new CoordinateImpl(STARTING_X * 10, STARTING_Y * 10));
        player.getBounds().setCollisionArea(player.getPosition().getX(), player.getPosition().getY(),
                AbstractEntity.OBJECT_SIZE,
                AbstractEntity.OBJECT_SIZE);

        // Put the pawn near a wall
        pawn.setPosition(testPos);
        pawn.setPosition(new CoordinateImpl(STARTING_X, STARTING_Y * PAWN_NEAR_WALL_Y_MULTIPLIER));
        pawn.getBounds().setCollisionArea(pawn.getPosition().getX(), pawn.getPosition().getY(),
                AbstractEntity.OBJECT_SIZE,
                AbstractEntity.OBJECT_SIZE);
        pawn.setDirection(Direction.DOWN);

        // Check if the pawn changes direction after colliding with a wall
        pawn.move(player, map);
        assertNotEquals(Direction.DOWN, pawn.getDirection());
        final Coordinate wallPosition = new CoordinateImpl(STARTING_X, STARTING_Y * WALL_POSITION_Y_MULTIPLIER);
        assertNotEquals(wallPosition, pawn.getPosition());
    }

    @Test
    void testTakeHit() {
        /*
         * Put the player and the pawn near each other so that the player can hit the
         * pawn
         */
        this.player = new PlayerImpl(STARTING_X * 3, STARTING_Y, STARTING_HEALTH, 0);
        player.move(Direction.LEFT, map);
        // Hit the pawn
        player.attack();

        // Test if the pawn is hit
        assertTrue(pawn.isHit(player.getSword().getBounds()));

        // Take the hit
        pawn.takeHit(player);

        // Test if the pawn is dead
        assertFalse(pawn.isAlive());
        assertEquals(CollisionType.INACTIVE, pawn.getBounds().getCollisionType());

        // Check if the player got the points
        assertNotEquals(0, player.getScore().getPoints());

    }

    @Test
    void testTick() {
        // Memorizing the initial time to wait
        final int initialTime = pawn.getTimeToWait();
        // Tick the pawn
        pawn.tick();
        // Test if the tick count is 1 or 0 depending on the time to wait
        if (initialTime == 1) {
            assertEquals(0, pawn.getTickCount());
        } else {
            assertEquals(1, pawn.getTickCount());
        }
        // Memorizing the initial time to wait and initial direction
        //initialTime = pawn.getTimeToWait();
        final Direction initialDirection = pawn.getDirection();
        int counter = 0;
        // Call the tick method until the pawn changes direction or the counter reaches
        // 100
        while (pawn.getDirection().equals(initialDirection) && counter < 100) {
            pawn.tick();
            counter++;
        }
        // Test if the pawn changed direction
        assertNotEquals(initialDirection, pawn.getDirection());
    }

}
