package controller.ballscontroller.launch;

import controller.ballscontroller.PauseThread;
import element.Vector2D;

import java.util.Optional;

public class LaunchControllerThread extends PauseThread implements LaunchControllerPause {
    private Optional<Vector2D> direction = Optional.empty();

    @Override
    public boolean isValidVector(final Vector2D vector2D) {
        final double min = 20;
        final double max = 180 - min;
        return vector2D.getDegreesAngle() > min && vector2D.getDegreesAngle() < max;
    }

    @Override
    public void setVector(final Vector2D direction) {
        if (direction.isNullVector() || direction.getYComponent() <= 0 || this.isValidVector(direction)) {
            throw new IllegalArgumentException();
        }
        this.direction = Optional.of(direction);
    }

    /**
     * @return the actual vector of the launch if presents or else an optional empty
     */
    protected Optional<Vector2D> getDirection() {
        return direction;
    }

    @Override
    public void launch() {
        this.start();
    }

    @Override
    public void start() {
        if (this.direction.isEmpty()) {
            throw new IllegalStateException();
        }
        super.start();
    }

    /**
     * set the end of the launch
     */
    protected void launchOver() {
        this.direction = Optional.empty();
        this.over();
    }
}
