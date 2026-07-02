package clashclass.ai.logic;

import clashclass.commons.ConversionUtility;
import clashclass.commons.Transform2D;
import clashclass.commons.Vector2D;
import clashclass.commons.VectorInt2D;
import clashclass.elements.buildings.BattleBuildingFactoryImpl;
import clashclass.elements.troops.BattleTroopFactoryImpl;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChooseTargetLogicTest {

    @Test
    void testChooseNearestTarget() {
        final var chooseTargetLogic = new ChooseTargetNearestLogicImpl();
        final var buildingsFactory = new BattleBuildingFactoryImpl();
        final var troopFactory = new BattleTroopFactoryImpl();

        final var building1 = buildingsFactory.createCannon(new VectorInt2D(30, 25));
        final var building2 = buildingsFactory.createCannon(new VectorInt2D(5, 10));
        final var building3 = buildingsFactory.createCannon(new VectorInt2D(28, 18));

        final var targetsList = List.of(building1, building2, building3);
        final var troop = troopFactory.createBarbarian(new Vector2D(0, 0));
        final var nearestTarget = chooseTargetLogic.chooseTarget(troop, targetsList);

        final var resultPosition = ConversionUtility.convertWorldToGridPosition(nearestTarget
                .getComponentOfType(Transform2D.class).get()
                .getPosition());

        assertEquals(new VectorInt2D(5, 10), resultPosition);
    }
}
