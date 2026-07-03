package model.decorator;

import model.entities.Enemy;
import model.entities.properties.Velocity;
import model.entities.properties.VelocityImpl;

/**
 * 
 * Decorator for enemies that comes back after a while.
 *
 */
public final class ComeBackEnemy extends EnemyDecorator {

    private static final double SCORE_MULTIPLICATOR = 2.0;

    private int timeBeforeStop;
    private int timeStopped;
    private final Velocity velocity;

    /**
     * 
     * @param enemy
     *            decorated.
     * @param timeBeforeStop
     *            .
     * @param timeStopped
     *            .
     */
    public ComeBackEnemy(final Enemy enemy, final int timeBeforeStop, final int timeStopped) {
        super(enemy);
        if (enemy instanceof KamikazeEnemy || enemy instanceof StopEnemy) {
            throw new UnsupportedOperationException();
        }
        this.timeBeforeStop = timeBeforeStop;
        this.timeStopped = timeStopped;
        this.velocity = new VelocityImpl(-enemy.getVelocity().getX(), -enemy.getVelocity().getY());
    }

    @Override
    public void update(final int time) {
        if (this.timeBeforeStop > 0) {
            this.timeBeforeStop -= time;
            if (this.timeBeforeStop <= 0) {
                this.setVelocity(new VelocityImpl(0, 0));
            }
        } else if (this.timeStopped > 0) {
            this.timeStopped -= time;
            if (this.timeStopped <= 0) {
                this.setVelocity(this.velocity);
            }
        }

        super.update(time);
    }

    @Override
    public double getScoreMultiplicator() {
        return SCORE_MULTIPLICATOR;
    }
}
