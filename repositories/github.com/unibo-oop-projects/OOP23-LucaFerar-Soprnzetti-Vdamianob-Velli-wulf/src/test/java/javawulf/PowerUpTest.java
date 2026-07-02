package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.Direction;
import javawulf.model.map.Map;
import javawulf.model.map.factory.MapFactoryImpl;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerImpl;
import javawulf.model.player.Score;
import javawulf.model.player.Sword;
import javawulf.model.player.SwordImpl;
import javawulf.model.powerup.PowerUpAttack;
import javawulf.model.powerup.PowerUpDoublePoints;
import javawulf.model.powerup.PowerUpFactory;
import javawulf.model.powerup.PowerUpFactoryImpl;
import javawulf.model.powerup.PowerUpInvincibility;
import javawulf.model.powerup.PowerUpSpeed;


final class PowerUpTest {
    // SetUp
    private static final int X_COORDINATE_PLAYER = 10;
    private static final int Y_COORDINATE_PLAYER = 10;
    private static final int X_COORDINATE_POWERUP = 34;
    private static final int Y_COORDINATE_POWERUP = 10;
    private static final int HEALTH = 3;
    private static final int POINTS = 0;

    // Results
    private static final int X_COORDINATE_PLAYER_SPEED = 16;
    private static final int Y_COORDINATE_PLAYER_SPEED = 10;

    private Player player;
    private Coordinate coordinatesPowerUp;
    private Coordinate coordinatesPlayer;
    private Coordinate coordinatesPlayerSpeed;
    private PowerUpFactory powerUpFactory;
    private Map map;

    @BeforeEach
    void setUpPlayerAndCoordinates() {
        this.powerUpFactory = new PowerUpFactoryImpl();
        this.player = new PlayerImpl(X_COORDINATE_PLAYER, Y_COORDINATE_PLAYER, HEALTH, POINTS);
        this.coordinatesPlayer = new CoordinateImpl(X_COORDINATE_PLAYER, Y_COORDINATE_PLAYER);
        this.coordinatesPlayerSpeed = new CoordinateImpl(X_COORDINATE_PLAYER_SPEED, Y_COORDINATE_PLAYER_SPEED);
        this.coordinatesPowerUp = new CoordinateImpl(X_COORDINATE_POWERUP, Y_COORDINATE_POWERUP);
        this.map = new MapFactoryImpl().getTestMap(player);
    }

    @Test
    void testCollectionPowerUp() {
        // Create a powerup near player 
        final PowerUpAttack powerUpAttack = powerUpFactory.createPowerUpAttack(coordinatesPowerUp);
        // Test if the power up doesnt get pick up if its far away
        assertFalse(player.getBounds().isCollidingWith(powerUpAttack.getBounds().getCollisionArea()));
        final Direction movementDirection = Direction.RIGHT;
        player.move(movementDirection, map);
        // Test if now power up is colliding with player
        assertTrue(player.getBounds().isCollidingWith(powerUpAttack.getBounds().getCollisionArea()));
        powerUpAttack.collect(player);
        // Test wich powerup is active in now
        assertEquals(powerUpAttack, player.getPowerUpHandler().getPowerUpActive().get());
    }

    @Test
    void testCollectionAndSubstitutionPowerUp() {
        final PowerUpAttack powerUpAttack = powerUpFactory.createPowerUpAttack(coordinatesPlayer);
        powerUpAttack.collect(player);
        // Test if powerUp active is attack
        assertEquals(powerUpAttack.getType(), player.getPowerUpHandler().getPowerUpActive().get().getType());
        assertEquals(SwordImpl.STRONG, player.getSword().getSwordStrength());
        assertEquals(powerUpAttack.getDuration(), player.getPowerUpHandler().getPowerUpActive().get().getDuration()); 
        // Change powerUp
        final PowerUpDoublePoints powerUpDoublePoints = powerUpFactory.createPowerUpDoublePoints(coordinatesPlayer);
        powerUpDoublePoints.collect(player);
        assertEquals(powerUpDoublePoints.getType(), player.getPowerUpHandler().getPowerUpActive().get().getType());
        assertEquals(Score.Multiplier.DOUBLE.getValue(), player.getScore().getMultiplier());
    }

