package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.BoundingBox;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.Direction;
import javawulf.model.AbstractEntity;
import javawulf.model.item.AmuletPiece;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerHealthImpl;
import javawulf.model.player.PlayerImpl;
import javawulf.model.item.ItemFactoryImpl;
import javawulf.model.map.Map;
import javawulf.model.map.factory.MapFactoryImpl;

/**
 * The class PlayerTest it used to check if the implementation
 * of Player works according to the game rules.
 */
final class PlayerTest {

    private static final int HEALTH = 3;
    private static final int STARTING_X = 12;
    private static final int STARTING_Y = 12;
    private static final int STARTING_POINTS = 0;
    private Player player;
    private Coordinate test;

    @BeforeEach
    void createPlayer() {
        this.player = new PlayerImpl(STARTING_X, STARTING_Y, HEALTH, STARTING_POINTS);
        this.test = new CoordinateImpl(STARTING_X, STARTING_Y);
    }

    @Test
    void testStartingPlayerStatistics() {
        assertEquals(test.getPosition(), player.getPosition().getPosition());
        assertEquals(new BoundingBoxImpl(STARTING_X, STARTING_Y, AbstractEntity.OBJECT_SIZE, AbstractEntity.OBJECT_SIZE,
                CollisionType.PLAYER).getCollisionArea(), player.getBounds().getCollisionArea());
        assertEquals(0, player.getNumberOfPieces());
    }

    @Test
    void testPlayerMovement() {
        final Direction movementDirection = Direction.DOWN_LEFT;
        final Map map = new MapFactoryImpl().getTestMap(player);
        final int delta = AbstractEntity.MOVEMENT_DELTA;
        final Coordinate expectedCoordinate = new CoordinateImpl(player.getPosition().getX()
            + (int) (movementDirection.getX() * delta), player.getPosition().getY()
            + (int) (movementDirection.getY() * delta));
        final BoundingBox expectedBoundingBox = new BoundingBoxImpl(expectedCoordinate.getX(),
            expectedCoordinate.getY(), AbstractEntity.OBJECT_SIZE, AbstractEntity.OBJECT_SIZE, CollisionType.PLAYER);
        player.move(movementDirection, map);
        assertNotEquals(test.getPosition(), player.getPosition().getPosition());
        assertEquals(expectedCoordinate.getPosition(), player.getPosition().getPosition());
        assertEquals(expectedBoundingBox.getCollisionArea(), player.getBounds().getCollisionArea());
    }

    @Test
    void testAttack() {
        final CollisionType original = player.getSword().getBounds().getCollisionType();
        final CollisionType expected = CollisionType.SWORD;
        assertEquals(original, player.getSword().getBounds().getCollisionType());
        player.attack();
        assertNotEquals(original, player.getSword().getBounds().getCollisionType());
        assertEquals(expected, player.getSword().getBounds().getCollisionType());
    }

    @Test
    void testGettingHit() {
        final BoundingBox item = new BoundingBoxImpl(STARTING_X, STARTING_Y, AbstractEntity.OBJECT_SIZE,
            AbstractEntity.OBJECT_SIZE, CollisionType.COLLECTABLE);
        assertFalse(player.isHit(item));
        assertNotEquals(CollisionType.STUNNED, player.getBounds().getCollisionType());

        BoundingBox enemy = new BoundingBoxImpl(STARTING_X + AbstractEntity.OBJECT_SIZE, STARTING_Y + AbstractEntity.OBJECT_SIZE,
            AbstractEntity.OBJECT_SIZE, AbstractEntity.OBJECT_SIZE, CollisionType.ENEMY);
        assertFalse(player.isHit(enemy));
        enemy = new BoundingBoxImpl(STARTING_X, STARTING_Y, AbstractEntity.OBJECT_SIZE,
            AbstractEntity.OBJECT_SIZE, CollisionType.ENEMY);
        assertTrue(player.isHit(enemy));
        assertEquals(CollisionType.STUNNED, player.getBounds().getCollisionType());
        assertNotEquals(new PlayerHealthImpl(HEALTH).getHealth(), player.getPlayerHealth().getHealth());
        assertEquals(new PlayerHealthImpl(HEALTH - 1).getHealth(), player.getPlayerHealth().getHealth());

        assertFalse(player.isHit(enemy));
        for (int i = 4; i > 0; i--) {
            this.player.reduceStun();
            assertEquals(CollisionType.STUNNED, player.getBounds().getCollisionType());
            assertFalse(player.isHit(enemy));
        }
        this.player.reduceStun();
        assertNotEquals(CollisionType.STUNNED, player.getBounds().getCollisionType());
        assertEquals(CollisionType.PLAYER, player.getBounds().getCollisionType());
        assertTrue(player.isHit(enemy));
    }

    @Test
    void testObtainFragment() {
        final int wrongResult = 5;
        final List<AmuletPiece> fragments = new ArrayList<>();
        final AmuletPiece fragment = new ItemFactoryImpl().createAmuletPiece(test);
        for (int i = 0; i < 4; i++) {
            fragments.add(fragment);
            player.collectAmuletPiece(fragments.get(i));
            assertEquals(i + 1, player.getNumberOfPieces());
        }
        assertThrows(IllegalStateException.class, () -> player.collectAmuletPiece(fragment));
        assertNotEquals(wrongResult, player.getNumberOfPieces());
    }
}
