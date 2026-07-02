package it.unibo.arkanoid.subject;

import it.unibo.arkanoid.event.CollisionInfo;
import it.unibo.arkanoid.model.Level;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * This class represents a rectangle that is not overable by the {@link Ball}, in the
 * edges of the field in the north, east and west positions.
 *
 */
public class Block extends AbstractSubject {

    /**
     * Constructor for Block component, with no velocity.
     * 
     * @param x
     *          The x coordinate in the Game Field.
     * @param y
     *          The y coordinate in the Game Field.
     * @param width
     *             The Width of the Block.
     * @param height
     *              The Height of the Block.
     * @param level
     *             The current level where the Block lives.
     */
    public Block(final double x, final double y, final double width, final double height, final Level level) {
        super(x, y, width, height, new Vector2D(0, 0), level);
    }

    @Override
    protected void handleCollision(final CollisionInfo collision) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubjectType getSubjectType() {
        return SubjectType.BLOCK;
    }

}
