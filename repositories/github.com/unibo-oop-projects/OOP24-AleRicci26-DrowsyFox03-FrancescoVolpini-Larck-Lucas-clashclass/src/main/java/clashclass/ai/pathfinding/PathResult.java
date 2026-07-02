package clashclass.ai.pathfinding;

import java.util.List;

/**
 * Represents a Path Result wrapper.
 *
 * @param pathNodes the list of path nodes
 * @param cost the total cost of the path
 */
public record PathResult(List<PathNode> pathNodes, float cost) {
    /**
     * Constructs the wrapper with a defensive copy.
     *
     * @param pathNodes the list of path nodes
     *
     */
    public PathResult {
        pathNodes = List.copyOf(pathNodes);
    }

    /**
     * Gets the list.
     *
     * @return the list
     */
    public List<PathNode> pathNodes() {
        return pathNodes;
    }

    /**
     * Returns the cost.
     *
     * @return the cost
     */
    public float cost() {
        return cost;
    }
}
