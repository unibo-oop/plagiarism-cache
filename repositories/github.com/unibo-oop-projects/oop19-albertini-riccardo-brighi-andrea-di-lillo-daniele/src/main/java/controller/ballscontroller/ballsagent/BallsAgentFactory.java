package controller.ballscontroller.ballsagent;

import model.ball.Ball;
import element.Point2D;

import java.util.Set;

public interface BallsAgentFactory {

    /**
     * @param balls        the set of balls to move
     * @param startPoint   the start point
     * @param timeInterval the time to wait for a movement
     * @return a BallAgent
     */

    BallsAgentPause standardBallAgent(Set<Ball> balls, Point2D startPoint, long timeInterval);

    /**
     * @param balls        the set of balls to move
     * @param startPoint   the start point
     * @param timeInterval the time to wait for a movement
     * @return a BallAgent with pause
     */

    BallsAgentPause standardPauseBallAgent(Set<Ball> balls, Point2D startPoint, long timeInterval);

    /**
     * @param balls        the set of balls to move
     * @param startPoint   the start point
     * @param timeInterval the time to wait for a movement
     * @return a BallAgent with effective time
     */

    BallsAgentPause effectiveBallAgent(Set<Ball> balls, Point2D startPoint, long timeInterval);

    /**
     * @param balls        the set of balls to move
     * @param startPoint   the start point
     * @param timeInterval the time to wait for a movement
     * @return a BallAgent with pause and effective
     */

    BallsAgentPause effectiveTimePauseBallAgent(Set<Ball> balls, Point2D startPoint, long timeInterval);

}
