package model.ship;

import com.almasb.fxgl.core.math.Vec2;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import model.bullet.Bullet;
import model.gun.Gun;
import model.gun.GunFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import utilities.EnumInt;
import utilities.InputCommands;
import utilities.PlayerGunValues;
import utilities.PlayerValues;

import static org.junit.jupiter.api.Assertions.*;

public final class PlayerShipTest {

    PlayerShip playerShip;

    @BeforeEach
    void setUp() {
        new JFXPanel();
        Vec2 initialPos = new Vec2(EnumInt.WIDTH.getValue()/2f, EnumInt.HEIGHT.getValue()/2f);
        playerShip = new PlayerShip(initialPos,
                PlayerValues.MAIN_SHIP.getValueFromKey("MAXHEALTH"),
                PlayerValues.MAIN_SHIP.getValueFromKey("MAXSPEED"),
                PlayerValues.MAIN_SHIP.getValueFromKey("ROTATIONSPEED")
        );
        playerShip.setSprite(new Image("images/shipPlayer.png"));
    }

    @Test
    @DisplayName("Movement Test")
    void testMovement() {
        Vec2 currentPosition = playerShip.getPosition().copy();
        double yaw = playerShip.getAngle();
        playerShip.thrust(InputCommands.UP);
        playerShip.move(1000000L);
        assertNotEquals(currentPosition, playerShip.getPosition(), "Player has moved");
        assertEquals(yaw,playerShip.getAngle());
        playerShip.rotate(InputCommands.RIGHT);
        playerShip.move(1000000L);
        assertNotEquals(currentPosition, playerShip.getPosition(), "Player has moved in another direction");
        assertNotEquals(yaw,playerShip.getAngle(), "Player has changed angle");
        currentPosition = playerShip.getPosition().copy();
        playerShip.decaySpeed();
        playerShip.move(1000000L);
        assertNotEquals(currentPosition, playerShip.getPosition(), "Player has slowed down");
    }

    @RepeatedTest(5)
    @DisplayName("Damage Test")
    void testStrike() {
        int damageAmount = 5;
        int currentHealth = playerShip.getHealth();
        playerShip.strike(damageAmount);
        assertEquals(currentHealth-damageAmount, playerShip.getHealth(), "Player took damage");
        playerShip.setHealth(0);
        assertFalse(playerShip.isAlive(), "Player has died");
    }

    @Test
    @DisplayName("Bullet Shot Test")
    void testShot() {
        Gun playerGun = GunFactory.playerGun(this.playerShip,
                (int)   PlayerGunValues.MAIN_GUN.getValueFromKey("DAMAGE")*100,
                PlayerGunValues.MAIN_GUN.getValueFromKey("MAXSPEED"),
                PlayerGunValues.MAIN_GUN.getValueFromKey("ACCELERATION"),
                PlayerGunValues.MAIN_GUN.getValueFromKey("ROTATIONSPEED"));
        playerShip.setGun(playerGun);
        assertNotNull(playerShip.shot(), "Player has shot");
        Bullet bullet = playerShip.shot();
        Vec2 currentPos = bullet.getPosition();
        bullet.move(1000000L);
        assertNotEquals(currentPos, bullet.getPosition(), "Player has moved");
        Ship enemy = EnemyFactory.basicEnemy(bullet.getPosition());
        enemy.strike(bullet.getDamage());
        assertFalse(enemy.isAlive(), "Enemy got hit and died");
    }
}
