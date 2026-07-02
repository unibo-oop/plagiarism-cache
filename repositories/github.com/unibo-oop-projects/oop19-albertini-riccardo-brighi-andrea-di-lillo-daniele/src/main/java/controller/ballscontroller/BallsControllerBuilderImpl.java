package controller.ballscontroller;

import controller.ballscontroller.ballsagent.BallsAgentFactory;
import controller.ballscontroller.ballsagent.BallsAgentFactoryMultiThread;
import controller.ballscontroller.ballsagent.BallsAgentFactorySingleThread;
import controller.ballscontroller.ballsagent.BallsAgentPause;
import controller.ballscontroller.launch.LaunchControllerFactory;
import controller.ballscontroller.launch.LaunchControllerFactoryImpl;
import controller.ballscontroller.launch.LaunchControllerPause;
import element.Point2D;
import element.Point2DImpl;
import element.Vector2D;
import model.ball.Ball;
import model.ball.BallBuilder;
import utility.Observer;
import utility.RangeFactoryImpl;
import utility.Source;
import utility.SourceImpl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BallsControllerBuilderImpl implements BallsControllerBuilder {

    private Type type;
    private Pause pause;
    private Time time;
    private int initialBalls = 1;
    private long timeInterval = 1;
    private int stepBetweenBalls = 2;
    private BallBuilder ballsBuilder;
    private Point2D startPoint;
    private final Source<Set<? extends Ball>> ballsStop = new SourceImpl<>();
    private final LaunchControllerFactory launchControllerFactory = new LaunchControllerFactoryImpl();

    @Override
    public BallsControllerBuilder addType(final Type type) {
        this.type = type;
        return this;
    }

    @Override
    public BallsControllerBuilder addPause(final Pause pause) {
        if (Objects.isNull(this.type)) {
            throw new IllegalStateException();
        }
        this.pause = pause;
        return this;
    }

    @Override
    public BallsControllerBuilder addTime(final Time time) {
        if (Objects.isNull(this.type)) {
            throw new IllegalStateException();
        }
        this.time = time;
        return this;
    }

    @Override
    public BallsControllerBuilder addInitialBalls(final int initialBalls) {
        if (initialBalls <= 0) {
            throw new IllegalArgumentException();
        }
        this.initialBalls = initialBalls;
        return this;
    }

    @Override
    public BallsControllerBuilder addBallsBuilder(final BallBuilder ballsBuilder) {
        if (Objects.isNull(ballsBuilder)) {
            throw new IllegalStateException();
        }
        this.ballsBuilder = ballsBuilder;
        return this;
    }

    @Override
    public BallsControllerBuilder addStartPosition(final Point2D startPoint) {
        if (Objects.isNull(startPoint)) {
            throw new IllegalStateException();
        }
        this.startPoint = startPoint;
        return this;
    }

    @Override
    public BallsControllerBuilder addTimeInterval(final long timeInterval) {
        if (timeInterval <= 0) {
            throw new IllegalArgumentException();
        }
        this.timeInterval = timeInterval;
        return this;
    }

    @Override
    public BallsControllerBuilder addNumberOfStepBetweenBalls(final int stepBetweenBalls) {
        if (stepBetweenBalls < 0) {
            throw new IllegalArgumentException();
        }
        this.stepBetweenBalls = stepBetweenBalls;
        return this;
    }

    @Override
    public BallsControllerBuilder addEndLaunchObserver(final Observer<Set<? extends Ball>> endLaunch) {
        if (Objects.isNull(endLaunch)) {
            throw new IllegalArgumentException();
        }
        this.ballsStop.addObserver(endLaunch);
        return this;
    }

    @Override
    public BallsController build() {
        final Set<Ball> balls = new HashSet<>();
        BallsAgentPause ballsAgent;
        LaunchControllerPause launchController;
        BallsAgentFactory ballsAgentFactory;
        switch (type) {
            case SINGLE_THREAD:
                ballsAgentFactory = new BallsAgentFactorySingleThread();
                break;
            case MULTI_THREAD:
                ballsAgentFactory = new BallsAgentFactoryMultiThread();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.type);
        }
        switch (time) {
            case EFFECTIVE_TIME:
                switch (pause) {
                    case PAUSE:
                        ballsAgent = ballsAgentFactory.effectiveTimePauseBallAgent(balls, startPoint, timeInterval);
                        launchController = launchControllerFactory.standardThreadPauseBallLaunch(balls, ballsAgent, timeInterval * stepBetweenBalls);
                        break;
                    case UNPAUSE:
                        ballsAgent = ballsAgentFactory.effectiveBallAgent(balls, startPoint, timeInterval);
                        launchController = launchControllerFactory.standardThreadBallLaunch(balls, ballsAgent, timeInterval * stepBetweenBalls);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + this.pause);
                }
                break;
            case ABSTRACT_TIME:
                switch (pause) {
                    case PAUSE:
                        ballsAgent = ballsAgentFactory.standardPauseBallAgent(balls, startPoint, timeInterval);
                        launchController = launchControllerFactory.standardThreadPauseBallLaunch(balls, ballsAgent, timeInterval * stepBetweenBalls);
                        break;
                    case UNPAUSE:
                        ballsAgent = ballsAgentFactory.standardBallAgent(balls, startPoint, timeInterval);
                        launchController = launchControllerFactory.standardThreadBallLaunch(balls, ballsAgent, timeInterval * stepBetweenBalls);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + this.pause);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.time);
        }
        return this.thread(balls, ballsAgent, launchController);

    }

    private BallsController thread(final Set<Ball> balls, final BallsAgentPause ballsAgent, final LaunchControllerPause launchController) {
        this.ballsBuilder = ballsBuilder.addStopObserver((source, arg) -> {
            ballsAgent.setEndPoint(arg.getPosition());
            ballsAgent.groupBall(arg);
            if (ballsAgent.areBallsStationary() && ballsAgent.areBallsGrouped()) {
                ballsStop.notifyObservers(balls);
            }
        });
        for (final int i : new RangeFactoryImpl().standardRange(this.initialBalls)) {
            balls.add(this.ballsBuilder
                    .addStartPosition(ballsAgent.getStartPoint()
                            .orElseThrow(IllegalStateException::new))
                    .build());
        }
        return new BallsController() {

            @Override
            public boolean isValidLaunch(final double x, final double y) {
                final Vector2D startVector = new Point2DImpl(x, y)
                        .subtraction(ballsAgent.getStartPoint()
                                .orElseThrow(IllegalStateException::new));
                return launchController.isValidVector(startVector);
            }

            @Override
            public void launchBalls(final double x, final double y) {
                if (!this.areBallsStationary() || this.isValidLaunch(x, y)) {
                    throw new IllegalStateException();
                }
                final Vector2D startVector = new Point2DImpl(x, y)
                        .subtraction(ballsAgent.getStartPoint()
                                .orElseThrow(IllegalStateException::new));
                launchController.setVector(startVector);
                launchController.launch();
            }

            private void addBallImpl() {
                synchronized (balls) {
                    balls.add(ballsBuilder
                            .addStartPosition(ballsAgent.getStartPoint()
                                    .orElseThrow(IllegalStateException::new))
                            .build());
                }
            }

            @Override
            public void addBall() {
                if (this.areBallsStationary()) {
                    this.addBallImpl();
                } else {
                    ballsStop.addObserver(new Observer<>() {
                        @Override
                        public void update(final Source<? extends Set<? extends Ball>> source, final Set<? extends Ball> argument) {
                            addBallImpl();
                            source.removeObserver(this);
                        }
                    });
                }
            }

            @Override
            public Set<Ball> getBalls() {
                synchronized (balls) {
                    return Set.copyOf(balls);
                }
            }

            @Override
            public boolean areBallsStationary() {
                synchronized (balls) {
                    return ballsAgent.areBallsStationary();
                }
            }

            @Override
            public void pause() {
                ballsAgent.pause();
                launchController.pause();
            }

            @Override
            public void restart() {
                ballsAgent.restart();
                launchController.restart();
            }

            @Override
            public boolean isPaused() {
                return ballsAgent.isPause() || launchController.isPause();
            }
        };
    }
}
