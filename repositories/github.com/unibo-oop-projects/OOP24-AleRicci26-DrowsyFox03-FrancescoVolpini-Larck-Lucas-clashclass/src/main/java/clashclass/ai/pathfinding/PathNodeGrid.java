package clashclass.ai.pathfinding;

import clashclass.commons.VectorInt2D;

import java.util.Set;

/**
 * Represents a grid that contains the PathNodes.
 */
public interface PathNodeGrid {
    /**
     * Gets the node at the given position.
     *
     * @param x the x component of the position
     * @param y the y component of the position
     *
     * @return the PathNode at the given position
     */
    PathNode getNode(int x, int y);

    /**
     * Gets all the nodes in the grid.
     *
     * @return all the nodes int the grid
     */
    Set<PathNode> getNodes();

    /**
     * Gets all the neighbors of a given node.
     *
     * @param node the given node
     *
     * @return the neighbors of the given node
     */
    Set<PathNode> getNeighborsOfNode(PathNode node);

    /**
     * Returns a Set of neighbors nodes' positions.
     *
     * @param node the node
     *
     * @return the neighbors nodes' positions
     */
    Set<VectorInt2D> getNeighborsPositionsOfNode(PathNode node);

    /**
     * Removes a node at a given position.
     *
     * @param position the given position
     * @param width the width
     * @param height the height
     */
    void removeAtPosition(VectorInt2D position, int width, int height);
}