    @Test
    void testStatsPowerUpAttack() {
        // Create a powerUp colliding with player
        final PowerUpAttack powerUpAttack = powerUpFactory.createPowerUpAttack(coordinatesPlayer);
        powerUpAttack.collect(player);
        // Test powerUp activation
        assertTrue(player.getPowerUpHandler().getPowerUpActive().get().stillActive());
        // Test powerUp stats changing
        assertEquals(SwordImpl.STRONG, player.getSword().getSwordStrength());
        assertEquals(powerUpAttack.getDuration(), player.getPowerUpHandler().getPowerUpActive().get().getDuration()); 
        // Test score
        assertNotEquals(0, player.getScore().getPoints());
    }

    @Test
    void testStatsPowerUpDoublePoints() {
        // Create a powerUp colliding with player
        final PowerUpDoublePoints powerUpDoublePoints = powerUpFactory.createPowerUpDoublePoints(coordinatesPlayer);
        powerUpDoublePoints.collect(player);
        // Test powerUp activation
        assertTrue(player.getPowerUpHandler().getPowerUpActive().get().stillActive());
        // Test powerUp stats changing
        assertEquals(Score.Multiplier.DOUBLE.getValue(), player.getScore().getMultiplier());
        assertEquals(powerUpDoublePoints.getDuration(), player.getPowerUpHandler().getPowerUpActive().get().getDuration()); 
        // Test score
        assertNotEquals(0, player.getScore().getPoints());
    }

    @Test
    void testStatsPowerUpInvincibility() {
        // Create a powerUp colliding with player
        final PowerUpInvincibility powerUpInvincibility = powerUpFactory.createPowerUpInvincibility(coordinatesPlayer);
        powerUpInvincibility.collect(player);
        // Test powerUp activation
        // Test powerUp stats changing
        assertEquals(CollisionType.STUNNED, player.getBounds().getCollisionType());
        assertEquals(powerUpInvincibility.getDuration(), player.getPowerUpHandler().getPowerUpActive().get().getDuration()); 
        // Test score
        assertNotEquals(0, player.getScore().getPoints());
    }

    @Test
    void testStatsPowerUpSpeed() {
        // Create a powerUp colliding with player
        final PowerUpSpeed powerUpSpeed = powerUpFactory.createPowerUpSpeed(coordinatesPlayer);
        powerUpSpeed.collect(player);
        // Test powerUp activation
        assertTrue(player.getPowerUpHandler().getPowerUpActive().get().stillActive());
        // Test powerUp stats changing
        final Direction movementDirection = Direction.RIGHT;
        player.move(movementDirection, map);
        assertEquals(coordinatesPlayerSpeed.getX(), player.getPosition().getX());
        assertEquals(powerUpSpeed.getDuration(), player.getPowerUpHandler().getPowerUpActive().get().getDuration()); 
        // Test score
        assertNotEquals(0, player.getScore().getPoints());
    }

    @Test
    void testPowerUpDuration() {
        // Create a powerUp colliding with player
        final PowerUpAttack powerUpAttack = powerUpFactory.createPowerUpAttack(coordinatesPlayer);
        // powerUp gets collected and activated
        powerUpAttack.collect(player);
        player.getPowerUpHandler().update(player);
        final int duration = player.getPowerUpHandler().getPowerUpActive().get().getDuration();
        // Test if powerUp got collected and updated player stats
        assertEquals(player.getPowerUpHandler().getPowerUpActive().get().getDuration(), duration);
        assertEquals(Player.PlayerColor.STRENGTH.getColor(), player.getColor());
        assertEquals(Sword.STRONG, player.getSword().getSwordStrength());
        // Let the powerUp deactivate
        for (int i = 0; i < duration - 1; i++) {
            player.getPowerUpHandler().update(player);
            assertEquals(Player.PlayerColor.STRENGTH.getColor(), player.getColor());
            assertEquals(Sword.STRONG, player.getSword().getSwordStrength());
        }
        // deactivate powerUp
        player.getPowerUpHandler().update(player);
        assertEquals(Player.PlayerColor.NONE.getColor(), player.getColor());
        assertEquals(Sword.NORMAL, player.getSword().getSwordStrength());
    }

}
