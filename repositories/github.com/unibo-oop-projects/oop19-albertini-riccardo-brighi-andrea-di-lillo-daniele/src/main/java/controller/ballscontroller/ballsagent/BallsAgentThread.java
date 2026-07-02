package controller.ballscontroller.ballsagent;

import controller.ballscontroller.PauseThread;
import element.Point2D;

import java.util.Optional;

public abstract class BallsAgentThread extends PauseThread implements BallsAgentPause {

    private Optional<Point2D> endPoint = Optional.empty();

    @Override
    public void move() {
        this.start();
    }

    @Override
    public synchronized void setEndPoint(final Point2D endPoint) {
        if (this.endPoint.isEmpty()) {
            this.endPoint = Optional.of(endPoint);
        }
    }

    @Override
    public synchronized Optional<Point2D> getEndPoint() {
        return endPoint;
    }

    /**
     * reset the endPoint
     */
    protected synchronized void resetEndPoint() {
        this.endPoint = Optional.empty();
    }
}
