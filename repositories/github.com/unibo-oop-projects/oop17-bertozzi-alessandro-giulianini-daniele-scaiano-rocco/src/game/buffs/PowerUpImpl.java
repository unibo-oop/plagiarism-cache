package game.buffs;

import java.awt.Rectangle;

import controller.GameLoop;
import game.GameImpl;
import game.GameObject;
import game.ID;
import game.Player;
import game.TimedEntity;
import utilities.Pair;

/**
 * This class implements the basic behavior of a powerUp.
 */
public abstract class PowerUpImpl extends TimedEntity implements PowerUp {

    /**
     * The standard lifetime of a powerUp.
     */
    public static final int POWER_UP_LIFETIME = GameLoop.FPS * 6;
    /**
     * Width of a powerUp.
     */
    public static final int WIDTH = 60;
    /**
     * Height of a powerUp.
     */
    public static final int HEIGHT = 60;

    private final PowerUpType type;
    private boolean activated;
    private Player player;

    /**
     * @param id id of this powerUp
     * @param position position of this powerUp
     * @param velocityX the velocity along the x axis
     * @param velocityY the velocity along the y axis
     * @param type the kind of buff this is

     */
    public PowerUpImpl(final ID id, final Pair<Integer, Integer> position, final int velocityX, final int velocityY, final PowerUpType type) {
        super(position, velocityX, velocityY, id, type.getLifetime());
        this.type = type;
        this.activated = false;
        this.setHitbox(new Rectangle(this.getPosition().getX() - WIDTH / 2, this.getPosition().getY() - HEIGHT / 2, WIDTH, HEIGHT));
    }

    @Override
    public final void update() {
        if (!this.activated) {
            this.getPosition().setX(this.getPosition().getX() + this.getVelocity().getX());
            this.getPosition().setY(this.getPosition().getY() + this.getVelocity().getY());
            this.setHitbox(new Rectangle(this.getPosition().getX() - WIDTH / 2, this.getPosition().getY() - HEIGHT / 2, WIDTH, HEIGHT));
            this.bounce();
        } else {
            this.setState();
            if (!this.isEnded()) {
                this.tick();
                this.applyBuff();
            } else {
                this.reset();
                this.setDead();
            }
        }
    }

    /**
     * Sets the position and the hitbox of the powerUp after it's activation.
     * It's protected because no-one from outside the package should be able to modify the position nor
     * the hitbox of the powerUp
     */
    protected abstract void setState();

    @Override
    public abstract void reset();

    /**
     * This method makes the powerUp bounce when it hits a border.
     * The code was taken from {@link BouncingObstacle}.
     */
    private void bounce() {
        if (this.getPosition().getX() + this.getHitbox().getWidth() / 2 > GameImpl.GAMEAREA_WIDTH
                || this.getPosition().getX() - this.getHitbox().getWidth() / 2 <= 0) {
            this.setVelocity(-this.getVelocity().getX(), this.getVelocity().getY());
        }
        if (this.getPosition().getY() + this.getHitbox().getHeight() / 2 > GameImpl.GAMEAREA_HEIGHT
                || this.getPosition().getY() - this.getHitbox().getHeight() / 2 <= 0) {
            this.setVelocity(this.getVelocity().getX(), -this.getVelocity().getY());
        }
    }

    /**{@link game.buffs.PowerUp#applyBuff()}. */
    @Override
    public void applyBuff() {
        if (!this.activated || this.getType().isRequiringUpdate()) {
            this.applyEffect();
            this.activated = true;
        }
    }

    /**
     * Applies the effect of this powerUp.
     */
    protected abstract void applyEffect();

    /**{@link game.Entity#collide(GameObject entity)}.*/
    @Override
    public void collide(final GameObject entity) {
        if (entity instanceof Player) {
            this.player = (Player) entity;
            this.applyBuff();
            this.setPosition(entity.getPosition());
            this.setHitbox(entity.getHitbox());
        }
    }

    /**
     * @return the type of this powerUp
     */
    public final PowerUpType getType() {
        return this.type;
    }

    /**
     * @return the entity that got this powerUp
     */
    public final Player getEntityToBuff() {
        return this.player;
    }

    @Override
    public final boolean isActivated() {
        return this.activated;
    }
}
