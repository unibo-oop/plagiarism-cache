package it.unibo.bmbman.model.entities;


import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.Direction;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.model.utilities.Velocity;

/**
 * Models the general aspects of a living entity.
 *
 */
public abstract class AbstractLivingEntity extends AbstractEntity implements LivingEntity {
    private int lives;
    private Velocity velocity;
    private Direction direction;
    /**
     * Create an {@link AbstractLivingEntity}.
     * @param position where the entity is in the world
     * @param entityType the {@link EntityType} of this entity
     * @param dimension the {@link Dimension} of entity
     * @param lives the number of lives that the entity has
     */
    public AbstractLivingEntity(final Position position, final EntityType entityType, final Dimension dimension, final int lives) {
        super(position, entityType, dimension);
        this.lives = lives;
        this.direction = Direction.IDLE;
        this.velocity = Velocity.ZERO;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return this.lives > 0;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void addLife() {
        this.lives = this.lives + 1;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeLife() {
        this.lives = this.lives - 1 > 0 ? this.lives - 1 : 0; 
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getLives() {
        return this.lives;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        switch (getDirection()) {
            case IDLE:
                setVelocity(Velocity.ZERO);
                break;
            case UP:
                setVelocity(new Velocity(0, -Velocity.SPEED));
                break;
            case DOWN:
                setVelocity(new Velocity(0, Velocity.SPEED));
                break;
            case LEFT:
                setVelocity(new Velocity(-Velocity.SPEED, 0));
                break;
            case RIGHT:
                setVelocity(new Velocity(Velocity.SPEED, 0));
                break;
            default:
                break;
        }
    }
    /**
     * {@inheritDoc}
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * {@inheritDoc}
     */
    public void setVelocity(final Velocity velocity) {
        this.velocity = velocity;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Direction direction) {
        this.direction = direction;
        move();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Direction getDirection() {
        return this.direction;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.setPosition(new Position(this.getPosition().getX() + this.getVelocity().getXcomponent(), 
                this.getPosition().getY() + this.getVelocity().getYcomponent()));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove() {
        return !isAlive();
    }

}
