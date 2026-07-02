package clashclass.ai.pathfinding;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;

import java.util.Optional;

/**
 * Represents a node used for pathfinding.
 */
public interface PathNode {
    /**
     * Gets the x component of the node.
     *
     * @return the x component of the node
     */
    int getX();

    /**
     * Gets the y component of the node.
     *
     * @return the y component of the node
     */
    int getY();

    /**
     * Gets the position vector of the node.
     *
     * @return the position vector with components (x,y)
     */
    VectorInt2D getPosition();

    /**
     * Gets the cost of the node.
     *
     * @return the cost of the node
     */
    float getCost();

    /**
     * Gets the GameObject associated with this node, if any.
     *
     * @return the GameObject associated, if present, otherwise an empty Optional
     */
    Optional<GameObject> getRefGameObject();
}
