package model.ball;

import utility.Observer;
import element.Point2D;

public interface BallBuilder {

    /**
     * @return the ball build with the param add
     */

    Ball build();

    /**
     * @param radius the radius of the ball
     * @return a BallBuilder
     */

    BallBuilder setRadius(double radius);

    /**
     * @param damage the damage of the ball
     * @return a BallBuilder
     */

    BallBuilder addDamage(int damage);

    /**
     * @param startSpeed the start speed of the ball
     * @return a BallBuilder
     */

    BallBuilder addStartSpeed(int startSpeed);

    /**
     * @param startPosition the start position of the ball
     * @return a BallBuilder
     */

    BallBuilder addStartPosition(Point2D startPosition);

    /**
     * @param observer the observer to add
     * @return a BallBuilder
     */

    BallBuilder addMovementObserver(Observer<Ball> observer);

    /**
     * @param observer the observer to add
     * @return a BallBuilder
     */

    BallBuilder addStopObserver(Observer<Ball> observer);
}
