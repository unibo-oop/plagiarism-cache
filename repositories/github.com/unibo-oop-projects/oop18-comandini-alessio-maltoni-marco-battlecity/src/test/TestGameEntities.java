package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import enums.Sprite;
import model.common.MovementImpl;
import model.common.PositionImpl;
import model.entities.Tank;
import model.entities.TankImpl;

class TestGameEntities {

    private static final double X_COORDINATE = 5;
    private static final double Y_COORDINATE = 7;

    @Test
    void testTank() {
        // Test Postion and Movement of the tank;
        Tank tank = new TankImpl(Sprite.PLAYER_TANK_YELLOW, new PositionImpl(X_COORDINATE, Y_COORDINATE));
        assertEquals(new PositionImpl(X_COORDINATE, Y_COORDINATE), tank.getActualPosition());
        // Test default position and movement of the tank;
        tank = new TankImpl(Sprite.PLAYER_TANK_YELLOW);
        assertEquals(new PositionImpl(0, 0), tank.getActualPosition());
        assertEquals(new MovementImpl(0, 0), tank.getActualMovement());
    }

    @Test
    void testBlock() {
        fail("Not yet implemented");
    }

    @Test
    void testBullet() {
        fail("Not yet implemented");
    }

}
