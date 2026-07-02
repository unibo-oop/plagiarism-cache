package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.Constants;
import it.unibo.scat.common.EntityState;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.common.Position;

/**
 * This class represents an abstract entity with basic properties such as
 * health, status and position.
 */
public abstract class AbstractEntity implements EntityState {
    /** Default points value. */
    private boolean alive;
    private int health;
    private final int startingHealth;
    private final int width;
    private final int height;
    private Position position;
    private final Position startingPosition;
    private final EntityType entityType;

    /**
     * Constructs a new entity with the specified properties.
     * 
     * @param type   the type of the entity.
     * @param x      the initial x coordinate.
     * @param y      the initial y coordinate.
     * @param width  the witdh of the entity.
     * @param height the height of the entity.
     * @param health the initial health of the entity.
     */
    public AbstractEntity(final EntityType type, final int x, final int y, final int width, final int height,
            final int health) {
        alive = true;
        entityType = type;
        startingPosition = new Position(x, y);
        position = new Position(x, y);
        this.width = width;
        this.height = height;
        startingHealth = health;
        this.health = health;
    }

    /**
     * Reduces health, then if health is equal to 0, the entity that got hit dies.
     * Each one of the dead invaders return the appropiate amount of points
     * 
     * @return number of points based on the type of entity that died.
     */
    public int onHit() {
        decreaseHealth();
        if (health == Constants.ZERO) {
            die();
        }

        return getEntityPoints();
    }

    /**
     * @return the points given to the player when entity is killed.
     */
    public int getEntityPoints() {
        return Constants.ZERO;
    }

    /**
     * Sets the position of the entity.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public void setPosition(final int x, final int y) {
        position = new Position(x, y);
    }

    /**
     * Sets alive to true.
     */
    private void setAlive() {
        alive = true;
    }

    /**
     * Decreases health by 1.
     */
    private void decreaseHealth() {
        health--;
    }

    /**
     * Sets alive to false.
     */
    private void die() {
        alive = false;
    }

    /**
     * Resets the entity to its initial state.
     * Calls the internal reset methods for health and position.
     */
    public void reset() {
        resetHealth();
        resetStartingPosition();
    }

    /**
     * Resets the health to the starting value and marks the entity as alive.
     */
    private void resetHealth() {
        health = startingHealth;
        setAlive();
    }

    /**
     * Resets the position to the starting position.
     */
    private void resetStartingPosition() {
        position = startingPosition;
    }

    /**
     * @return the current position of the entity
     * 
     */
    @Override
    public Position getPosition() {
        return new Position(position.getX(), position.getY());
    }

    /**
     * Entity type getter.
     * 
     * @return the entity type.
     */
    @Override
    public EntityType getType() {
        return entityType;
    }

    /**
     * Alive boolean getter.
     * 
     * @return alive status.
     */
    @Override
    public boolean isAlive() {
        return alive;
    }

    /**
     * Width getter.
     * 
     * @return the width.
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * @return the height of the entity.
     * 
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * @return health of the entity
     * 
     */
    @Override
    public int getHealth() {
        return health;
    }
}
