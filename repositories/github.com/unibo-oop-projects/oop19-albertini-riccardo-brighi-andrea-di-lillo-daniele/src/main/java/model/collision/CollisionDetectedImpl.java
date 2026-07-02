package model.collision;

import model.block.Block;
import element.Point2D;
import element.Vector2D;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Class that permit to return multiple arguments at the same time in a method
 */
public class CollisionDetectedImpl implements CollisionDetected{
    private Triple<Optional<List<Block>>, Optional<Vector2D>, Optional<Point2D>> collisionElements;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollisionDetectedImpl that = (CollisionDetectedImpl) o;
        return Objects.equals(collisionElements, that.collisionElements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collisionElements);
    }

    /**
     * Constructor of CollisionDetectedImpl
     *
     * @param blocks list of blocks that the bal hit.
     * @param newDirection new direction of the ball.
     * @param newCenterPosition new position of the ball (where was the collision).
     */
    public CollisionDetectedImpl(final List<Block> blocks, final Vector2D newDirection, final Point2D newCenterPosition){
        collisionElements = new  ImmutableTriple<>(Optional.ofNullable(blocks), Optional.ofNullable(newDirection),
                                                    Optional.ofNullable(newCenterPosition));
    }

    /**
     * Constructor of CollisionDetectedImpl with null block
     *
     * @param newDirection new direction of the ball.
     * @param newCenterPosition new position of the ball (where was the collision).
     */
    public CollisionDetectedImpl(final Vector2D newDirection, final Point2D newCenterPosition){
        this(null, newDirection, newCenterPosition);
    }

    /**
     * Constructor of CollisionDetectedImpl with null block and null direction
     *
     * @param newCenterPosition
     */
    public CollisionDetectedImpl(final Point2D newCenterPosition){
        this(null, null, newCenterPosition);
    }

    @Override
    public Optional<List<Block>> getBlocks() {
        return collisionElements.getLeft();
    }

    @Override
    public Optional<Vector2D> getNewDirection() {
        return collisionElements.getMiddle();
    }

    @Override
    public Optional<Point2D> getNewCenterPosition() {
        return collisionElements.getRight();
    }
}
