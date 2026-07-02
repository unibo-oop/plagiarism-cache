import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.apache.commons.lang3.tuple.Pair;

import org.junit.jupiter.api.Test;

import model.ball.Ball;
import model.ball.BallImpl;
import model.character.Abra;
import model.world.GameWorld;
import model.world.GameWorldImpl;

public class AbraTest {

    private static final double POSX = 12.0;
    private static final double POSY = 15.0;

    @Test
    void testPower() {
        final GameWorld world = new GameWorldImpl();
        final Ball ball = new BallImpl();
        final Pair<Double, Double> pos = Pair.of(POSX, POSY);
        ball.move(pos);
        world.setBall(ball);
        final Abra abra = new Abra();
        abra.usePower(world);
        assertEquals(world.getBall().getPosition().getLeft(), ball.getPosition().getLeft());
        assertNotSame(world.getBall().getPosition().getRight() == POSY, "Ball's position after Abra's power");
    }
}
