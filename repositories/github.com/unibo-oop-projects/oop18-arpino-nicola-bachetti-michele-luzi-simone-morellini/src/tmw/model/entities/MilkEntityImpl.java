package tmw.model.entities;

import java.util.Timer;
import java.util.TimerTask;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.common.V2d;

/**
 * This Class is the implementation of the MilkEntity interface.
 */
public class MilkEntityImpl extends AbstractGameEntity implements MilkEntity {

    private static final int DEFAULT_HP_MILK = 200;
    private static final int TIME_TO_SHOOT = 200;
    private static final int INVULNERABILITY_TIME = 500;
    private static final double DIMENSION_PROPORTION_MILK = 0.04;
    private static final int STANDARD_SIZE = 800;
    private static final int DEFAULT_DAMAGE = 20;
    private static final double STANDARD_SPEED_MILK = 5;

    private int reloadTime;
    private int damage;
    private double normalSpeed;
    private boolean invulnerabilty;
    private boolean shootReady;

    /**
     * Construct a new main character.
     * 
     * @param pos       - the initial position of the main character as a
     *                  {@link P2d}
     * @param vel       - the initial velocity of the main character as a
     *                  {@link V2d}
     * @param fieldSize - the game resolution used to calculate the main character's
     *                  dimension
     */
    public MilkEntityImpl(final P2d pos, final V2d vel, final Dim2D fieldSize) {
        super(GameEntityType.MILK, pos, vel, DEFAULT_HP_MILK,
                STANDARD_SPEED_MILK * fieldSize.getWidth() / STANDARD_SIZE,
                new Dim2D(fieldSize.getWidth() * DIMENSION_PROPORTION_MILK,
                        fieldSize.getWidth() * DIMENSION_PROPORTION_MILK));

        this.reloadTime = TIME_TO_SHOOT;
        this.damage = DEFAULT_DAMAGE;
        this.normalSpeed = this.getSpeed();
        this.invulnerabilty = false;
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

        }, this.reloadTime);
    }

    private synchronized void setInvulnerability(final boolean invulnerabilty) {
        this.invulnerabilty = invulnerabilty;
    }

    private synchronized boolean isInvulnerable() {
        return this.invulnerabilty;
    }

    private void startInvulnerabilityTimer() {
        new Timer(true).schedule(new TimerTask() {

            @Override
            public void run() {
                setInvulnerability(false);
            }

        }, INVULNERABILITY_TIME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetDefaultDimension(final Dim2D dimension) {
        this.resizeUpdate(dimension, STANDARD_SPEED_MILK, DIMENSION_PROPORTION_MILK);
        this.normalSpeed = this.getSpeed();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void takeDamage(final int damage) {
        if (!this.isInvulnerable()) {
            this.setInvulnerability(true);
            this.startInvulnerabilityTimer();
            super.takeDamage(damage);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isThisTheEnd() {
        return !this.isAlive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxHp() {
        return DEFAULT_HP_MILK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void heal(final int amount) {
        if (this.getCurrentHealth() + amount > DEFAULT_HP_MILK) {
            this.setHp(DEFAULT_HP_MILK);
        } else {
            this.setHp(this.getCurrentHealth() + amount);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDefaultTimeForReload() {
        return TIME_TO_SHOOT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefaultTimeForReload() {
        this.reloadTime = TIME_TO_SHOOT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTimeForReload() {
        return this.reloadTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTimeForReload(final int timeForReload) {
        this.reloadTime = timeForReload;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDefaultDamage() {
        return DEFAULT_DAMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefaultDamage() {
        this.damage = DEFAULT_DAMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDamage(final int newDamage) {
        this.damage = newDamage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDefaultSpeed() {
        return this.normalSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefaultSpeed() {
        this.setSpeed(this.normalSpeed);
    }
}
