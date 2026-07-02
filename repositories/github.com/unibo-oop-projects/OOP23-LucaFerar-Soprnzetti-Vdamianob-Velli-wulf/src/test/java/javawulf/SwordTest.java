package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.BoundingBox;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.map.Map;
import javawulf.model.map.factory.MapFactoryImpl;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.Direction;
import javawulf.model.GameObject;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerImpl;
import javawulf.model.player.Sword;
import javawulf.model.player.Sword.SwordType;

/**
 * SwordTest's purpose is to check whether the implementation
 * of Sword works as it should, considering also the movement
 * of the Player character.
 */
final class SwordTest {

    private static final int HEALTH = 3;
    private static final int STARTING_X = 12;
    private static final int STARTING_Y = 12;
    private static final int STARTING_POINTS = 0;
    private Sword sword;
    private Player player;
    private Coordinate test;
    private static final Direction START_DIRECTION = Direction.DOWN;
    private static final int DELTA = GameObject.OBJECT_SIZE;
    private BoundingBox startBox;
    private Coordinate playerPosition;
    private Map map;

    @BeforeEach
    void createPlayer() {
        this.test = new CoordinateImpl(STARTING_X + (int) (START_DIRECTION.getX() * GameObject.OBJECT_SIZE),
            STARTING_Y + (int) (START_DIRECTION.getY() * GameObject.OBJECT_SIZE));
        this.player = new PlayerImpl(STARTING_X, STARTING_Y, HEALTH, STARTING_POINTS);
        this.sword = this.player.getSword();
        this.startBox = new BoundingBoxImpl(STARTING_X + (int) (START_DIRECTION.getX() * GameObject.OBJECT_SIZE),
            STARTING_Y + (int) (START_DIRECTION.getY() * GameObject.OBJECT_SIZE), GameObject.OBJECT_SIZE,
            GameObject.OBJECT_SIZE, CollisionType.INACTIVE);
        this.map = new MapFactoryImpl().getTestMap(player);
    }

    @Test
    void testStartingSwordStatistics() {
        assertEquals(test.getPosition(), this.sword.getPosition().getPosition());
        assertEquals(startBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(1, this.sword.getSwordStrength());
        assertEquals(SwordType.NORMAL, this.sword.getSwordType());
        assertNotEquals(CollisionType.SWORD, this.sword.getBounds().getCollisionType());
    }

    // CPD-OFF
    /*  The sword movement is tested in 3 different ways. These test require that
    *   the sword is checked by the end of the movement, so there is some
    *   repetition
    */
    @Test
    void testSwordMovement() {
        final Direction movementDirection = Direction.UP;
        this.player.move(movementDirection, map);
        playerPosition = this.player.getPosition();
        final BoundingBox expectBox = new BoundingBoxImpl(playerPosition.getX() + (int) (movementDirection.getX() * DELTA),
            playerPosition.getY() + (int) (movementDirection.getY() * DELTA),  GameObject.OBJECT_SIZE,
            GameObject.OBJECT_SIZE, CollisionType.INACTIVE);

        assertFalse(this.sword.getBounds().isCollidingWith(this.player.getBounds().getCollisionArea()));
        assertNotEquals(this.player.getPosition().getPosition(), this.sword.getPosition().getPosition());
        assertNotEquals(test.getPosition(), this.sword.getPosition().getPosition());
        assertNotEquals(startBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(expectBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
    }

    @Test
    void testSwordDiagonalMovement() {
        final Direction movementDirection = Direction.DOWN_LEFT;
        this.player.move(movementDirection, map);
        playerPosition = this.player.getPosition();
        final Coordinate expectCoordinate = new CoordinateImpl(playerPosition.getX() + (int) (Direction.DOWN.getX() * DELTA),
            playerPosition.getY() + (int) (Direction.DOWN.getY() * DELTA));
        final BoundingBox expectBox = new BoundingBoxImpl(playerPosition.getX() + (int) (Direction.DOWN.getX() * DELTA),
            playerPosition.getY() + (int) (Direction.DOWN.getY() * DELTA),  GameObject.OBJECT_SIZE,
            GameObject.OBJECT_SIZE, CollisionType.INACTIVE);

        assertFalse(this.sword.getBounds().isCollidingWith(this.player.getBounds().getCollisionArea()));
        assertNotEquals(test.getPosition(), this.sword.getPosition().getPosition());
        assertNotEquals(startBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(expectBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(expectCoordinate.getPosition(), this.sword.getPosition().getPosition());
    }

    @Test
    void testSwordOppositeDiagonalMovement() {
        final Direction movementDirection = Direction.UP_RIGHT;
        this.player.move(movementDirection, map);
        playerPosition = this.player.getPosition();
        final Coordinate expectCoordinate = new CoordinateImpl(playerPosition.getX() + (int) (Direction.RIGHT.getX() * DELTA),
            playerPosition.getY() + (int) (Direction.RIGHT.getY() * DELTA));
        final BoundingBox expectBox = new BoundingBoxImpl(playerPosition.getX() + (int) (Direction.RIGHT.getX() * DELTA),
            playerPosition.getY() + (int) (Direction.RIGHT.getY() * DELTA),  GameObject.OBJECT_SIZE,
            GameObject.OBJECT_SIZE, CollisionType.INACTIVE);

        assertFalse(this.sword.getBounds().isCollidingWith(this.player.getBounds().getCollisionArea()));
        assertNotEquals(test.getPosition(), this.sword.getPosition().getPosition());
        assertNotEquals(startBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(expectBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(expectCoordinate.getPosition(), this.sword.getPosition().getPosition());
    }
    // CPD-ON

    @Test
    void testActivation() {
        this.sword.activate();
        assertEquals(CollisionType.SWORD, this.sword.getBounds().getCollisionType());

        this.sword.deactivate();
        assertNotEquals(CollisionType.SWORD, this.sword.getBounds().getCollisionType());
    }

    @Test
    void testTypeChange() {
        this.sword.changeSwordType();
        assertEquals(SwordType.GREATSWORD, this.sword.getSwordType());

        this.sword.changeSwordType();
        assertNotEquals(SwordType.GREATSWORD, this.sword.getSwordType());
    }

    @Test
    void testGreatSwordCollisionArea() {
        this.sword.changeSwordType();
        final Direction movementDirection = Direction.RIGHT;
        this.player.move(movementDirection, map);
        playerPosition = this.player.getPosition();
        int constantHeight = 1;
        int constantWidth = 1;
        if (Math.abs((int) movementDirection.getX()) > 0) {
            constantHeight = 3;
        } else {
            constantWidth = 3;
        }
        final BoundingBox expectBox = new BoundingBoxImpl(playerPosition.getX() + (int) (movementDirection.getX() * DELTA),
            playerPosition.getY() + (int) (movementDirection.getY() * DELTA),  GameObject.OBJECT_SIZE * constantWidth,
            GameObject.OBJECT_SIZE * constantHeight, CollisionType.INACTIVE);

        assertFalse(this.sword.getBounds().isCollidingWith(this.player.getBounds().getCollisionArea()));
        assertNotEquals(this.player.getPosition().getPosition(), this.sword.getPosition().getPosition());
        assertNotEquals(test.getPosition(), this.sword.getPosition().getPosition());
        assertNotEquals(startBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(expectBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
    }

}
