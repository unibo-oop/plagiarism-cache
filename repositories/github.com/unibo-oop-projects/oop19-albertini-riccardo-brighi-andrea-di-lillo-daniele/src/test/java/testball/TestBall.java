package testball;

import element.Elements;
import element.Point2D;
import element.Vector2D;
import element.Vector2DImpl;
import model.ball.Ball;
import model.ball.BallBuilder;
import model.ball.BallBuilderImpl;
import org.junit.jupiter.api.Test;
import utility.RangeFactory;
import utility.RangeFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestBall {

    private final BallBuilder builder = new BallBuilderImpl();
    private final RangeFactory rangeFactory = new RangeFactoryImpl();

    @Test
    void testBuilder() {

        assertThrows(IllegalStateException.class, builder::build);

        assertThrows(IllegalArgumentException.class, () -> builder.setRadius(-2));

        assertThrows(IllegalArgumentException.class, () -> builder.addDamage(-2));

        assertThrows(IllegalArgumentException.class, () -> builder.addMovementObserver(null));

        assertThrows(IllegalArgumentException.class, () -> builder.addStopObserver(null));

        assertThrows(IllegalArgumentException.class, () -> builder.addStartPosition(null));
    }


    @Test
    void testBallMovementByTime() {

        final Vector2D direction = new Vector2DImpl(1, 1);
        Point2D position =  Elements.ORIGIN;

        final Ball ball = builder.addStartPosition(position).build();
        ball.setDirection(direction);

        final long timeInterval = 20;
        for (final int i : rangeFactory.standardRange(4)) {
            ball.moveByTime(timeInterval);
            assertEquals(position.sum(ball.getDirection().scalarMultiplication(timeInterval * ball.getSpeed())), ball.getPosition());
            position = ball.getPosition();
        }
    }

    @Test
    void testBallMovementByDistance() {

        final Vector2D direction = new Vector2DImpl(1, 1);
        Point2D position =  Elements.ORIGIN;

        final Ball ball = builder.addStartPosition(position)
                .addMovementObserver((source, arg) -> System.out.println("test"))
                .addStopObserver((source, arg) -> System.out.println("stop"))
                .build();
        ball.setDirection(direction);

        final double distance = 40;
        for (final int i : rangeFactory.standardRange(6)) {
            ball.moveByDistance(distance);
            assertEquals(position.sum(ball.getDirection().scalarMultiplication(distance)), ball.getPosition());
            position = ball.getPosition();
        }
        ball.setDirection(Elements.VECTOR_NULL);
    }

    @Test
    void testBallCollision() {
        final Vector2D direction = new Vector2DImpl(1, 0);
        final Vector2D newDirection = Elements.VECTOR_NULL;
        final Point2D position =  Elements.ORIGIN;

        final Ball ball = builder.addStartPosition(position).build();
        ball.setDirection(direction);
        ball.moveByDistance(2);

        ball.collision(position.sum(direction), newDirection);

        assertEquals(position.sum(direction).sum(newDirection), ball.getPosition());

        assertThrows(IllegalArgumentException.class, () -> ball.collision(position, newDirection));
    }
}
