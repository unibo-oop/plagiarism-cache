package aoc.model.entity;

import aoc.utilities.Vector;

/**
 * Defines the base implementation for all entities.
 */
public class Entity implements EntityInterface {

    /**
     * The position in the world of the entity.
     */
    private Vector currentPosition;

    /**
     * The speed with this entity moves in the world.
     */
    private Vector speed;

    /**
     * True if the entity is still alive.
     */
    private boolean alive;

    /**
     * The type of this entity.
     */
    private final String name;

    /**
     * The base constructor for an entity.
     * 
     * @param position The position where to create the entity.
     * @param speed The initial moving speed of the entity.
     * @param name The name of the entity.
     */
    public Entity(final Vector position, final Vector speed, final String name) {
	this.setPosition(position);
	this.setSpeed(speed);
	this.alive = true;
	this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector getPosition() {
	return this.currentPosition;
    }

    /**
     * Sets the position of the entity with a given Vector.
     * 
     * @param position
     * 			The Vector representing the new position.
     */
    protected final void setPosition(final Vector position) {
        this.currentPosition = new Vector(position.getX(), position.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * Kills the entity, setting alive to false.
     */
    protected void kill() {
        this.alive = false;
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public Vector getSpeed() {
        return speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final Vector speed) {
        this.speed = new Vector(speed.getX(), speed.getY());
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.setPosition(this.currentPosition.increaseWithVector(this.speed));
    };

}
