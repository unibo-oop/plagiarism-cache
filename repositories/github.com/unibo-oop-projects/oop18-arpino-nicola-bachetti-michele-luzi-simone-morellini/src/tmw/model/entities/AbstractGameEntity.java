package tmw.model.entities;

import java.util.Optional;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.common.V2d;
import tmw.model.objects.BaseGameObject;

/**
 * This Class represents a skeletal for every implementation of an entity and
 * gives an implementation of every method GameEntity, except for update, shoot
 * and readyToShoot because they differ from entity to entity.
 * 
 */
public abstract class AbstractGameEntity extends BaseGameObject implements GameEntity {

    private static final int STANDARD_SIZE = 800;

    private final GameEntityType type;
    private V2d vel;
    private double speed;
    private int hp;

    /**
     * Base Constructor for every entity.
     *
     * @param type      - The GameEntityType value that identify the entity
     * @param pos       - The initial position of the entity as a {@link P2d}
     * @param vel       - The initial velocity of the entity as a {@link V2d}
     * @param hp        - The health of the entity
     * @param speed     - the speed of the entity
     * @param dimension - the dimension of the entity as a {@link Dim2D}
     */
    public AbstractGameEntity(final GameEntityType type, final P2d pos, final V2d vel, final int hp, final double speed,
            final Dim2D dimension) {
        super(pos, dimension);
        this.type = type;
        this.vel = vel;
        this.hp = hp;
        this.speed = speed;
    }

    /**
     * This method should be called when the game is resized to resize the entity
     * itself.
     * 
     * @param newDimension        - the new size of the game
     * @param speedMultiplier     - the multiplier for the speed
     * @param dimensionMultiplier - the multiplier for the dimension
     */
    protected void resizeUpdate(final Dim2D newDimension, final double speedMultiplier,
            final double dimensionMultiplier) {
        this.setDimension(new Dim2D(dimensionMultiplier * newDimension.getWidth(),
                dimensionMultiplier * newDimension.getWidth()));
        this.setSpeed((speedMultiplier * newDimension.getWidth()) / STANDARD_SIZE);
        this.setPos(new P2d(this.getCurrentPos().getX() * newDimension.getWidth() / STANDARD_SIZE,
                this.getCurrentPos().getY() * newDimension.getWidth() / STANDARD_SIZE));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameEntityType getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V2d getCurrentVel() {
        return new V2d(this.vel.getX(), this.vel.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVel(final V2d vel) {
        this.vel = new V2d(vel.getX(), vel.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final double newSpeed) {
        this.speed = newSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return this.hp > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentHealth() {
        return this.hp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHp(final int hp) {
        this.hp = hp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void takeDamage(final int damage) {
        if (this.hp - damage < 0) {
            this.hp = 0;
        } else {
            this.hp -= damage;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        this.hp = 0;
    }

    /**
     * Update the position of the entity.
     */
    @Override
    public void update(final Optional<P2d> newPostion) {
        if (newPostion.isPresent()) {
            this.setPos(newPostion.get());
        }
    }

    /**
     * {@inheritDoc}
     */
    public abstract boolean readyToShoot();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void shoot();
}
