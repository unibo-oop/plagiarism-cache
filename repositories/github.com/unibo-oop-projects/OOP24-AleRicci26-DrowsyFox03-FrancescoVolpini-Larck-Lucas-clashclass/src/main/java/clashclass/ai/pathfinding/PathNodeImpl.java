package clashclass.ai.pathfinding;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;

import java.util.Optional;

/**
 * Represents an implementation of a pathfinding Node.
 */
public class PathNodeImpl implements PathNode {
    private final VectorInt2D position;
    private final float cost;
    private final Optional<GameObject> refGameObject;

    /**
     * Constructs the node.
     *
     * @param position the position of the node
     * @param cost the cost of the node
     * @param refGameObject the GameObject associated to the node, if any
     */
    public PathNodeImpl(final VectorInt2D position, final float cost, final Optional<GameObject> refGameObject) {
        this.position = position;
        this.cost = cost;
        this.refGameObject = refGameObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return this.position.x();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return this.position.y();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VectorInt2D getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getCost() {
        return this.cost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameObject> getRefGameObject() {
        return this.refGameObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "(" + this.position.x() + "," + this.position.y() + ")";
    }
}
