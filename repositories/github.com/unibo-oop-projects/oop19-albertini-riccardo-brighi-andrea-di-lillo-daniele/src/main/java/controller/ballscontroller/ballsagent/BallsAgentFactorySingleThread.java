package controller.ballscontroller.ballsagent;

import element.Point2D;
import model.ball.Ball;

import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

public class BallsAgentFactorySingleThread extends BallsAgentFactoryAbstract {

    @Override
    protected BallsAgentPause ballPauseAgent(final Set<Ball> balls, final Point2D startPosition, final long timeInterval, final UnaryOperator<Long> calculateTime) {
        return new BallsAgentThread() {

            private Optional<Point2D> startPoint = Optional.of(startPosition);

            @Override
            public Optional<Point2D> getStartPoint() {
                return this.startPoint;
            }

            @Override
            public void groupBall(final Ball ball) {
                if (this.getEndPoint().isEmpty()) {
                    throw new IllegalStateException();
                }
                if (!ball.getPosition().equals(this.getEndPoint().get())) {
                    ball.setDirection(this.getEndPoint().get().subtraction(ball.getPosition()));
                    ball.setDestination(this.getEndPoint().get());
                    if (this.isOver()) {
                        this.start();
                    }
                }
                if (this.areBallsStationary() && startPoint.isEmpty()) {
                    startPoint = this.getEndPoint();
                    this.resetEndPoint();
                }
            }

            @Override
            public void run() {
                long lastUpdate = System.currentTimeMillis();
                while (true) {
                    synchronized (this) {
                        if (this.isPause()) {
                            try {
                                this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            lastUpdate = System.currentTimeMillis();
                        }
                    }
                    final long time = System.currentTimeMillis();
                    final long interval = time - lastUpdate;
                    synchronized (balls) {
                        balls.stream()
                                .filter(i -> !i.isStationary())
                                .forEach(i -> i.moveByTime(calculateTime.apply(interval)));
                    }
                    lastUpdate = time;
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (this.areBallsStationary()) {
                        this.over();
                    }
                }
            }

            @Override
            public boolean areBallsStationary() {
                synchronized (balls) {
                    return balls.stream().allMatch(Ball::isStationary);
                }
            }

            @Override
            public boolean areBallsGrouped() {
                synchronized (balls) {
                    return balls.stream().map(Ball::getPosition).distinct().count() == 1;
                }
            }
        };
    }
}
