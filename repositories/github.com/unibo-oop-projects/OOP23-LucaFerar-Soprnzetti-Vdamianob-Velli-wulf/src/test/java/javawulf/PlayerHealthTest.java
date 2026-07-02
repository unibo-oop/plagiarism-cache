package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// CHECKSTYLE: OFF
//The annotation is needed to avoid false positives
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
// CHECKSTYLE: ON
import javawulf.model.BoundingBox;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.AbstractEntity;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerHealth;
import javawulf.model.player.PlayerImpl;
import javawulf.model.player.PlayerHealth.ShieldStatus;

/**
 * PlayerHealthTest tests whether the implementation of PlayerHealth
 * works as it should.
 */
final class PlayerHealthTest {

    private static final int HEALTH = 3;
    private static final int STARTING_X = 12;
    private static final int STARTING_Y = 12;
    private static final int STARTING_POINTS = 0;
    private Player player;
    private PlayerHealth hp;

    @BeforeEach
    void createPlayer() {
        this.player = new PlayerImpl(STARTING_X, STARTING_Y, HEALTH, STARTING_POINTS);
        this.hp = this.player.getPlayerHealth();
    }

    @Test
    void testStartingPlayerHealth() {
        assertEquals(HEALTH, this.hp.getHealth());
        assertEquals(HEALTH, this.hp.getMaxHealth());
        assertEquals(ShieldStatus.NONE, this.hp.getShieldStatus());
    }

    @Test
    @SuppressFBWarnings(
        value = {
            "L", "D", "UwF"
        },
        justification = "Player is initialized in the @BeforeEach"
    )
    void testDamageComingFromPlayer() {
        final BoundingBox enemy = new BoundingBoxImpl(STARTING_X, STARTING_Y,
            AbstractEntity.OBJECT_SIZE, AbstractEntity.OBJECT_SIZE, CollisionType.ENEMY);
        this.player.isHit(enemy);
        assertEquals(HEALTH - 1, this.hp.getHealth());
        assertEquals(HEALTH, this.hp.getMaxHealth());
    }

    @Test
    void testHealthChange() {
        this.hp.setHealth(-1);
        assertEquals(HEALTH - 1, this.hp.getHealth());
        assertEquals(HEALTH, this.hp.getMaxHealth());
        this.hp.setHealth(2);
        assertEquals(HEALTH, this.hp.getHealth());
        assertEquals(HEALTH, this.hp.getMaxHealth());
    }

    @Test
    void testMaxHealthIncrease() {
        this.hp.increaseMaxHealth(1);
        assertEquals(HEALTH + 1, this.hp.getMaxHealth());
        assertEquals(HEALTH, this.hp.getHealth());
    }

    @Test
    void testShield() {
        this.hp.setShieldStatus(ShieldStatus.FULL);
        assertEquals(ShieldStatus.FULL, this.hp.getShieldStatus());

        this.hp.setHealth(-1);
        assertEquals(HEALTH, this.hp.getHealth());
        assertEquals(ShieldStatus.HALF, this.hp.getShieldStatus());

        this.hp.setHealth(-1);
        assertEquals(HEALTH, this.hp.getHealth());
        assertEquals(ShieldStatus.NONE, this.hp.getShieldStatus());

        this.hp.setHealth(-1);
        assertEquals(HEALTH - 1, this.hp.getHealth());
        assertEquals(ShieldStatus.NONE, this.hp.getShieldStatus());
    }

    @Test
    void testHealthChangeAndShield() {
        this.hp.setHealth(-1);
        this.hp.setShieldStatus(ShieldStatus.FULL);
        this.hp.setHealth(-1);
        assertEquals(HEALTH - 1, this.hp.getHealth());
        assertEquals(ShieldStatus.HALF, this.hp.getShieldStatus());
    }

}
