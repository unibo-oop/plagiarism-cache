package model.entitiesutil;

import model.Model;
import util.Pair;

/**
 * Abstract class to manage all the movable {@link Entity}s.
 */
public abstract class EntityMovable extends EntityAbstract {
    private EntityDirection direction;

    /**
     * An {@link Entity} that has an {@link EntityDirection} and can be moved in a
     * {@link EntityDirection}.
     * 
     * @param model     is the {@link Model}
     * @param x         is the left x coordinate
     * @param y         is the top y coordinate
     * @param width     of the entity
     * @param height    if the entity
     * @param direction of the entity
     */
    public EntityMovable(final Model model, final int x, final int y, final int width, final int height,
            final EntityDirection direction) {
        super(model, x, y, width, height);
        this.direction = direction;
    }

    /**
     * @return the current direction of the {@link Entity}
     */
    public EntityDirection getDirection() {
        return this.direction;
    }

    /**
     * Update the direction the {@link Entity} must keep going toward.
     * 
     * @param direction of the entity
     */
    public void setDirection(final EntityDirection direction) {
        this.direction = direction;
    }

    /**
     * Update the position of the {@link Entity} and add it to the rechecked list.
     * 
     * @param x is the left x coordinate
     * @param y is the top y coordinate
     */
    public void setPosition(final int x, final int y) {
        this.setX(x);
        this.setY(y);
        this.getModel().getLevel().addToRecheckEntity(this);
    }

    /**
     * @param x position (from left) to set to the {@link Entity}
     */
    public void setX(final int x) {
        this.body.setPosition(new Pair<>(x, this.body.getPosition().getY()));
        this.getModel().getLevel().addToRecheckEntity(this);
    }

    /**
     * @param y position (from top) to set to the {@link Entity}
     */
    public void setY(final int y) {
        this.body.setPosition(new Pair<>(this.body.getPosition().getX(), y));
        this.getModel().getLevel().addToRecheckEntity(this);
    }

    /**
     * Do the next step (it depends on what action the {@link Entity} is executing).
     */
    public abstract void doStep();

    /**
     * Called for each game loop execution, after collision have been checked.
     */
    public abstract void doAfterCollisionStep();

}