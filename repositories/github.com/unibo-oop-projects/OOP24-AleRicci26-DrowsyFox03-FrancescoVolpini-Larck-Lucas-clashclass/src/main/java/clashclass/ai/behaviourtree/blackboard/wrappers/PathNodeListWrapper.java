package clashclass.ai.behaviourtree.blackboard.wrappers;

import clashclass.ai.pathfinding.PathNode;

import java.util.List;

/**
 * Represents a class that incapsulates a {@link List} of {@link PathNode}.
 * It's used in the {@link clashclass.ai.behaviourtree.blackboard.BlackboardProperty} to grant
 * the possibility to access the class type and pass it as a parameter.
 *
 * @param list the list of path nodes
 */
public record PathNodeListWrapper(List<PathNode> list) {
    /**
     * Constructs the wrapper with a defensive copy.
     *
     * @param list the list of path nodes
     */
    public PathNodeListWrapper {
        list = List.copyOf(list);
    }

    /**
     * Gets the list.
     *
     * @return the list
     */
    public List<PathNode> list() {
        return list;
    }
}
