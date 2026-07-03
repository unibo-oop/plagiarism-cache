package breakout.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import breakout.model.entities.Ball;
import breakout.model.entities.Paddle;

/**
 * 
 */
public class GameObjectTest {

    /**
     * 
     */
    @Test
    public void testEntities() {
        Ball cBall = new Ball(0, 0, 1, 90, 0.5);
        Paddle cPaddle = new Paddle(0, 10, 1, 5, 1);

        System.out.println(cBall + "\n");
        System.out.println(cPaddle + "\n");

        cBall.update(10);
        cPaddle.update(10);

        assertTrue(cBall.collidedWith(cPaddle));

        cBall = new Ball(0, 0, 1, 90, 0.5);
        cPaddle = new Paddle(0, 10, 1, 5, 1);

    }

}
