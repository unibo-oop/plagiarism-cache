package controller.ballscontroller.ballsagent;

import element.Point2D;
import model.ball.Ball;

import java.util.Set;
import java.util.function.UnaryOperator;

public abstract class BallsAgentFactoryAbstract implements BallsAgentFactory {
    @Override
    public BallsAgentPause standardBallAgent(final Set<Ball> balls, final Point2D startPoint, final long timeInterval) {
        return new BallsAgentThreadNoPause(this.standardPauseBallAgent(balls, startPoint, timeInterval));
    }

    @Override
    public BallsAgentPause standardPauseBallAgent(final Set<Ball> balls, final Point2D startPoint, final long timeInterval) {
        return ballPauseAgent(balls, startPoint, timeInterval, i -> timeInterval);
    }

    @Override
    public BallsAgentPause effectiveBallAgent(final Set<Ball> balls, final Point2D startPoint, final long timeInterval) {
        return new BallsAgentThreadNoPause(this.effectiveTimePauseBallAgent(balls, startPoint, timeInterval));
    }

    @Override
    public BallsAgentPause effectiveTimePauseBallAgent(final Set<Ball> balls, final Point2D startPoint, final long timeInterval) {
        return ballPauseAgent(balls, startPoint, timeInterval, UnaryOperator.identity());
    }

    /**
     *
     * @param balls the set of balls to move
     * @param startPosition the star position
     * @param timeInterval the interval between the ball
     * @param calculateTime the function to calculate the time
     * @return a BallAgentPause
     */
    protected abstract BallsAgentPause ballPauseAgent(Set<Ball> balls, Point2D startPosition, long timeInterval, UnaryOperator<Long> calculateTime);
}
