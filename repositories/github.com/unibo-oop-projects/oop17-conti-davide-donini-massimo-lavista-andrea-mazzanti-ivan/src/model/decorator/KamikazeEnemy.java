package model.decorator;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.entities.Enemy;
import model.entities.Spaceship;
import model.entities.properties.PositionImpl;
import model.entities.properties.VelocityImpl;
import model.utilities.StaticVelocity;

/**
 * 
 * A passive enemy who will try to collide with the spaceship.
 *
 */
public final class KamikazeEnemy extends EnemyDecorator {

    private static final int KAMIKAZE_SPEED = 708;
    private static final double SCORE_MULTIPLICATOR = 2.5;

    private static final int TIME_BEFORE_STOP = 500;
    private static final int TIME_BEFORE_KAMIKAZE = 500;
    private boolean kamikazed;
    private boolean stopped;
    private final Spaceship spaceship;
    private int time;

    /**
     * 
     * @param enemy
     *            decorated.
     * @param spaceship
     *            used to get the position where the kamikaze will go.
     */
    public KamikazeEnemy(final Enemy enemy, final Spaceship spaceship) {
        super(enemy);
        if (enemy instanceof StopEnemy || enemy instanceof ComeBackEnemy) {
            throw new UnsupportedOperationException();
        }
        this.kamikazed = false;
        this.stopped = false;
        this.spaceship = spaceship;
        this.time = 0;
    }

    @Override
    public void update(final int elapsed) {
        super.update(elapsed);

        if (!this.kamikazed) {
            this.time += elapsed;
            if (this.time > TIME_BEFORE_STOP && !(this.stopped)) {
                this.stopped = true;
                super.setVelocity(new VelocityImpl(0, 0));
            }
            if (this.time > TIME_BEFORE_STOP + TIME_BEFORE_KAMIKAZE) {
                this.kamikazed = true;
                if (super.getShape() instanceof Circle) {
                    super.setVelocity(StaticVelocity.setAbsoluteSpeed(this.spaceship.getPosition().sub(super.getPosition()),
                            KAMIKAZE_SPEED));
                } else if (super.getShape() instanceof Rectangle) {
                    super.setVelocity(StaticVelocity.setAbsoluteSpeed(
                            this.spaceship.getPosition()
                                    .sub(new PositionImpl(
                                            super.getPosition().getX() + ((Rectangle) super.getShape()).getWidth() / 2,
                                            super.getPosition().getY()
                                                    + ((Rectangle) super.getShape()).getHeight() / 2)),
                                    KAMIKAZE_SPEED));
                }
            }
        }
    }

    @Override
    public double getScoreMultiplicator() {
        return SCORE_MULTIPLICATOR;
    }
}
