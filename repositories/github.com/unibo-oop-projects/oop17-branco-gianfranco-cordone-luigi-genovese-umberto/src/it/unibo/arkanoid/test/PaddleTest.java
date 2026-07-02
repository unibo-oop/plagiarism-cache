package it.unibo.arkanoid.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import it.unibo.arkanoid.model.Level;
import it.unibo.arkanoid.model.LevelBuilder;
import it.unibo.arkanoid.model.LevelBuilderImpl;
import it.unibo.arkanoid.subject.Paddle;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * Class to test if {@link Paddle} works correctly.
 *
 */
public class PaddleTest {

    private final LevelBuilder levelBuilder = new LevelBuilderImpl();
    private final Level level = levelBuilder.build();
    private final Paddle paddle = new Paddle(0, 0, 50, 10, new Vector2D(0, 0), level);

    /**
     * Test Position.
     */
    @Test
    public void testPosition() { //NOPMD
        this.checkPosition(0, 0);
        this.paddle.update(100);
        this.checkPosition(0, 0);
        this.paddle.setPosition(new Vector2D(1.0, 0.0));
        this.checkPosition(1.0, 0);
        this.paddle.setPosition(new Vector2D(-1.0, 0.0));
        this.checkPosition(-1.0, 0);
        this.paddle.setPosition(new Vector2D(0.0, 1.0));
        this.checkPosition(0, 1.0);
        this.paddle.setPosition(new Vector2D(0.0, -1.0));
        this.checkPosition(0, -1.0);
    }

    /**
     * Test Velocity.
     */
    @Test
    public void testVelocity() { //NOPMD
        this.checkVelocity(0.0, 0.0);
        this.paddle.setVelocity(new Vector2D(1.0, 0.0));
        this.checkVelocity(1.0, 0.0);
        this.paddle.setVelocity(new Vector2D(-1.0, 0.0));
        this.checkVelocity(-1.0, 0.0);
        this.paddle.setVelocity(new Vector2D(0.0, 1.0));
        this.checkVelocity(0.0, 1.0);
        this.paddle.setVelocity(new Vector2D(0.0, -1.0));
        this.checkVelocity(0.0, -1.0);
        assert (true);
    }

    private void checkPosition(final double x, final double y) {
        final Vector2D paddlePosition = this.paddle.getPosition();
        assertEquals(paddlePosition.getX(), x); //NOPMD
        assertEquals(paddlePosition.getY(), y); //NOPMD
    }

    private void checkVelocity(final double x, final double y) {
        assertEquals(this.paddle.getVelocity().getX(), x); //NOPMD 
        assertEquals(this.paddle.getVelocity().getY(), y); //NOPMD
    }
}
