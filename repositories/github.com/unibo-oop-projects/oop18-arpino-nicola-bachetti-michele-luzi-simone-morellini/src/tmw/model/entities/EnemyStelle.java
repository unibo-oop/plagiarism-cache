package tmw.model.entities;

import java.util.Timer;
import java.util.TimerTask;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.common.V2d;

/**
 * This Class represents the enemy Stelle; this particular entity doesn't move
 * and only thing he does is shooting against the main character.
 */
public class EnemyStelle extends AbstractGameEntity implements Enemy {

    private static final int DEFAULT_HP_STELLE = 40;
    private static final int TIME_TO_SHOOT = 2000;
    private static final double DIMENSION_PROPORTION_STELLE = 0.04;
    private static final int STANDARD_SIZE = 800;
    private static final int SCORE = 100;
    private static final double STANDARD_SPEED_STELLE = 0;
    private static final int CONTACT_DAMAGE = 20;

    private boolean shootReady;

    /**
     * Construct a new enemy Stelle.
     * 
     * @param pos       - the initial position of the enemy as a {@link P2d}
     * @param vel       - the initial velocity of the enemy as a {@link V2d}
     * @param fieldSize - the game resolution used to calculate the enemy's
     *                  dimension
     */
    public EnemyStelle(final P2d pos, final V2d vel, final Dim2D fieldSize) {
        super(GameEntityType.STELLA, pos, vel, DEFAULT_HP_STELLE,
                STANDARD_SPEED_STELLE * fieldSize.getWidth() / STANDARD_SIZE,
                new Dim2D(fieldSize.getWidth() * DIMENSION_PROPORTION_STELLE,
                        fieldSize.getWidth() * DIMENSION_PROPORTION_STELLE));

        this.shootReady = false;
        this.startShooterTimer();
    }

    private synchronized void setReadyToShoot(final boolean ready) {
        this.shootReady = ready;
    }

    private void startShooterTimer() {
        new Timer(true).schedule(new TimerTask() {

            @Override
            public void run() {
                setReadyToShoot(true);
            }

        }, TIME_TO_SHOOT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getScore() {
        return SCORE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetDefaultDimension(final Dim2D dimension) {
        this.resizeUpdate(dimension, STANDARD_SPEED_STELLE, DIMENSION_PROPORTION_STELLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getContactDamage() {
        return CONTACT_DAMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean readyToShoot() {
        return this.shootReady;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void shoot() {
        this.setReadyToShoot(false);
        this.startShooterTimer();
    }

}
