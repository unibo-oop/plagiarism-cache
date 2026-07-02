package tmw.model.entities;

import java.util.Timer;
import java.util.TimerTask;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.common.V2d;

/**
 * This class is the implementation of the BossEntity interface and represents
 * the boss the game.
 *
 */
public class BossEntityImpl extends AbstractGameEntity implements BossEntity {

    private static final int DEFAULT_HP_BOSS = 200;
    private static final int TIME_TO_SHOOT = 2000;
    private static final int TIME_TO_CHARGE = 8000;
    private static final double DIMENSION_PROPORTION_BOSS = 0.08;
    private static final int STANDARD_SIZE = 800;
    private static final double STANDARD_SPEED_BOSS = 2;
    private static final int SCORE = 1000;
    private static final int CONTACT_DAMAGE = 10;

    private boolean charging;
    private double normalSpeed;
    private boolean chargeReady;
    private boolean shootReady;

    /**
     * Construct a new boss.
     * 
     * @param pos       - the initial position of the boss as a {@link P2d}
     * @param vel       - the initial velocity of the boss as a {@link V2d}
     * @param fieldSize - the game resolution used to calculate the dimension of the
     *                  boss
     */
    public BossEntityImpl(final P2d pos, final V2d vel, final Dim2D fieldSize) {
        super(GameEntityType.BOSS, pos, vel, DEFAULT_HP_BOSS,
                STANDARD_SPEED_BOSS * fieldSize.getWidth() / STANDARD_SIZE,
                new Dim2D(fieldSize.getWidth() * DIMENSION_PROPORTION_BOSS,
                        fieldSize.getWidth() * DIMENSION_PROPORTION_BOSS));

        this.normalSpeed = this.getSpeed();
        this.charging = false;
        this.chargeReady = false;
        this.shootReady = false;
        this.startShooterTimer();
        this.startChargeTimer();
    }

    private synchronized void setReadyToShoot(final boolean ready) {
        this.shootReady = ready;
    }

    /**
     * This method start the timer for the reload.
     */
    private void startShooterTimer() {
        new Timer(true).schedule(new TimerTask() {

            @Override
            public void run() {
                setReadyToShoot(true);
            }

        }, TIME_TO_SHOOT);
    }

    private synchronized void setChargeReady(final boolean chargeReady) {
        this.chargeReady = chargeReady;
    }

    private synchronized boolean isChargeReady() {
        return this.chargeReady;
    }

    private void startChargeTimer() {
        new Timer(true).schedule(new TimerTask() {

            @Override
            public void run() {
                setChargeReady(true);
            }

        }, TIME_TO_CHARGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return SCORE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetDefaultDimension(final Dim2D dimension) {
        this.resizeUpdate(dimension, STANDARD_SPEED_BOSS, DIMENSION_PROPORTION_BOSS);
        this.normalSpeed = this.getSpeed();

        if (this.charging) {
            this.setSpeed(normalSpeed * 2);
        }
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
    public boolean readyForSpecialAttack() {
        return this.isChargeReady();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startSpecialAttack() {
        this.setChargeReady(false);
        this.charging = true;
        this.setChargeSpeed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopSpecialAttack() {
        this.startChargeTimer();
        this.charging = false;
        this.setNormalSpeed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUsingTheSpecialAttack() {
        return this.charging;
    }

    /**
     * Set the speed for the charge of the boss.
     */
    private void setChargeSpeed() {
        this.setSpeed(this.normalSpeed * 2);
    }

    /**
     * Set the speed to the normal value for the boss.
     */
    private void setNormalSpeed() {
        this.setSpeed(this.normalSpeed);
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
    public void shoot() {
        this.setReadyToShoot(false);
        this.startShooterTimer();
    }

}
