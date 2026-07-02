package clashclass.elements.troops;

import clashclass.commons.HealthComponent;
import clashclass.commons.Transform2D;
import clashclass.commons.Vector2D;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TroopFactoryTest {
    TroopFactory troopFactory;

    @Test
    void testTroopFactoryCreation() {
        troopFactory = new PlayerTroopFactoryImpl();

        final var barbarian = troopFactory.createBarbarian(new Vector2D(20, 20));

        assertTrue(barbarian.getComponentOfType(Transform2D.class).isPresent());
    }

    @Test
    void testBattleTroopFactoryHasAdditionalComponents() {
        troopFactory = new BattleTroopFactoryImpl();

        final var barbarian = troopFactory.createBarbarian(new Vector2D(20, 20));

        assertTrue(barbarian.getComponentOfType(Transform2D.class).isPresent());
        assertTrue(barbarian.getComponentOfType(HealthComponent.class).isPresent());
    }
}
