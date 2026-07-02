package frogger;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.FreezePowerUp;
import frogger.model.implementations.MovingObjectImpl;
import frogger.model.implementations.PickableObjectDependency;
import frogger.model.implementations.PowerUpType;

class FreezePowerUpTest {

    @Test
    void testApplyEffectSetsSpeedToZeroAndStoresOriginalSpeeds() {
        final float speedoObj1 = 2.5f;
        final float speedoObj2 = 1.0f;
        final DummyMovingObject obj1 = new DummyMovingObject(speedoObj1);
        final DummyMovingObject obj2 = new DummyMovingObject(speedoObj2);
        final List<MovingObjectImpl> obstacles = new ArrayList<>();
        obstacles.add(obj1);
        obstacles.add(obj2);

        final FreezePowerUp freeze = new FreezePowerUp(new Position(0, 0), new Pair(1, 1), 5);
        freeze.setRelatedEntity(obstacles);

        freeze.applyEffect();

        assertEquals(0f, obj1.getSpeed());
        assertEquals(0f, obj2.getSpeed());

        final float[] storedSpeeds = freeze.getEntitiesSpeed();
        assertArrayEquals(new float[]{speedoObj1, speedoObj2}, storedSpeeds);
    }

    @Test
    void testRemoveEffectRestoresOriginalSpeeds() {
        final float speedoObj1 = 3.0f;
        final float speedoObj2 = 4.0f;
        final DummyMovingObject obj1 = new DummyMovingObject(speedoObj1);
        final DummyMovingObject obj2 = new DummyMovingObject(speedoObj2);
        final List<MovingObjectImpl> obstacles = new ArrayList<>();
        obstacles.add(obj1);
        obstacles.add(obj2);

        final FreezePowerUp freeze = new FreezePowerUp(new Position(0, 0), new Pair(1, 1), 5);
        freeze.setRelatedEntity(obstacles);

        freeze.applyEffect();
        freeze.removeEffect();

        assertEquals(speedoObj1, obj1.getSpeed());
        assertEquals(speedoObj2, obj2.getSpeed());
    }

    @Test
    void testGetRequiredDependencies() {
        final FreezePowerUp freeze = new FreezePowerUp(new Position(0, 0), new Pair(1, 1), 5);
        assertEquals(PickableObjectDependency.OBSTACLE, freeze.getRequiredDependencies());
    }

    @Test
    void testGetPowerUpType() {
        final FreezePowerUp freeze = new FreezePowerUp(new Position(0, 0), new Pair(1, 1), 5);
        assertEquals(PowerUpType.FREEZE, freeze.getPowerUpType());
    }

    @Test
    void testSetAndGetEntitiesSpeedDefensiveCopy() {
        final FreezePowerUp freeze = new FreezePowerUp(new Position(0, 0), new Pair(1, 1), 5);
        final float[] speeds = {1.1f, 2.2f};
        freeze.setEntitiesSpeed(speeds);

        final float[] returned = freeze.getEntitiesSpeed();
        assertArrayEquals(speeds, returned);

        // Modify the returned array and check that the internal one does not change
        final float tmp = 50.0f;
        returned[0] = tmp;
        assertNotEquals(returned[0], freeze.getEntitiesSpeed()[0]);
    }

    // Dummy MovingObjectImpl for the test
    class DummyMovingObject extends MovingObjectImpl {
        private float speed;

        DummyMovingObject(final float speed) {
            super(new Position(0, 0), new Pair(1, 1), speed, Direction.LEFT);
            this.speed = speed;
        }

        @Override
        public float getSpeed() {
            return speed;
        }

        @Override
        public void setSpeed(final float speed) {
            this.speed = speed;
        }
    }
}
