package controller.ballscontroller.ballsagent;

import controller.ballscontroller.Pause;
import controller.ballscontroller.PauseThread;
import element.Point2D;
import model.ball.Ball;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

public class BallsAgentFactoryMultiThread extends BallsAgentFactoryAbstract {
    
    @Override
    protected BallsAgentPause ballPauseAgent(final Set<Ball> balls, final Point2D startPosition, final long timeInterval, final UnaryOperator<Long> calculateTime) {
        return new BallsAgentPause() {

            private Optional<Point2D> startPoint = Optional.of(startPosition);
            private Optional<Point2D> endPoint = Optional.empty();
            private final Map<Ball, PauseThread> mapping = new HashMap<>();

            @Override
            public void restart() {
                if (!this.isOver()) {
                    for (final Pause pause : mapping.values()) {
                        pause.restart();
                    }
                }
            }

            @Override
            public void pause() {
                if (!this.isOver()) {
                    for (final Pause pause : mapping.values()) {
                        pause.pause();
                    }
                }
            }

            @Override
            public boolean isPause() {
                return mapping.values().stream().allMatch(PauseThread::isPause);
            }

            @Override
            public boolean isOver() {
                return mapping.values().stream().allMatch(PauseThread::isOver);
            }

            @Override
            public synchronized void move() {
                if (!(this.isOver() || this.isPause())) {
                    balls.stream().filter(Ball::isStationary).forEach(ball -> {
                        if (!mapping.containsKey(ball)) {
                            mapping.put(ball, new PauseThread() {
                                @Override
                                public void run() {
                                    long lastUpdate = System.currentTimeMillis();
                                    while (true) {
                                        synchronized (this) {
                                            if (isPause()) {
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
                                        ball.moveByTime(calculateTime.apply(interval));
                                        lastUpdate = time;
                                        try {
                                            Thread.sleep(timeInterval);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if (ball.isStationary()) {
                                            this.over();
                                        }
                                    }
                                }
                            });
                        }
                        mapping.get(ball).start();
                    });
                }
            }

            @Override
            public synchronized void setEndPoint(final Point2D endPoint) {
                if (this.endPoint.isEmpty()) {
                    this.endPoint = Optional.of(endPoint);
                }
            }

            @Override
            public Optional<Point2D> getStartPoint() {
                return this.startPoint;
            }

            @Override
            public Optional<Point2D> getEndPoint() {
                return this.endPoint;
            }

            @Override
            public void groupBall(final Ball ball) {
                if (this.getEndPoint().isEmpty()) {
                    throw new IllegalStateException();
                }
                if (!ball.getPosition().equals(this.getEndPoint().get())) {
                    ball.setDirection(this.getEndPoint().orElseThrow(IllegalStateException::new).subtraction(ball.getPosition()));
                    ball.setDestination(this.getEndPoint().get());
                    final PauseThread pauseThread = mapping.get(ball);
                    if (pauseThread.isOver()) {
                        pauseThread.start();
                    }
                }
                if (this.areBallsStationary() && startPoint.isEmpty()) {
                    startPoint = this.getEndPoint();
                    this.endPoint = Optional.empty();
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
