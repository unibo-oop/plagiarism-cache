package testball;

import controller.ballscontroller.BallsController;
import controller.ballscontroller.BallsControllerBuilder;
import controller.ballscontroller.BallsControllerBuilderImpl;
import element.Elements;
import element.Point2D;
import element.Point2DImpl;
import model.ball.Ball;
import model.ball.BallBuilder;
import model.ball.BallBuilderImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBallsController {

    private final Point2D start = Elements.ORIGIN;


    @Test
    void testBuilder() throws InterruptedException {
        final BallBuilder ballBuilder = new BallBuilderImpl().addMovementObserver((source, arg) -> {
            System.out.println("movement");
            arg.setDestination(new Point2DImpl(0, 100));
        });
        final BallsController controller = new BallsControllerBuilderImpl()
                .addBallsBuilder(ballBuilder)
                .addEndLaunchObserver((source, arg) -> System.out.println("fine lancio"))
                .addStartPosition(start)
                .addType(BallsControllerBuilder.Type.SINGLE_THREAD)
                .addTime(BallsControllerBuilder.Time.ABSTRACT_TIME)
                .addPause(BallsControllerBuilder.Pause.PAUSE)
                .addTimeInterval(20)
                .addNumberOfStepBetweenBalls(2)
                .build();
        assertTrue(controller.areBallsStationary());
        assertEquals(1, controller.getBalls().size());
        controller.addBall();
        assertEquals(2, controller.getBalls().size());

        controller.launchBalls(0, 1);
        Thread.sleep(40);
        assertFalse(controller.areBallsStationary());
        Thread.sleep(60);
        assertEquals(1, controller.getBalls().stream().filter(Ball::isStationary).count());
        assertFalse(controller.areBallsStationary());
        Thread.sleep(40);
        assertTrue(controller.areBallsStationary());

    }

    @Test
    void testPause() throws InterruptedException {
        final BallBuilder ballBuilder = new BallBuilderImpl().addMovementObserver((source, arg) -> {
            System.out.println("movement");
            arg.setDestination(new Point2DImpl(0, 100));
        });
        final BallsController controller = new BallsControllerBuilderImpl()
                .addBallsBuilder(ballBuilder)
                .addEndLaunchObserver((source, arg) -> System.out.println("fine lancio"))
                .addStartPosition(start)
                .addType(BallsControllerBuilder.Type.SINGLE_THREAD)
                .addTime(BallsControllerBuilder.Time.ABSTRACT_TIME)
                .addPause(BallsControllerBuilder.Pause.PAUSE)
                .addTimeInterval(20)
                .addNumberOfStepBetweenBalls(2)
                .build();
        assertTrue(controller.areBallsStationary());
        assertEquals(1, controller.getBalls().size());
        controller.addBall();
        assertEquals(2, controller.getBalls().size());

        controller.launchBalls(0, 1);
        Thread.sleep(40);
        assertFalse(controller.areBallsStationary());

        controller.pause();
        Thread.sleep(40);
        assertTrue(controller.isPaused());
        controller.restart();

        Thread.sleep(60);
        assertEquals(1, controller.getBalls().stream().filter(Ball::isStationary).count());
        assertFalse(controller.areBallsStationary());
        Thread.sleep(40);
        assertTrue(controller.areBallsStationary());

    }

    @Test
    void testBallsControllerTwice() throws InterruptedException {
        final BallBuilder ballBuilder = new BallBuilderImpl().addMovementObserver((source, arg) -> {
            System.out.println("movement");
            arg.setDestination(new Point2DImpl(0, 100));
        });
        final BallsController controller = new BallsControllerBuilderImpl()
                .addBallsBuilder(ballBuilder)
                .addEndLaunchObserver((source, arg) -> System.out.println("fine lancio"))
                .addStartPosition(start)
                .addType(BallsControllerBuilder.Type.SINGLE_THREAD)
                .addTime(BallsControllerBuilder.Time.ABSTRACT_TIME)
                .addPause(BallsControllerBuilder.Pause.PAUSE)
                .addTimeInterval(20)
                .addNumberOfStepBetweenBalls(2)
                .build();
        assertTrue(controller.areBallsStationary());
        assertEquals(1, controller.getBalls().size());

        controller.launchBalls(0, 1);
        Thread.sleep(110);
        assertTrue(controller.areBallsStationary());
        controller.getBalls().forEach(i -> i.setDestination(new Point2DImpl(0, 200)));
        controller.launchBalls(0, 200);
        Thread.sleep(110);
        assertTrue(controller.areBallsStationary());

    }
}
