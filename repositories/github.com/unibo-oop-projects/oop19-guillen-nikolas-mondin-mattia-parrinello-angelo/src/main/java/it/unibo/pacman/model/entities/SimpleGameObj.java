package it.unibo.pacman.model.entities;

import java.awt.Rectangle;

import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.model.utilities.Position;
/**
 * An implementation of {@link Entity}.
 */
public class SimpleGameObj implements Entity {
    private final int width;
    private final int height;
    private Position position;
    private final EntityType type;
    /**
     * Construct an implementation of {@link Entity}.
     * 
     * @param width    the width of the entity.
     * @param height   the height of the entity.
     * @param position the position of the entity.
     * @param type     the type of the entity.
     */
    protected SimpleGameObj(final int width, final int height, final Position position, final EntityType type) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.type = type;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(position.getX(), position.getY(), width, height);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return position;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return type;
    }
    /**
     * An implementation of the setter for the classes that needs to extend
     * SimpleGameObj.
     * 
     * @param newPosition the new position to set
     */
    public void setPosition(final Position newPosition) {
        position = newPosition;
    }
    /**
     * An implementation of the getter for the classes that needs to extend
     * SimpleGameObj.
     * 
     * @return the width of the entity
     */
    public int getWidth() {
        return width;
    }
    /**
     * An implementation of the getter for the classes that needs to extend
     * SimpleGameObj.
     * 
     * @return the height of the entity
     */
    public int getHeight() {
        return height;
    }
}
