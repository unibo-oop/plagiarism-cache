package controller.ballscontroller.ballsagent;

import model.ball.Ball;
import element.Point2D;

import java.util.Optional;

public class BallsAgentThreadNoPause implements BallsAgentPause {

    private final BallsAgentPause ballsAgent;

    public BallsAgentThreadNoPause(final BallsAgentPause ballsAgent) {
        this.ballsAgent = ballsAgent;
    }

    @Override
    public void restart() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void move() {
        ballsAgent.move();
    }

    @Override
    public boolean isPause() {
        return ballsAgent.isPause();
    }

    @Override
    public boolean isOver() {
        return ballsAgent.isOver();
    }

    @Override
    public void setEndPoint(final Point2D endPoint) {
        ballsAgent.setEndPoint(endPoint);
    }

    @Override
    public Optional<Point2D> getStartPoint() {
        return ballsAgent.getStartPoint();
    }

    @Override
    public Optional<Point2D> getEndPoint() {
        return ballsAgent.getEndPoint();
    }

    @Override
    public void groupBall(final Ball ball) {
        ballsAgent.groupBall(ball);
    }

    @Override
    public boolean areBallsStationary() {
        return ballsAgent.areBallsStationary();
    }
}
