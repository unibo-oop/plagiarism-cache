package controller.ballscontroller.launch;

import controller.ballscontroller.ballsagent.BallsAgentPause;
import element.Vector2D;
import model.ball.Ball;

import java.util.Optional;
import java.util.Set;

public class LaunchControllerFactoryImpl implements LaunchControllerFactory {

    @Override
    public LaunchController standardBallLaunch(final Set<Ball> balls, final double movementInterval) {
        return new LaunchController() {

            private Optional<Vector2D> direction = Optional.empty();

            @Override
            public boolean isValidVector(final Vector2D vector2D) {
                final double min = 20;
                final double max = 180 - min;
                return vector2D.getDegreesAngle() > min && vector2D.getDegreesAngle() < max;
            }

            @Override
            public void setVector(final Vector2D direction) {
                if (direction.isNullVector() || direction.getYComponent() <= 0 || !this.isValidVector(direction)) {
                    throw new IllegalArgumentException();
                }
                this.direction = Optional.of(direction);
            }

            @Override
            public void launch() {
                if (direction.isEmpty()) {
                    throw new IllegalStateException();
                }
                for (final Ball ball : balls) {
                    if (ball.isStationary()) {
                        ball.setDirection(direction.get());
                        balls.stream().filter(i -> !i.isStationary()).forEach(i -> i.moveByDistance(movementInterval));
                    }
                }
            }
        };
    }

    @Override
    public LaunchControllerPause standardThreadBallLaunch(final Set<Ball> balls, final BallsAgentPause ballsAgent, final long timeInterval) {
        final LaunchControllerPause launchController = standardThreadPauseBallLaunch(balls, ballsAgent, timeInterval);
        return new LaunchControllerThreadNoPause(launchController);
    }

    @Override
    public LaunchControllerPause standardThreadPauseBallLaunch(final Set<Ball> balls, final BallsAgentPause ballsAgent, final long timeInterval) {
        return new LaunchControllerThread() {

            private boolean fistGone;

            @Override
            public void start() {
                this.fistGone = false;
                super.start();
            }

            @Override
            public void run() {
                while (true) {
                    for (final Ball ball : balls) {
                        synchronized (this) {
                            if (this.isPause()) {
                                try {
                                    this.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (ball.isStationary()) {
                            ball.setDirection(this.getDirection().orElseThrow(IllegalStateException::new));
                            if (!this.fistGone) {
                                this.fistGone = true;
                                ballsAgent.move();
                            }
                            try {
                                Thread.sleep(timeInterval);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    this.launchOver();
                }
            }
        };
    }
}
