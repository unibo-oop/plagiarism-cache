package it.unibo.pacman.model.entities;

import it.unibo.pacman.model.utilities.Direction;
import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.model.utilities.Position;
import it.unibo.pacman.model.utilities.Status;
/**
 * An implementation of {@link Mortal}.
 */
public class MortalGameObj extends MovableGameObj implements Mortal {
    private int lives;
    /**
     * Create a {@link MortalGameObj}.
     * @param width {@link SimpleGameObj}
     * @param height {@link SimpleGameObj}
     * @param position {@link SimpleGameObj}
     * @param type {@link SimpleGameObj}
     * @param direction {@link MovableGameObj}
     * @param status {@link MovableGameObj}
     * @param speed {@link MovableGameObj}
     * @param lives the lives of the entity.
     */
    protected MortalGameObj(final int width, final int height, final Position position, final EntityType type,
            final Direction direction, final Status status, final int speed, final int lives) {
        super(width, height, position, type, direction, status, speed);
        this.lives = lives;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDead() {
        return lives <= 0;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseLives() {
        lives = lives - 1;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getLives() {
        return lives;
    }
}
