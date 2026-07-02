package model.balllaucher;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.geometry.Vector2;

import model.ball.Ball;
import model.world.WorldSettings;

/**
 * Implementation of {@link BallLauncher}.
 */
public class BallLauncherImpl implements BallLauncher {

    private static final double LAUNCHER_WIDTH  = WorldSettings.WORLD_HEIGHT * 0.03;
    private static final double LAUNCHER_HEIGHT = WorldSettings.WORLD_HEIGHT * 0.08;

    private static final double LAUNCH_IMPULSE = WorldSettings.WORLD_HEIGHT * 0.3;

    private Ball ball;
    private Pair<Double, Double> position;

    /**
     * Default constructor.
     * Create a launcher with default width and height that points directly down.
     */
    public BallLauncherImpl() {
        this.position = Pair.of(0., WorldSettings.WORLD_HEIGHT - LAUNCHER_HEIGHT);
    }

    @Override
    public final void update(final Pair<Double, Double> mousePosition) {
        if (mousePosition.getLeft().compareTo(0.) != 0) {
            final double angle = Math.atan2(mousePosition.getRight(), mousePosition.getLeft());
            final double newPositionY = WorldSettings.WORLD_HEIGHT - LAUNCHER_HEIGHT * Math.sin(angle);
            final double newPositionX = LAUNCHER_HEIGHT * Math.cos(angle);
            final Pair<Double, Double> newPosition = Pair.of(newPositionX, newPositionY);
            this.position = newPosition;
        }
        if (this.ball != null && this.ball.isReadyToLaunch()) {
            this.ball.move(this.position);
        }
    }

    @Override
    public final void load(final Ball b) {
        this.ball = b;
        this.ball.move(this.position);
        this.ball.setReadyToLaunch(true);
    }

    @Override
    public final void launch() {
        if (this.ball != null && this.ball.isReadyToLaunch()) {
            this.ball.setReadyToLaunch(false);
            final Vector2 impulse = new Vector2(this.position.getLeft(), this.position.getRight() - WorldSettings.WORLD_HEIGHT);
            impulse.setMagnitude(LAUNCH_IMPULSE);
            this.ball.launch(impulse);
        }
    }

    @Override
    public final Pair<Double, Double> getPosition() {
        return this.position;
    }

    @Override
    public final double getWidth() {
        return LAUNCHER_WIDTH;
    }

    @Override
    public final double getHeight() {
        return LAUNCHER_HEIGHT;
    }

}
