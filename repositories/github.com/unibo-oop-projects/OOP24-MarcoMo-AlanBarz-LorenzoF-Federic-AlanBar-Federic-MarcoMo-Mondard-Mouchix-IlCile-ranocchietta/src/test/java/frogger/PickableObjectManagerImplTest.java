package frogger;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.ExtraLifePowerUp;
import frogger.model.implementations.FreezePowerUp;
import frogger.model.implementations.GameImpl;
import frogger.model.implementations.PickableObjectDependency;
import frogger.model.implementations.PickableObjectImpl;
import frogger.model.implementations.PickableObjectManagerImpl;
import frogger.model.interfaces.PowerUp;

class PickableObjectManagerImplTest {

    private GameImpl game;
    private PickableObjectManagerImpl manager;

    @BeforeEach
    void setUp() {
        game = new GameImpl(new Pair(1, 1), "skin.png");
        manager = new PickableObjectManagerImpl(game);
    }

    @Test
    void testAddPickableObjectPowerUp() {
        final FreezePowerUp freeze = new FreezePowerUp(new Position(0, 0), new Pair(1, 1), 5);
        final ExtraLifePowerUp extraLife = new ExtraLifePowerUp(new Position(0, 0), new Pair(1, 1));

        manager.addPickableObject(freeze);
        manager.addPickableObject(extraLife);

        final List<PowerUp> active = manager.getActivePowerUps();
        assertEquals(2, active.size());
        if (active.get(0) instanceof FreezePowerUp) {
            assertTrue(active.get(0).isActive());
        } else {
            assertFalse(active.get(0).isActive());
        }
    }

    @Test
    void testCheckPowerUpsRemovesInactive() {
        final FreezePowerUp freeze = new FreezePowerUp(new Position(0, 0), new Pair(1, 1), 1);

        manager.addPickableObject(freeze);

        freeze.deactivate();
        manager.checkPowerUps();

        assertTrue(manager.getActivePowerUps().isEmpty());
    }

    @Test
    void testAddPickableObjectNonPowerUp() {
        final PickableObjectImpl obj = new PickableObjectImpl(new Position(0, 0), new Pair(1, 1)) {
            @Override
            public PickableObjectDependency getRequiredDependencies() {
                return PickableObjectDependency.PLAYER;
            }

            @Override
            public void onPick() { }
        };
        obj.setRelatedEntity(game.getPlayer());

        assertDoesNotThrow(() -> manager.addPickableObject(obj));
    }
}
