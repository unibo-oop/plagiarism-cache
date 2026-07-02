package spacesurvival.model.gameobject.main;

import spacesurvival.model.common.P2d;
import spacesurvival.model.common.V2d;
import spacesurvival.model.gameobject.moveable.MoveableObject;
import spacesurvival.model.gameobject.moveable.movement.MovementLogic;
import spacesurvival.utilities.Delay;
import spacesurvival.utilities.ThreadUtils;
import spacesurvival.utilities.gameobject.StatusUtils;
import spacesurvival.model.EngineImage;
import spacesurvival.model.collision.bounding.BoundingBox;
import spacesurvival.model.collision.eventgenerator.EventComponent;

/**
 * A game object with main functionalities, including life, impact damege and status.
 */
public abstract class MainObject extends MoveableObject {
    private int life;
    private int impactDamage;
    private Status status;
    private int score;

    public MainObject(final EngineImage engineImage, final P2d position, final BoundingBox bb,
            final EventComponent phys, final V2d velocity, final double acceleration, final MovementLogic movementLogic, final int life,
            final int impactDamage, final int score, final P2d targetPosition) {
        super(engineImage, position, bb, phys, velocity, acceleration, movementLogic, targetPosition);
        this.life = life;
        this.impactDamage = impactDamage;
        this.status = Status.NORMAL;
        this.score = score;
    }

    /**
     * @return the current object life.
     */
    public int getLife() {
        return life;
    }

    /**
     * Sets a new life to game object.
     * 
     * @param life the quantity life to set
     */
    public void setLife(final int life) {
        this.life = life;
    }

    /**
     * @return true if object life is higher than zero, false otherwise.
     */
    public boolean isAlive() {
        return this.life > 0;
    }

    /**
     * @return true if object life is lower than zero, false otherwise.
     */
    public boolean isDead() {
        return this.life <= 0;
    }

    /**
     * Increase object life by a quantity.
     * 
     * @param heal quantity of healing
     */
    public void increaseLife(final int heal) {
        this.life += heal;
    }

    /**
     * Decrease object life by a quantity.
     * 
     * @param damage quantity of damage
     */
    public void decreaseLife(final int damage) {
        this.life -= damage;
    }

    /**
     * @return the object impact damage
     */
    public int getImpactDamage() {
        return impactDamage;
    }

    /**
     * Sets a new impact damage to the object.
     * 
     * @param impactDamage the impact damage to set
     */
    public void setImpactDamage(final int impactDamage) {
        this.impactDamage = impactDamage;
    }

    /**
     * @return the current object status.
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Sets a new status to the object and start to handle it.
     * 
     * @param status the status to set
     */
    public void setStatus(final Status status) {
        if (!this.status.equals(status)) {
            this.status = status;
            super.setEffectAnimation(status.getAnimation());
            this.onStatus();
        }
    }

    /**
     * @return true if object is invincible
     */
    public boolean isInvincible() {
        return this.status == Status.INVINCIBLE;
    }

    /**
     * Starts a thread to apply the status effect, wait for its duration and then restore status to normal.
     */
    private void onStatus() {
        switch (this.status) {
        case INVINCIBLE:
            break;
        case ON_FIRE:
            new Thread(MainObject.this::onFire).start();
            break;
        case FROZEN:
            new Thread(MainObject.this::frozen).start();
            break;
        case PARALYZED:
            new Thread(MainObject.this::paralized).start();
            break;
        default:
            break;
        }
        new Thread(() -> waitStatusDuration(status.getDuration())).start();
    }

    /**
     * Wait for the effect duration and then set the object status to normal.
     * 
     * @param milliseconds time to wait
     */
    public void waitStatusDuration(final int milliseconds) {
        ThreadUtils.sleep(milliseconds);
        this.setStatus(Status.NORMAL);
    }

    /**
     * Handle the effect caused by fire status.
     */
    public void onFire() {
        while (this.status == Status.ON_FIRE) {
            if (this.getLife() - StatusUtils.FIRE_DAMAGE > 0) {
                this.decreaseLife(StatusUtils.FIRE_DAMAGE);
            } else {
                this.setLife(0);
            }
            ThreadUtils.sleep(Delay.FIRE_EFFECT);
        }
    }

    /**
     * Handle the effect caused by frozen status.
     */
    public void frozen() {
        final V2d initialVel = this.getVelocity();
        final double initialAcc = this.getAcceleration();
        this.setVelocity(getVelocity().mul(StatusUtils.FROZEN_SLOWDOWN));
        this.setAcceleration(getAcceleration() * StatusUtils.FROZEN_SLOWDOWN);

        while (this.status == Status.FROZEN) {
            ThreadUtils.sleep(Delay.WAIT_IN_WHILE);
        }
        this.setVelocity(initialVel);
        this.setAcceleration(initialAcc);
    }

    /**
     * Handle the effect caused by paralyzed status.
     */
    public void paralized() {
        this.stopMoving();
        while (this.status == Status.PARALYZED) {
            ThreadUtils.sleep(Delay.WAIT_IN_WHILE);
        }
        this.startMoving();
    }

    /**
     * @return the score given when the object is destroyed.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score given when the object is destroyed.
     * 
     * @param score the score to set
     */
    public void setScore(final int score) {
        this.score = score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "MainObject [life=" + life + ", impactDamage=" + impactDamage + ", status=" + status + ", score=" + score
                + super.toString() + "]";
    }

}
