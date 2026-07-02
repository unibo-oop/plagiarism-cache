package frogger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.ExtraLifePowerUp;
import frogger.model.implementations.PickableObjectDependency;
import frogger.model.implementations.PlayerObjectImpl;
import frogger.model.implementations.PowerUpType;

class ExtraLifePowerUpTest {

    @Test
    void testApplyEffectAddsLifeToPlayer() {
        final PlayerObjectImpl player = new PlayerObjectImpl(new Pair(1, 1), "skin.png");
        final int initialLives = player.getLives();

        final ExtraLifePowerUp powerUp = new ExtraLifePowerUp(new Position(1, 1), new Pair(1, 1));
        powerUp.setRelatedEntity(player);

        powerUp.applyEffect();

        assertEquals(initialLives + 1, player.getLives());
    }

    @Test
    void testApplyEffectDoesNothingIfNotPlayer() {
        final Object notAPlayer = new Object();

        final ExtraLifePowerUp powerUp = new ExtraLifePowerUp(new Position(1, 1), new Pair(1, 1));
        powerUp.setRelatedEntity(notAPlayer);

        assertDoesNotThrow(powerUp::applyEffect);
    }

    @Test
    void testRemoveEffectDoesNothing() {
        final ExtraLifePowerUp powerUp = new ExtraLifePowerUp(new Position(1, 1), new Pair(1, 1));
        assertDoesNotThrow(powerUp::removeEffect);
    }

    @Test
    void testGetRequiredDependencies() {
        final ExtraLifePowerUp powerUp = new ExtraLifePowerUp(new Position(1, 1), new Pair(1, 1));
        assertEquals(PickableObjectDependency.PLAYER, powerUp.getRequiredDependencies());
    }

    @Test
    void testGetPowerUpType() {
        final ExtraLifePowerUp powerUp = new ExtraLifePowerUp(new Position(1, 1), new Pair(1, 1));
        assertEquals(PowerUpType.EXTRA_LIFE, powerUp.getPowerUpType());
    }
}
