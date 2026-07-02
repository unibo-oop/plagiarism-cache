package it.unibo.oop.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Optional;

import it.unibo.oop.utilities.Position;

/**
 * This abstract class shapes the standard {@link Entity} of the game. Every
 * {@link Entity} has got its own {@link Position}, and its own Environment
 * where it's placed.
 */
public abstract class AbstractEntity implements Entity {

    private Position entityPosition;
    private Optional<GameStateImpl> gameEnvironment = Optional.ofNullable(GameStateImpl.getInstance());
    private boolean deathFlag;

    /**
     * Constructor with a position where to place the {@link AbstractEntity}
     * 
     * @param startingX
     *            Starting X position
     * @param startingY
     *            Starting Y position
     */
    public AbstractEntity(final double startingX, final double startingY) {
        this.entityPosition = new Position(startingX, startingY);
        this.deathFlag = false;
    }

    /**
     * Getter for the {@link Position} of an {@link Entity}.
     * @return The {@link Entity} {@link Position}
     */
    public Position getPosition() {
        return this.entityPosition;
    }

    /**
     * Setter for the {@link Position} of an {@link Entity}.
     * 
     * @param newPosition the position to set
     */
    public void setPosition(final Position newPosition) {
        this.entityPosition = newPosition;
    }

    /**
     * Getter for the death flag
     * @return True if the {@link AbstractEnemy} is dead
     */
    public boolean isDead() {
        return this.deathFlag;
    }

    /**
     * Sets the {@link AbstractEntity} dead
     */
    public void killEntity() {
        this.deathFlag = true;
    }

    /**
     * Getter for the X position of an {@link Entity}.
     * 
     * @return The x position
     */
    public double getX() {
        return entityPosition.getX();
    }

    /**
     * Getter for the Y position of an {@link Entity}.
     * 
     * @return The Y position
     */
    public double getY() {
        return entityPosition.getY();
    }

    /**
     * The top left corner of the {@link Entity}
     * 
     * @return The top left position
     */
    public Position getTopLeftPos() {
        return new Position(entityPosition.getIntX() - this.getEntityWidth() / 2,
                entityPosition.getIntY() - this.getEntityHeight() / 2);
    }

    /**
     * Generates a {@link Rectangle} that delimits the current {@link Entity}.
     * 
     * @return a rectangle containing, in the center, the {@link Entity}
     */
    public Rectangle getBounds() {
        final int tmpWidth = this.getEntityWidth();
        final int tmpHeight = this.getEntityHeight();
        final Dimension tmpDim = new Dimension(tmpWidth, tmpHeight);
        final Point topLeftCorner = new Point(this.getTopLeftPos().getIntX(), this.getTopLeftPos().getIntY());
        return new Rectangle(topLeftCorner, tmpDim);
    }

    /**
     * Returns true if this entity intersecates another one passed as parameter.
     * 
     * @return If the bounds intersects the other {@link Entity} bounds
     */
    public boolean intersecate(final Entity secondEntity) {
        return this.getBounds().intersects(secondEntity.getBounds());
    }

    /**
     * Getter for the shape Height of the current Object.
     * 
     * @return The entity height
     */
    protected abstract int getEntityHeight();

    /**
     * Getter for the shape Width of the current Object.
     * 
     * @return The {@link Entity} width
     */
    protected abstract int getEntityWidth();

    /**
     * Attaches another environment to the current {@link Entity}.
     * 
     * @param newEnvironment The envirnment to attach
     */
    public void attachEnvironment(final GameStateImpl newEnvironment) {
        this.gameEnvironment = Optional.of(newEnvironment);
    }

    /**
     * Removes the environment if it's necessary.
     */
    public void removeEnvironment() {
        if (this.hasEnvironment()) {
            this.gameEnvironment = Optional.empty();
        }
    }

    /**
     * Gets the Environment presence
     * @return true if the {@link Entity} has got an environment.
     */
    public boolean hasEnvironment() {
        return this.gameEnvironment.isPresent();
    }

    /**
     * Gets the environment
     * @return the environment of an {@link Entity} as an object to manipulate.
     */
    public GameStateImpl getEnvironment() {
        return this.gameEnvironment.get();
    }
}