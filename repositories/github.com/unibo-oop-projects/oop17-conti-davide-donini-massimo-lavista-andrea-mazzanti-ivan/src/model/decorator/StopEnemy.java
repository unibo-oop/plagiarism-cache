package model.decorator;

import model.entities.Enemy;
import model.entities.properties.VelocityImpl;

/**
 * 
 * Decoration that made an enemy stop after a certain number of milliseconds.
 *
 */
public final class StopEnemy extends EnemyDecorator {

    private static final double SCORE_MULTIPLICATOR = 1.5;

    private int timeBeforeStop;

    /**
     * 
     * @param enemy
     *           decorated.
     * @param timeBeforeStop .
     */
    public StopEnemy(final Enemy enemy, final int timeBeforeStop) {
        super(enemy);
        if (enemy instanceof KamikazeEnemy || enemy instanceof ComeBackEnemy) {
            throw new UnsupportedOperationException();
        }
        this.timeBeforeStop = timeBeforeStop;
    }

    @Override
    public void update(final int time) {
        if (this.timeBeforeStop > 0) {
            this.timeBeforeStop -= time;
            if (this.timeBeforeStop <= 0) {
                this.setVelocity(new VelocityImpl(0, 0));
            }
        }
        super.update(time);
    }

    @Override
    public double getScoreMultiplicator() {
        return SCORE_MULTIPLICATOR;
    }

}
