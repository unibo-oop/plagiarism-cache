package controller.ballscontroller;

import element.Point2D;
import model.ball.Ball;
import model.ball.BallBuilder;
import utility.Observer;

import java.util.Set;

public interface BallsControllerBuilder {

    enum Type {
        SINGLE_THREAD,
        MULTI_THREAD
    }

    enum Pause {
        PAUSE,
        UNPAUSE
    }

    enum Time {
        EFFECTIVE_TIME,
        ABSTRACT_TIME
    }

    /**
     * @return a BallsController
     * @throws IllegalStateException if the parameter are incompatible
     */
    BallsController build();

    /**
     * @param type the type of the BallsController
     * @return the BallsControllerBuilder
     */

    BallsControllerBuilder addType(Type type);

    /**
     * @param pause the type of pause
     * @return the BallsControllerBuilder
     * @throws IllegalStateException if the @param pause is incompatible
     */

    BallsControllerBuilder addPause(Pause pause);

    /**
     * @param time the type of time
     * @return the BallsControllerBuilder
     * @throws IllegalStateException if the @param time is incompatible
     */

    BallsControllerBuilder addTime(Time time);

    /**
     * @param initialBalls the initial balls
     * @return the BallsControllerBuilder
     * @throws IllegalArgumentException if the @param initialBalls is minus of 1
     */

    BallsControllerBuilder addInitialBalls(int initialBalls);

    /**
     * @param ballsBuilder the builder of the balls
     * @return the BallsControllerBuilder
     * @throws IllegalArgumentException if the @param ballsBuilder is null
     */

    BallsControllerBuilder addBallsBuilder(BallBuilder ballsBuilder);

    /**
     * @param startPoint the point of start
     * @return the BallsControllerBuilder
     * @throws IllegalArgumentException if the @param startPoint is null
     */

    BallsControllerBuilder addStartPosition(Point2D startPoint);

    /**
     * @param timeInterval the time interval
     * @return the BallsControllerBuilder
     * @throws IllegalArgumentException if the @param timeInterval is minus of 1
     */

    BallsControllerBuilder addTimeInterval(long timeInterval);

    /**
     * @param stepBetweenBalls the step between the balls
     * @return the BallsControllerBuilder
     * @throws IllegalArgumentException if the @param timeInterval is minus of 0
     */

    BallsControllerBuilder addNumberOfStepBetweenBalls(int stepBetweenBalls);

    /**
     * @param endLaunch the observer of the end launch
     * @return the BallsControllerBuilder
     * @throws IllegalArgumentException if the @param endLaunch is null
     */

    BallsControllerBuilder addEndLaunchObserver(Observer<Set<? extends Ball>> endLaunch);


}
