package it.unibo.roguekong.test;

import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.entity.impl.powerup.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerUpsTest {
    private PlayerImpl player;
    private ChangePlayerSpeed changePlayerSpeed;
    private DoubleJump doubleJump;
    private ChangePlayerGravity changePlayerGravity;
    private Invulnerability invulnerability;
    private IncreasePlayerLives increasePlayerLives;

    @BeforeEach
    void setUp(){
        player = new PlayerImpl();
        changePlayerSpeed = new ChangePlayerSpeed(1.4);
        doubleJump = new DoubleJump();
        changePlayerGravity = new ChangePlayerGravity(0.5);
        invulnerability = new Invulnerability();
        increasePlayerLives = new IncreasePlayerLives();
    }

    @Test
    void testSpeedPowerUp(){
        changePlayerSpeed.applyEffect(player);
        assertEquals(1.4, player.getVelocity().getVelocityX());
    }

    @Test
    void testDoubleJumpPowerUp(){
        doubleJump.applyEffect(player);
        assertEquals(2, player.getMaxJumps());
    }

    @Test
    void testChangePlayerGravity(){
        changePlayerGravity.applyEffect(player);
        assertEquals(0.025, player.getGravity().gravity());
    }

    @Test
    void testLivesPowerUp(){
        increasePlayerLives.applyEffect(player);
        assertEquals(4, player.getLives().getLives());
    }

    @Test
    void testRemoveAllEffectd(){
        ChangePlayerSpeed.removeEffect(player);
        DoubleJump.removeEffect(player);
        ChangePlayerGravity.removeEffect(player);
        assertEquals(1, player.getVelocity().getVelocityX());
        assertEquals(1, player.getMaxJumps());
        assertEquals(0.05, player.getGravity().gravity());
    }
}
