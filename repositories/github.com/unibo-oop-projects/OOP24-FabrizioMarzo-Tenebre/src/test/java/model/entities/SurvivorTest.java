package model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import model.armory.munition.Munition;
import model.armory.weapon.Weapon;
import model.entities.survivor.Common;
import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorState;
import model.physics.physics_entities.PhysicsBaseSurvivor;
import model.bounding_box.BoundingBox;
import model.bounding_box.RectBoundingBox;

public class SurvivorTest {

    private Survivor survivor;

    @BeforeEach
    public void setUp() throws Exception {
        survivor = new Common(
                1000,
                90,
                185,
                Pair.of(0.0, 0.0),
                Pair.of(200.0, 0.0),
                new PhysicsBaseSurvivor(),
                new RectBoundingBox(Pair.of(0.0, 185.0), Pair.of(90.0, 0.0)));
    }

    @Test
    public void testDamageSuffer() {
        survivor.damageSuffer(100);
        assertEquals(900, survivor.getLive());

        survivor.damageSuffer(1000);
        assertEquals(0, survivor.getLive());
    }

    @Test
    public void testPositionSetterGetterAndBoundingBoxUpdate() {
        Pair<Double, Double> newPos = Pair.of(10.0, 20.0);
        survivor.setPosition(newPos);
        assertEquals(newPos, survivor.getCurrentPos());

        BoundingBox bbox = survivor.getBBox();
        assertNotNull(bbox);
    }

    @Test
    public void testVelocitySetterGetter() {
        Pair<Double, Double> newVel = Pair.of(50.0, 75.0);
        survivor.setVelocity(newVel);
        assertEquals(newVel, survivor.getCurrentVel());

        assertEquals(Pair.of(200.0, 0.0), survivor.getBaseSurvivorVel());
    }

    @Test
    public void testStateSetterGetter() {
        survivor.setState(SurvivorState.SURVIVOR_MOVE_DOWN);
        assertEquals(SurvivorState.SURVIVOR_MOVE_DOWN, survivor.getState());
    }

    @Test
    public void testShootWithMockWeapon() {
        Weapon mockWeapon = Mockito.mock(Weapon.class);

        when(mockWeapon.shoot(anyDouble())).thenReturn(List.of());

        survivor.setWeapon(mockWeapon);

        List<Munition> shots = survivor.shoot(0.5);

        assertNotNull(shots);
        assertTrue(shots.isEmpty());

    }
}
