package clashclass.ai.behaviourtree.blackboard.wrappers;

import clashclass.ecs.GameObject;

import java.util.List;

/**
 * Represents a class that incapsulates a {@link List} of {@link GameObject}.
 * It's used in the {@link clashclass.ai.behaviourtree.blackboard.BlackboardProperty} to grant
 * the possibility to access the class type and pass it as a parameter.
 *
 * @param list the list of game objects
 */
public record GameObjectListWrapper(List<GameObject> list) {
    /**
     * Constructs the wrapper with a defensive copy.
     *
     * @param list the list of game objects
     */
    public GameObjectListWrapper {
        list = List.copyOf(list);
    }

    /**
     * Gets the list.
     *
     * @return the list
     */
    public List<GameObject> list() {
        return list;
    }
}
