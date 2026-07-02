package model.entitiesutil;

import java.util.Objects;

import model.Model;
import model.physics.CollisionDirection;
import util.Pair;

/**
 * Abstract class to manage all {@link Entity}s.
 */
public abstract class EntityAbstract implements Entity {

    private final Model model;
    private boolean visible = true;
    protected final Body body;

    /**
     * Any element inside the game.
     * 
     * @param model     is the {@link Model}
     * @param x         is the left x coordinate
     * @param y         is the top y coordinate
     * @param width     of the entity
     * @param height    if the entity
     */
    public EntityAbstract(final Model model, final int x, final int y, final int width, final int height) {
        this.model = Objects.requireNonNull(model);
        this.body = new Body(new Pair<>(x, y), width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        this.visible = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisible() {
        return this.visible;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.body.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.body.getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.body.getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return this.body.getPosition().getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return this.body.getPosition().getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Model getModel() {
        return this.model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void isTouchedBy(Entity entity, CollisionDirection collisionDirection);
}
